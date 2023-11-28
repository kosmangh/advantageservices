package com.sabonay.advantageservices.services;

import com.ctrloption.constants.DebitCredit;
import com.ctrloption.formating.NumberFormattingUtils;
import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.ctrloption.utils.MsgFormatter;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatebilling.BillPayment;
import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.entities.occupancy.Occupant;
import com.sabonay.advantageservices.entities.occupancy.OccupantProperty;
import com.sabonay.advantageservices.models.estatebilling.PropertyLedgerInfo;
import com.sabonay.advantageservices.models.reports.DemandNoticeInfo;
import com.sabonay.advantageservices.requestvalidators.BillPaymentValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.restmodels.billpayment.BillPaymentRequest;
import com.sabonay.advantageservices.restmodels.billpayment.DemandNoticeRequest;
import com.sabonay.advantageservices.restmodels.billpayment.DemandNoticeResponse;
import com.sabonay.advantageservices.restmodels.billpayment.PropertyLedgerEntriesRequest;
import com.sabonay.advantageservices.restmodels.billpayment.PropertyLedgerEntriesResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
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
            AppLogger.printPayload(log, "billPaymentRequest response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.error("invalid rental bill request ");
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            EstateProperty property = basicServices.find(EstateProperty.class, request.getPropertyId());
            if (property == null) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.NO_PROPERTY_FOUND);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            Occupant occupant = basicServices.find(Occupant.class, request.getOccupantId());
            if (occupant == null) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.NO_OCCUPANT_FOUND);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(request.getDateOfTransaction());
            String billingMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.UK);
            int ledgerYear = calendar.get(Calendar.YEAR);
            double amountPaid = 0.0;

            amountPaid = NumberFormattingUtils.formatDecimalNumberTo_2(request.getAmountInvolved());
            PropertyLedger propertyLedger = new PropertyLedger();
            propertyLedger.setEstateProperty(property);
            propertyLedger.setLedgerYear(ledgerYear);
            propertyLedger.setAmountInvolved(amountPaid);
            propertyLedger.setDateOfRecordEntry(new Date());
            propertyLedger.setDateOfRecordTransaction(calendar.getTime());
            propertyLedger.setDebitCreditEntry(DebitCredit.CREDIT.getLabel());
            propertyLedger.setPaymentType(request.getPaymentType());
            propertyLedger.setOccupant(occupant);
            propertyLedger.setPayeeName(occupant.getOccupantName());
            propertyLedger.setMediumOfPayment(request.getModeOfPayment());
            propertyLedger.setMediumOfPaymentNumber(request.getModeOfPaymentNo());
            propertyLedger.setPaymentFor(MsgFormatter.sentenceCase(request.getPaymentType() + " payment, " + ledgerYear));
            propertyLedger.setYearPaidFor(ledgerYear);
            propertyLedger.setReceiptNumberIssued(request.getReceiptNumber());
            IdGeneratorServices.setEstateLedgerId(propertyLedger, billingMonth);

            log.info(propertyLedger + "\t");
            PropertyLedger alreadyPayment4TheMonth = basicServices.find(PropertyLedger.class, propertyLedger.getRecordId());
            if (null == alreadyPayment4TheMonth) {
                log.info("First time  bill payment for " + billingMonth);
                propertyLedger.setCreatedBy(request.getCreatedBy());
                propertyLedger.setCreatedDate(new Date());
            } else {
                log.info("Updated existing bill paymentfor " + billingMonth);
                propertyLedger.setCreatedBy(alreadyPayment4TheMonth.getCreatedBy());
                propertyLedger.setCreatedDate(alreadyPayment4TheMonth.getCreatedDate());
                propertyLedger.setLastModifiedDate(new Date());
                propertyLedger.setLastModifiedBy(request.getCreatedBy());
            }
            basicServices.update(propertyLedger);

            BillPayment billPayment = new BillPayment();
            billPayment.setOccupant(occupant.getRecordId());
            billPayment.setReceiptNumber(request.getReceiptNumber());
            billPayment.setAmountInvolved(amountPaid);
            billPayment.setDateOfTransaction(calendar.getTime());
            billPayment.setDatePaid(new Date());
            billPayment.setModeOfPayment(request.getModeOfPayment());
            billPayment.setModeOfPaymentNo(request.getModeOfPaymentNo());
            billPayment.setCreatedBy(request.getCreatedBy());
            billPayment.setCreatedDate(new Date());
            billPayment.setRecordId(propertyLedger.getRecordId());
            basicServices.update(billPayment);

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
            headerResponse = BillPaymentValidator.validatePropetyLedgerEntriesRequest(request);
            AppLogger.printPayload(log, "billPaymentRequest response ", headerResponse);
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
            AppLogger.printPayload(log, "billPaymentRequest response ", headerResponse);
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
            AppLogger.printPayload(log, "billPaymentRequest response ", headerResponse);
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
            List<PropertyLedgerInfo> listOfPropertyLedgers = utitlityServices.getBillPayments(request);
            if (null == listOfPropertyLedgers) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
