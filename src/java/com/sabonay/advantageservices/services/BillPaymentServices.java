package com.sabonay.advantageservices.services;

import com.ctrloption.constants.BillStatus;
import com.ctrloption.constants.DebitCredit;
import com.ctrloption.formating.NumberFormattingUtils;
import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.ctrloption.utils.MsgFormatter;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatebilling.Bills;
import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
import com.sabonay.advantageservices.entities.occupancy.OccupantProperty;
import com.sabonay.advantageservices.models.billpayment.BillsInfo;
import com.sabonay.advantageservices.models.estatebilling.PropertyLedgerInfo;
import com.sabonay.advantageservices.models.reports.DemandNoticeInfo;
import com.sabonay.advantageservices.requestvalidators.BillPaymentValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.restmodels.billpayment.BillPaymentRequest;
import com.sabonay.advantageservices.restmodels.billpayment.DemandNoticeRequest;
import com.sabonay.advantageservices.restmodels.billpayment.DemandNoticeResponse;
import com.sabonay.advantageservices.restmodels.billpayment.OccupantBillsRequest;
import com.sabonay.advantageservices.restmodels.billpayment.OccupantBillsResponse;
import com.sabonay.advantageservices.restmodels.billpayment.PropertyLedgerEntriesRequest;
import com.sabonay.advantageservices.restmodels.billpayment.PropertyLedgerEntriesResponse;
import com.sabonay.advantageservices.restmodels.billpayment.ReversePayBillRequest;
import com.sabonay.advantageservices.restmodels.billpayment.UpdatePayBillRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.services.utils.UtitlityServices;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import com.sabonay.advantageservices.utils.enums.PaymentType;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Thu 24 Aug, 2023 05:32 am
 */
@Stateless
public class BillPaymentServices extends CrudController implements Serializable {