//            response.setPropertyLedgers(listOfPropertyLedgers);
            response.setCurrentBalance(0.0);
            response.setTotalCredit(0.0);
            response.setTotalDebit(0.0);
            if (!listOfPropertyLedgers.isEmpty()) {
                response.setCurrentBalance(0.0);
                response.setTotalCredit(0.0);
                response.setTotalDebit(0.0);
            }
            //current balance
            Double totalDebit = 0.0, totalCredit = 0.0, balance = 0.0;
            for (PropertyLedgerInfo eachOne : listOfPropertyLedgers) {
                if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.CREDIT.getLabel())) {
                    totalCredit += eachOne.getAmountInvolved();
                } else if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.DEBIT.getLabel())) {
                    totalDebit += eachOne.getAmountInvolved();
                }
            }

            List<PropertyLedgerInfo> payments = new ArrayList<>();
            if (listOfPropertyLedgers.size() > 1000) {
                log.info("records more than 1000 use parallelStream");
                payments = listOfPropertyLedgers.parallelStream()
                        .filter(eachOne -> eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.CREDIT.getLabel()))
                        .collect(Collectors.toList());
            } else {
                log.info("records less than 1000 use stream");
                payments = listOfPropertyLedgers.stream()
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
            if (null == request.getChargeYear()) {
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
            }

            List<PropertyLedger> listOfGroundRentBills = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, PropertyLedger.class);
            builder.addStringQryParam(search, request.getSearchValue(), QryBuilder.ComparismCriteria.LIKE);
            builder.addStringQryParam(EntityFields.paymentType, PaymentType.GROUND_RENT.getLabel(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addNumberParam(EntityFields.ledgerYear, request.getChargeYear(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addObjectParam(EntityFields.deleted, false);
            log.info(" fetching ledgers " + builder.getQryInfo());
            listOfGroundRentBills = builder.buildQry().getResultList();
            if (null == listOfGroundRentBills) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            log.info("total staff retrieved " + listOfGroundRentBills.size());
            List<DemandNoticeInfo> listOfDemandNotices = new ArrayList<>();
            Double[] totalLedgers;
            Double totalArrears = 0.0, totalCurrentCharge = 0.0;
            if (!listOfGroundRentBills.isEmpty()) {
                for (PropertyLedger eachOne : listOfGroundRentBills) {
                    OccupantProperty property = utitlityServices.getOccupantPropertyNew(eachOne.getOccupant(), eachOne.getEstateProperty());
//                    AppLogger.printPayloadCompact(log, "property found: ", property);
                    if (Objects.isNull(property)) {
                    } else {
                    totalLedgers = new Double[2];
                    totalLedgers = utitlityServices.getPreviousAndCurrentBalances4DemandNotice(eachOne.getOccupant().getRecordId(), eachOne.getEstateProperty().getRecordId(), request.getChargeYear());
                    totalCurrentCharge += totalLedgers[0];
                    totalArrears += totalLedgers[1];
                        listOfDemandNotices.add(new DemandNoticeInfo(eachOne, totalLedgers[0], totalLedgers[1], property.getLastDateOfOccupancy()));
                    listOfDemandNotices.add(new DemandNoticeInfo(eachOne, totalLedgers[0], totalLedgers[1], new Date()));
                    }

                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setTotalCurrentCharge(totalCurrentCharge);
            response.setTotalArrears(totalArrears);
            response.setTotalAmountDue(totalArrears + totalCurrentCharge);

//            for (DemandNoticeInfo eachOne : listOfDemandNotices) {
//                OccupantProperty property = utitlityServices.getOccupantProperty(eachOne.getOccupant().getRecordId(), eachOne.getEstateProperty().getRecordId());
//
//            }
            response.setDemandNotices(listOfDemandNotices);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }
}