    private static final Logger log = Logger.getLogger(BillPaymentServices.class.getName());
    @Inject
    private BasicServices basicServices;
    @Inject
    private UtitlityServices utitlityServices;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        setEm(em);
        setEnviroment(Enviroment.JAVA_EE);
    }

    public GenericResponse processPayRentBillRequest(BillPaymentRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            log.info("about to billPaymentRequest");
            headerResponse = BillPaymentValidator.validateBillPaymentRequest(request);
//            AppLogger.printPayload(log, "billPaymentRequest response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.error("invalid rental bill request ");
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            List<PropertyLedger> listOfReceipts = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, PropertyLedger.class);
            builder.addStringQryParam(EntityFields.receiptNumberIssued, request.getReceiptNumber(), QryBuilder.ComparismCriteria.EQUAL);
            log.info(" checking duplicate receiptNumberIssued " + builder.getQryInfo());
            listOfReceipts = builder.buildQry().getResultList();
            log.info("Total receiptNumberIssued found " + listOfReceipts.size());
            if (!listOfReceipts.isEmpty()) {
                msg = "Receipt number already exist;duplicate not allowed";
                log.error(msg);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            Bills bill = basicServices.find(Bills.class, request.getBillId());
            if (Objects.isNull(bill)) {
                log.info("Bill not found");
                msg = "Bill not found";
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(request.getDateOfTransaction());
            String billingMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
            int ledgerYear = request.getBillYear();
            double amount2BePaid = 0.0;
            amount2BePaid = NumberFormattingUtils.formatDecimalNumberTo_2(request.getamountPaid());
            int totalBills = 0;
            List<Bills> listOfBills = new ArrayList<>();
            listOfBills = utitlityServices.getOccupantOutstandingBills(bill, request.isSpreadAmtForOccupantProperties());
            totalBills = listOfBills.size();
            log.info("total bills found " + totalBills);
            amount2BePaid = amount2BePaid / totalBills;
            log.info("Each property will to be paid:: " + amount2BePaid);
            for (Bills eachBill : listOfBills) {

                PropertyLedger propertyLedger = new PropertyLedger();
                propertyLedger.setEstateProperty(eachBill.getEstateProperty());
                propertyLedger.setLedgerYear(ledgerYear);
                propertyLedger.setamountPaid(amount2BePaid);
                propertyLedger.setDateOfRecordEntry(new Date());
                propertyLedger.setDateOfRecordTransaction(calendar.getTime());
                propertyLedger.setDebitCreditEntry(DebitCredit.CREDIT.getLabel());
                propertyLedger.setPaymentType(request.getPaymentType());
                propertyLedger.setOccupant(eachBill.getOccupant());
                propertyLedger.setPayeeName(eachBill.getOccupant().getOccupantName());
                propertyLedger.setMediumOfPayment(request.getModeOfPayment());
                propertyLedger.setMediumOfPaymentNumber(request.getModeOfPaymentNo());
                String narration = MsgFormatter.sentenceCase(request.getPaymentType() + " payment," + ledgerYear);
                if (eachBill.getBillType().equalsIgnoreCase(PaymentType.HOUSE_RENT.getLabel())) {
                    narration = MsgFormatter.sentenceCase(request.getPaymentType() + " payment," + eachBill.getBillMonth() + " " + ledgerYear);
                }
                propertyLedger.setPaymentFor(narration);
                propertyLedger.setYearPaidFor(ledgerYear);
                propertyLedger.setReceiptNumberIssued(request.getReceiptNumber());
                IdGeneratorServices.setEstateLedgerId(propertyLedger, billingMonth);

                log.info(propertyLedger + "\t");
                PropertyLedger alreadyPaid = basicServices.find(PropertyLedger.class, propertyLedger.getRecordId());
                if (null == alreadyPaid) {
                    log.info("First time  bill payment for " + billingMonth);
                    propertyLedger.setCreatedBy(request.getCreatedBy());
                    propertyLedger.setCreatedDate(new Date());
                } else {
                    log.info("Updated existing bill paymentfor " + billingMonth);
                    propertyLedger.setCreatedBy(alreadyPaid.getCreatedBy());
                    propertyLedger.setCreatedDate(alreadyPaid.getCreatedDate());
                    propertyLedger.setLastModifiedDate(new Date());
                    propertyLedger.setLastModifiedBy(request.getCreatedBy());
                }

                eachBill.setBillAmountPaid(eachBill.getBillAmountPaid() + amount2BePaid);
                log.info("bill amount " + eachBill.getBillAmount());
                log.info("bill total amount Paid " + eachBill.getBillAmountPaid());
                Double arrears = eachBill.getBillAmount() - eachBill.getBillAmountPaid();
                log.info("arrears:: " + arrears);
                if (arrears > 0) {
                    log.info("some payment has been made before but "
                            + "there is still arrears, set bill status=PART PAYMENT & entry type=DEBIT BAL");
                    eachBill.setBillStatus(BillStatus.PART_PAYMENT.getLabel());
                    eachBill.setEntryType(DebitCredit.DEBIT_BAL.getLabel());
                } else if (arrears < 0) {
                    log.info("there is more payment than arrears "
                            + "set bill status=PAID & entry type=CREDIT BAL");
                    eachBill.setBillStatus(BillStatus.PAID.getLabel());
                    eachBill.setEntryType(DebitCredit.CREDIT_BAL.getLabel());
                } else if (arrears == 0) {
                    log.info("bill amount is equal to bill amount paid, no arrears "
                            + "set bill status=PAID & entry type=NONE");
                    eachBill.setBillStatus(BillStatus.PAID.getLabel());
                    eachBill.setEntryType(DebitCredit.NONE.getLabel());
                }

                eachBill.setLastCrDate(new Date());
                eachBill.setLastCrRecordedBy(request.getCreatedBy());
                eachBill.setOccupant(propertyLedger.getOccupant());
                eachBill.setEstateProperty(propertyLedger.getEstateProperty());
                basicServices.update(eachBill);

                propertyLedger.setBill(eachBill);
                basicServices.update(propertyLedger);
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(request.getPaymentType() + " successfully paid"));
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "processRentalBillRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse processUpdatePayBillRequest(UpdatePayBillRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            log.info("about to UpdatePayBillRequest");
            headerResponse = BillPaymentValidator.validateUpdateBillPaymentRequest(request);
//            AppLogger.printPayload(log, "billPaymentRequest response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.error("invalid rental bill request ");
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            Bills bill = basicServices.find(Bills.class, request.getBillId());
            if (Objects.isNull(bill)) {
                log.info("Bill not found");
                msg = "Bill not found";
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            PropertyLedger ledger = basicServices.find(PropertyLedger.class, request.getLedgerId());
            if (Objects.isNull(ledger)) {
                log.info("Ledger  not found for " + request.getLedgerId());
                msg = "No ledger found for update";
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            log.info("Deduct old ledger amount from bill amount and then  add the new amount");
            Double updatedAmountPaid = (bill.getBillAmountPaid() - ledger.getamountPaid()) + request.getAmountPaid();

            log.info("Bill found;make changes");
            bill.setLastModifiedBy(request.getModifiedBy());
            bill.setLastModifiedDate(new Date());
            bill.setBillAmountPaid(updatedAmountPaid);

            Double arrears = bill.getBillAmount() - bill.getBillAmountPaid();
            log.info("arrears:: " + arrears);
            if (arrears > 0) {
                log.info("some payment has been made before but "
                        + "there is still arrears, set bill status=PART PAYMENT & entry type=DEBIT BAL");
                bill.setBillStatus(BillStatus.PART_PAYMENT.getLabel());
                bill.setEntryType(DebitCredit.DEBIT_BAL.getLabel());
            } else if (arrears < 0) {
                log.info("there is more payment than arrears "
                        + "set bill status=PAID & entry type=CREDIT BAL");
                bill.setBillStatus(BillStatus.PAID.getLabel());
                bill.setEntryType(DebitCredit.CREDIT_BAL.getLabel());
            } else if (arrears == 0) {
                log.info("bill amount is equal to bill amount paid, no arrears "
                        + "set bill status=PAID & entry type=NONE");
                bill.setBillStatus(BillStatus.PAID.getLabel());
                bill.setEntryType(DebitCredit.NONE.getLabel());
            }

            ledger.setMediumOfPayment(request.getModeOfPayment());
            ledger.setMediumOfPaymentNumber(request.getModeOfPaymentNo());
            ledger.setReceiptNumberIssued(request.getReceiptNumber());
            ledger.setLastModifiedBy(request.getModifiedBy());
            ledger.setLastModifiedDate(new Date());
            ledger.setamountPaid(request.getAmountPaid());
            basicServices.update(bill);
            basicServices.update(ledger);
            log.info("Bill payment updated successfully for " + bill.getRecordId());
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(ledger.getPaymentType() + " successfully updated"));
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "processRentalBillRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse processReversePayBillRequest(ReversePayBillRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            log.info("about to ReversePayBillRequest");

            if (null == request.getBillId()) {
                msg = "Bill id is required";
                log.error(msg);
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            if (null == request.getLedgerId()) {
                msg = "ledger id is required";
                log.error(msg);
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            Bills bill = basicServices.find(Bills.class, request.getBillId());
            if (Objects.isNull(bill)) {
                log.info("Bill not found");
                msg = "Bill not found";
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            PropertyLedger ledger = basicServices.find(PropertyLedger.class, request.getLedgerId());
            if (Objects.isNull(ledger)) {
                log.info("Ledger  not found for " + request.getLedgerId());
                msg = "No ledger found for update";
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            log.info("Deduct old ledger amount from bill amount and then  add the new amount");
            Double updatedAmountPaid = (bill.getBillAmountPaid() - ledger.getamountPaid());

            log.info("Bill found;make changes");
            bill.setLastModifiedBy(request.getModifiedBy());
            bill.setLastModifiedDate(new Date());
            bill.setBillAmountPaid(updatedAmountPaid);

            Double arrears = bill.getBillAmount() - bill.getBillAmountPaid();
            log.info("arrears:: " + arrears);
            if (updatedAmountPaid == 0) {
                log.info("some payment has been made before but "
                        + "there is still arrears, set bill status=PART PAYMENT & entry type=DEBIT BAL");
                bill.setBillStatus(BillStatus.UNPAID.getLabel());
                bill.setEntryType(DebitCredit.DEBIT.getLabel());
            } else if (arrears > 0) {
                log.info("some payment has been made before but "
                        + "there is still arrears, set bill status=PART PAYMENT & entry type=DEBIT BAL");
                bill.setBillStatus(BillStatus.PART_PAYMENT.getLabel());
                bill.setEntryType(DebitCredit.DEBIT_BAL.getLabel());
            } else if (arrears < 0) {
                log.info("there is more payment than arrears "
                        + "set bill status=PAID & entry type=CREDIT BAL");
                bill.setBillStatus(BillStatus.PAID.getLabel());
                bill.setEntryType(DebitCredit.CREDIT_BAL.getLabel());
            } else if (arrears == 0) {
                log.info("bill amount is equal to bill amount paid, no arrears "
                        + "set bill status=PAID & entry type=NONE");
                bill.setBillStatus(BillStatus.PAID.getLabel());
                bill.setEntryType(DebitCredit.NONE.getLabel());
            }

            log.info("About to update bill id " + request.getBillId() + " and delete ledger id:: " + request.getLedgerId());
            basicServices.update(bill);
            basicServices.deleteCrud(ledger, true);
            log.info("Bill payment updated successfully for " + bill.getRecordId());
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(ledger.getPaymentType() + " successfully reversed"));
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "processRentalBillRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public PropertyLedgerEntriesResponse fetchOccupantLedger(PropertyLedgerEntriesRequest request) throws IOException {
        PropertyLedgerEntriesResponse response = new PropertyLedgerEntriesResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            log.info("about to billPaymentRequest");
            //list of entries
            List<PropertyLedgerInfo> listOfPropertyLedgers = utitlityServices.getOccupantPropertyLedgerEntries(request);
            if (null == listOfPropertyLedgers) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            log.info("listOfPropertyLedgers size " + listOfPropertyLedgers.size());
            response.setPropertyLedgers(listOfPropertyLedgers);
            //current balance
            Double currentBal = utitlityServices.occupantCurrentBal(request.getOccupantId(), request.getPropertyId());
            response.setCurrentBalance(currentBal);
            Double[] entriesBalances = utitlityServices.occupantCurrentBalWithinDateRange(request.getOccupantId(), request.getPropertyId(),
                    request.getStartDate(), request.getEndDate());
            response.setTotalCredit(entriesBalances[1]);
            response.setTotalDebit(entriesBalances[0]);
            //outstanding bal for DR ledgers
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "processRentalBillRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public PropertyLedgerEntriesResponse fetchAllOccupantLedger(PropertyLedgerEntriesRequest request) throws IOException {
        PropertyLedgerEntriesResponse response = new PropertyLedgerEntriesResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            log.info("about to billPaymentRequest");
            headerResponse = BillPaymentValidator.validatePropetyLedgerEntriesRequest(request);
//            AppLogger.printPayload(log, "billPaymentRequest response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.error("invalid rental bill request ");
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            //list of entries
            List<PropertyLedgerInfo> listOfPropertyLedgers = utitlityServices.getAllOccupantPropertyLedgerEntries(request);
            if (null == listOfPropertyLedgers) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            log.info("listOfPropertyLedgers size " + listOfPropertyLedgers.size());
            response.setPropertyLedgers(listOfPropertyLedgers);
            //current balance
            Double currentBal = utitlityServices.occupantCurrentBal(request.getOccupantId(), request.getPropertyId());
            response.setCurrentBalance(currentBal);
            Double[] entriesBalances = utitlityServices.allOccupantCurrentBal(request.getOccupantId(), request.getPropertyId());
            response.setTotalCredit(entriesBalances[1]);
            response.setTotalDebit(entriesBalances[0]);
            //outstanding bal for DR ledgers
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "processRentalBillRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public OccupantBillsResponse fetchOutstandingBills(OccupantBillsRequest request) throws IOException {
        OccupantBillsResponse response = new OccupantBillsResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            log.info("about to OccupantBillsRequest");
            if (null == request.getPropertyId()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.PROPERTY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            if (null == request.getOccupantId()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.OCCUPANT_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

//            AppLogger.printPayload(log, "billPaymentRequest response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.error("invalid rental bill request ");
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            //list of entries
            List<BillsInfo> listOfOccupantPropertyBills = utitlityServices.getAllOutstandingBills(request);
            if (null == listOfOccupantPropertyBills) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            log.info("listOfPropertyLedgers size " + listOfOccupantPropertyBills.size());
            response.setListOfBills(listOfOccupantPropertyBills);
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "processRentalBillRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public PropertyLedgerEntriesResponse fetchBillPayments(PropertyLedgerEntriesRequest request) throws IOException {
        PropertyLedgerEntriesResponse response = new PropertyLedgerEntriesResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            log.info("about to billPaymentRequest");
            headerResponse = BillPaymentValidator.validatePropetyLedgerEntriesRequest(request);
//            AppLogger.printPayload(log, "billPaymentRequest response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.error("invalid rental bill request ");
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            //list of entries
            List<PropertyLedgerInfo> listOfPayments = utitlityServices.getBillPayments(request);
            if (null == listOfPayments) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            response.setCurrentBalance(0.0);
            response.setTotalCredit(0.0);
            response.setTotalDebit(0.0);
            if (!listOfPayments.isEmpty()) {
                response.setCurrentBalance(0.0);
                response.setTotalCredit(0.0);
                response.setTotalDebit(0.0);
            }
            //current balance
            Double totalDebit = 0.0, totalCredit = 0.0, balance = 0.0;
            for (PropertyLedgerInfo eachOne : listOfPayments) {
                if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.CREDIT.getLabel())
                        || eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.CREDIT_BAL.getLabel())) {
                    totalCredit += eachOne.getamountPaid();
                } else if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.DEBIT.getLabel())
                        || eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.DEBIT_BAL.getLabel())) {
                    totalDebit += eachOne.getamountPaid();
                }
            }

            List<PropertyLedgerInfo> payments = new ArrayList<>();
            if (listOfPayments.size() > 1000) {
                log.info("records more than 1000 use parallelStream");
                payments = listOfPayments.parallelStream()
                        .filter(eachOne -> eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.CREDIT.getLabel()))
                        .collect(Collectors.toList());
            } else {
                log.info("records less than 1000 use stream");
                payments = listOfPayments.stream()
                        .filter(eachOne -> eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.CREDIT.getLabel()))
                        .collect(Collectors.toList());
            }

            balance = totalDebit - totalCredit;
            log.info(" totalDebit: " + totalDebit + " totalDebit: " + totalDebit + " balance: " + balance);
            response.setCurrentBalance(0.0);
            response.setTotalCredit(totalCredit);
            response.setTotalDebit(totalDebit);
            response.setCurrentBalance(balance);
            response.setPropertyLedgers(payments);
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "processRentalBillRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public DemandNoticeResponse generateDemandNotice(DemandNoticeRequest request) throws IOException {
        DemandNoticeResponse response = new DemandNoticeResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            String msg = "";
            if (null == request.getSearchParameter()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.SEARCH_BY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (null == request.getSearchValue()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.SEARCH_VALUE_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (null == request.getBillYear()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.RENT_YEAR_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            String search = "";
            if (request.getSearchParameter().equalsIgnoreCase("BAB")) {
                search = "estateProperty.estateBlock.recordId";
            } else if (request.getSearchParameter().equalsIgnoreCase("NAM")) {
                search = "occupant.occupantName";
            } else if (request.getSearchParameter().equalsIgnoreCase("MOB")) {
                search = "occupant.mobileNo";
            } else if (request.getSearchParameter().equalsIgnoreCase("PNAM")) {
                search = "estateProperty.propertyName";
            } else if (request.getSearchParameter().equalsIgnoreCase("PNO")) {
                search = "estateProperty.propertyNumber";
            } else if (request.getSearchParameter().equalsIgnoreCase("PID")) {
                search = "estateProperty.recordId";
            }

            log.info("Gets bills for selected bill/payment type " + request.getBillType());
            List<Bills> listOfGroundRentBills = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, Bills.class);
            builder.addStringQryParam(search, request.getSearchValue(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addStringQryParam(EntityFields.billType, request.getBillType(), QryBuilder.ComparismCriteria.EQUAL);
            if (request.getBillType().equalsIgnoreCase(PaymentType.HOUSE_RENT.getLabel())) {
                builder.addStringQryParam(EntityFields.billMonth, request.getBillMonth(), QryBuilder.ComparismCriteria.EQUAL);
            }
            builder.addNumberParam(EntityFields.billYear, request.getBillYear(), QryBuilder.ComparismCriteria.EQUAL);
            log.info(" fetching bills " + builder.getQryInfo());
            listOfGroundRentBills = builder.buildQry().getResultList();
            if (null == listOfGroundRentBills) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            log.info("total bills retrieved for " + request.getBillType() + ":: " + listOfGroundRentBills.size());
            if (listOfGroundRentBills.isEmpty()) {
                msg = "No bills found for demand notice";
                log.info(msg);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            List<DemandNoticeInfo> listOfDemandNotices = new ArrayList<>();
            Double[] currentBalAndArrears;
            Double totalArrears = 0.0, totalCurrentCharge = 0.0;
            for (Bills eachBill : listOfGroundRentBills) {
                OccupantProperty property = utitlityServices.getOccupantPropertyNew(eachBill.getOccupant(), eachBill.getEstateProperty());
                if (Objects.isNull(property)) {
                } else {
                    currentBalAndArrears = new Double[2];
                    currentBalAndArrears = utitlityServices.getCurrentBalAndArrears4DemandNotice(eachBill, request);
//                    if (currentBalAndArrears[0] > 0 && currentBalAndArrears[1] > 0) {
                    totalCurrentCharge += currentBalAndArrears[0];
                    totalArrears += currentBalAndArrears[1];
                    listOfDemandNotices.add(new DemandNoticeInfo(eachBill, currentBalAndArrears, property.getLastDateOfOccupancy()));
//                    }
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);

            if (totalCurrentCharge < 0) {
                log.info("There is credit bal for selected year current charge");
                response.setTotalCurrentCharge(0.0);
                response.setTotalArrears(totalArrears + totalCurrentCharge);
            }
            if (totalArrears < 0) {
                log.info("There is credit bal for selected year charge");
                response.setTotalArrears(totalArrears * -1);
                response.setEntryType("Credit Bal");
            }
            if (totalArrears > 0) {
                response.setTotalArrears(totalArrears);
                response.setEntryType("Debit Bal");
            }

//            response.setTotalCurrentCharge(totalCurrentCharge);
            response.setTotalAmountDue(response.getTotalArrears() + totalCurrentCharge);
            response.setDemandNotices(listOfDemandNotices);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

}
