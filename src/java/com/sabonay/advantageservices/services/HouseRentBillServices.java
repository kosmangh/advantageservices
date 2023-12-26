package com.sabonay.advantageservices.services;

import com.ctrloption.constants.BillStatus;
import com.ctrloption.constants.DebitCredit;
import com.ctrloption.formating.DateTimeUtils;
import com.ctrloption.formating.NumberFormattingUtils;
import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.ctrloption.models.BillMonth;
import com.ctrloption.utils.MsgFormatter;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatebilling.Bills;
import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.entities.occupancy.OccupantProperty;
import com.sabonay.advantageservices.models.DataFetchType;
import com.sabonay.advantageservices.models.estatebilling.PropertyLedgerInfo;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.requestvalidators.RentalBillValidator;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatebilling.GroundRentalBillListResponse;
import com.sabonay.advantageservices.restmodels.estatebilling.RentalBackBillRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.RentalBillListRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.RentalBillRequest;
import com.sabonay.advantageservices.services.utils.UtitlityServices;
import com.sabonay.advantageservices.utils.AdConstants;
import com.sabonay.advantageservices.utils.AdvantageDateUtils;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import com.sabonay.advantageservices.utils.enums.OccupationType;
import com.sabonay.advantageservices.utils.enums.PaymentType;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
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
 * @date Sat 19 Aug, 2023 05:00 am
 */
@Stateless
public class HouseRentBillServices extends CrudController implements Serializable {

    private static final Logger log = Logger.getLogger(HouseRentBillServices.class.getName());
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

    public GenericResponse processRentalBillRequest(RentalBillRequest request) throws IOException {
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
            log.info("about to validateRentalBillRequest");
            headerResponse = RentalBillValidator.validateRentalBillRequest(request);
            AppLogger.printPayload(log, "validateRentalBillRequest response ", headerResponse);
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
                msg = ResponseCodes.NO_PROPERTY_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            DataFetchType dataFetchType = new DataFetchType(request);
            dataFetchType.setOccupationType(OccupationType.RENTAL.getLabel());
            AppLogger.printPayload(log, "dataFetchType for RentalBillRequest ", dataFetchType);
            List<OccupantProperty> listOfOccupantProperties = utitlityServices.getOccupantProperties4Billing(dataFetchType);
            log.info("Total records  occupant properties for processRentalBillRequest: " + listOfOccupantProperties.size());
            if (null == listOfOccupantProperties) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                AppLogger.printPayload(log, "", response);
                return response;
            }
            if (listOfOccupantProperties.isEmpty()) {
                msg = property.getPropertyName() + " is not occupied as Rental";
                log.info(msg);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            int notProcessCount = 0, processCount = 0;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(request.getBillDate());
            String billingMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.UK);
            int ledgerYear = calendar.get(Calendar.YEAR);
            double amtToBeCharge = 0.0;
            for (OccupantProperty eachOne : listOfOccupantProperties) {
                amtToBeCharge = NumberFormattingUtils.formatDecimalNumberTo_2(request.getRentAmount());
                PropertyLedger propertyLedger = new PropertyLedger();
                propertyLedger.setEstateProperty(eachOne.getEstateProperty());
                propertyLedger.setLedgerYear(ledgerYear);
                propertyLedger.setamountPaid(amtToBeCharge);
                propertyLedger.setDateOfRecordEntry(new Date());
                propertyLedger.setDateOfRecordTransaction(calendar.getTime());
                propertyLedger.setDebitCreditEntry(DebitCredit.DEBIT.getLabel());
                propertyLedger.setPaymentType(PaymentType.HOUSE_RENT.getLabel());
                propertyLedger.setOccupant(eachOne.getOccupant());
                propertyLedger.setPayeeName(AdConstants.SHC);
                propertyLedger.setMediumOfPayment(AdConstants.NONE);
                propertyLedger.setMediumOfPaymentNumber(AdConstants.NONE);
                propertyLedger.setPaymentFor("Rental Bill, " + billingMonth + " " + ledgerYear);
                IdGeneratorServices.setEstateLedgerId(propertyLedger, billingMonth);
                log.info(propertyLedger + "\t");
                PropertyLedger alreadyBilled4TheMonth = basicServices.find(PropertyLedger.class, propertyLedger.getRecordId());
                if (null == alreadyBilled4TheMonth) {
                    log.info("First time rent bill for " + billingMonth);
                    propertyLedger.setCreatedBy(request.getCreatedBy());
                    propertyLedger.setCreatedDate(new Date());
                } else {
                    log.info("Updated existing rent bill for " + billingMonth);
                    propertyLedger.setCreatedBy(alreadyBilled4TheMonth.getCreatedBy());
                    propertyLedger.setCreatedDate(alreadyBilled4TheMonth.getCreatedDate());
                    propertyLedger.setLastModifiedDate(new Date());
                    propertyLedger.setLastModifiedBy(request.getCreatedBy());
                }
                String recordId = propertyLedger.getRecordId();
                Bills bills = new Bills();
                bills = basicServices.find(Bills.class, recordId);
                log.info(" record id " + recordId);

                if (Objects.isNull(bills)) {
                    log.info("Bill not found;first time billing");
                    log.info(" record id " + recordId);
                    bills = new Bills();
                    bills.setRecordId(recordId);
                    bills.setCreatedBy(request.getCreatedBy());
                    bills.setCreatedDate(new Date());

                    bills.setBillStatus(BillStatus.UNPAID.getLabel());
                    bills.setEntryType(DebitCredit.DEBIT.getLabel());
                    bills.setBillAmountPaid(0.0);
                    bills.setBillAmount(amtToBeCharge);

                } else {
                    log.info("Bill found;existing billing");
                    bills.setBillAmount(amtToBeCharge);
                    Double arrears = bills.getBillAmount() - bills.getBillAmountPaid();
                    if (arrears > 0 && bills.getBillAmountPaid() > 0) {
                        log.info("some payment has been made before but "
                                + "there is still arrears, set bill status=PART PAYMENT & entry type=DEBIT BAL");
                        bills.setBillStatus(BillStatus.PART_PAYMENT.getLabel());
                        bills.setEntryType(DebitCredit.DEBIT_BAL.getLabel());
                    } else if (arrears < 0) {
                        log.info("there is more payment than arrears "
                                + "set bill status=PAID & entry type=CREDIT BAL");
                        bills.setBillStatus(BillStatus.PAID.getLabel());
                        bills.setEntryType(DebitCredit.CREDIT_BAL.getLabel());
                    } else if (arrears == 0) {
                        log.info("bill amount is equal to bill amount paid, no arrears "
                                + "set bill status=PAID & entry type=NONE");
                        bills.setBillStatus(BillStatus.PAID.getLabel());
                        bills.setEntryType(DebitCredit.NONE.getLabel());
                    }
                    bills.setLastModifiedBy(request.getCreatedBy());
                    bills.setLastModifiedDate(new Date());
                }
                bills.setBillMonth(billingMonth.toUpperCase());
                bills.setDeleted(false);
                bills.setLastDrDate(new Date());
                bills.setLastDrRecordedBy(request.getCreatedBy());
                bills.setBillYear(ledgerYear);
                bills.setBillType(PaymentType.HOUSE_RENT.getLabel());
                bills.setBillPenaltyAmount(0.0);
                bills.setOccupant(eachOne.getOccupant());
                bills.setEstateProperty(eachOne.getEstateProperty());
                basicServices.update(bills);
                propertyLedger.setBill(bills);
                basicServices.update(propertyLedger);
                processCount++;

//                basicServices.update(propertyLedger);
            }

            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage("Applying rental bill For " + billingMonth + " " + ledgerYear + " completed");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateGroundRentBill IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse processLastRentalbill4CurrentMonth(RentalBillRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            log.info("about to validateRentalBillRequest");
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            log.info("About to processBatchRentalBillRequest ");
            log.info("Getting all rental occupant properties");
            List<OccupantProperty> listOfOccupantProperties = new ArrayList<>();
            listOfOccupantProperties = utitlityServices.getAllOccupantProperties4Billing(OccupationType.RENTAL.getLabel());
            log.info("Total records  occupant properties found : " + listOfOccupantProperties.size());
            if (null == listOfOccupantProperties) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                AppLogger.printPayload(log, "", response);
                return response;
            }
            if (listOfOccupantProperties.isEmpty()) {
                msg = "No properties occupied as Rental;please assisgn properties to occupants";
                log.info(msg);
                headerResponse.setResponseCode(ResponseCodes.SUCCESS);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            String billingMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.UK);
            int ledgerYear = calendar.get(Calendar.YEAR);
            log.info("Run through each occupant property get last rental billed amount and creat a debit ledger");
            for (OccupantProperty eachOne : listOfOccupantProperties) {
                PropertyLedger lastRentChargedLedger = utitlityServices.lastDebitLedgerEntryForProperty(eachOne.getEstateProperty().getRecordId());
                if (null == lastRentChargedLedger) {
                    log.info("No last rent amount charged for " + eachOne.getEstateProperty().toString() + " skip to next ");
                    continue;
                }
                PropertyLedger propertyLedger = new PropertyLedger();
                propertyLedger.setEstateProperty(eachOne.getEstateProperty());
                propertyLedger.setLedgerYear(ledgerYear);
                propertyLedger.setamountPaid(lastRentChargedLedger.getamountPaid());
                propertyLedger.setDateOfRecordEntry(new Date());
                propertyLedger.setDateOfRecordTransaction(calendar.getTime());
                propertyLedger.setDebitCreditEntry(DebitCredit.DEBIT.getLabel());
                propertyLedger.setPaymentType(PaymentType.HOUSE_RENT.getLabel());
                propertyLedger.setOccupant(eachOne.getOccupant());
                propertyLedger.setPayeeName(AdConstants.SHC);
                propertyLedger.setMediumOfPayment(AdConstants.NONE);
                propertyLedger.setMediumOfPaymentNumber(AdConstants.NONE);
                propertyLedger.setPaymentFor("Property rent, " + billingMonth + " " + ledgerYear);
                IdGeneratorServices.setEstateLedgerId(propertyLedger, billingMonth);
                log.info(propertyLedger + "\t");
                List<PropertyLedger> propertyLedgers = basicServices.searchRecordsStrict(PropertyLedger.class, EntityFields.recordId, propertyLedger.getRecordId());
                if (null == propertyLedgers) {
                    log.error("Error finding property ledger " + eachOne.getRecordId() + " skip");
                    continue;
                }
                if (propertyLedgers.isEmpty()) {
                    log.info("First time rent bill for " + billingMonth + "," + ledgerYear);
                    propertyLedger.setCreatedBy(request.getCreatedBy());
                    propertyLedger.setCreatedDate(new Date());
                } else {
                    PropertyLedger ledgerAlreadyExist = propertyLedgers.get(0);
                    log.info("Updated existing rent bill for " + billingMonth + "," + ledgerYear);
                    propertyLedger.setCreatedBy(ledgerAlreadyExist.getCreatedBy());
                    propertyLedger.setCreatedDate(ledgerAlreadyExist.getCreatedDate());
                    propertyLedger.setLastModifiedDate(new Date());
                    propertyLedger.setLastModifiedBy(request.getCreatedBy());
                }
                String recordId = propertyLedger.getRecordId();
                Bills bills = new Bills();
                bills = basicServices.find(Bills.class, recordId);
                log.info(" record id " + recordId);

                if (Objects.isNull(bills)) {
                    log.info("Bill not found;first time billing");
                    log.info(" record id " + recordId);
                    bills = new Bills();
                    bills.setRecordId(recordId);
                    bills.setCreatedBy(request.getCreatedBy());
                    bills.setCreatedDate(new Date());

                    bills.setBillStatus(BillStatus.UNPAID.getLabel());
                    bills.setEntryType(DebitCredit.DEBIT.getLabel());
                    bills.setBillAmountPaid(0.0);
                    bills.setBillAmount(lastRentChargedLedger.getamountPaid());

                } else {
                    log.info("Bill found;existing billing");
                    bills.setBillAmount(lastRentChargedLedger.getamountPaid());
                    Double arrears = bills.getBillAmount() - bills.getBillAmountPaid();
                    if (arrears > 0 && bills.getBillAmountPaid() > 0) {
                        log.info("some payment has been made before but "
                                + "there is still arrears, set bill status=PART PAYMENT & entry type=DEBIT BAL");
                        bills.setBillStatus(BillStatus.PART_PAYMENT.getLabel());
                        bills.setEntryType(DebitCredit.DEBIT_BAL.getLabel());
                    } else if (arrears < 0) {
                        log.info("there is more payment than arrears "
                                + "set bill status=PAID & entry type=CREDIT BAL");
                        bills.setBillStatus(BillStatus.PAID.getLabel());
                        bills.setEntryType(DebitCredit.CREDIT_BAL.getLabel());
                    } else if (arrears == 0) {
                        log.info("bill amount is equal to bill amount paid, no arrears "
                                + "set bill status=PAID & entry type=NONE");
                        bills.setBillStatus(BillStatus.PAID.getLabel());
                        bills.setEntryType(DebitCredit.NONE.getLabel());
                    }
                    bills.setLastModifiedBy(request.getCreatedBy());
                    bills.setLastModifiedDate(new Date());
                }
                bills.setBillMonth(billingMonth.toUpperCase());
                bills.setDeleted(false);
                bills.setLastDrDate(new Date());
                bills.setLastDrRecordedBy(request.getCreatedBy());
                bills.setBillYear(ledgerYear);
                bills.setBillType(PaymentType.HOUSE_RENT.getLabel());
                bills.setBillPenaltyAmount(0.0);
                bills.setOccupant(eachOne.getOccupant());
                bills.setEstateProperty(eachOne.getEstateProperty());
                basicServices.update(bills);
                propertyLedger.setBill(bills);
                basicServices.update(propertyLedger);
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage("Applying batch rental bills for " + listOfOccupantProperties.size()
                    + " occupants for " + billingMonth + "," + ledgerYear + " completed");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateGroundRentBill IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse processBackRentalbill(RentalBackBillRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            log.info("about to validateRentalBillRequest");
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            headerResponse = RentalBillValidator.validateBackRentalBillRequest(request);
            AppLogger.printPayload(log, "validateRentalBillRequest response ", headerResponse);
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
                msg = ResponseCodes.NO_PROPERTY_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            log.info("About to applyBackRentalbill ");
            DataFetchType dataFetchType = new DataFetchType(request);
            dataFetchType.setOccupationType(OccupationType.RENTAL.getLabel());
            AppLogger.printPayload(log, "dataFetchType for rentalBackBillRequest ", dataFetchType);
            List<OccupantProperty> listOfOccupantProperties = new ArrayList<>();
            log.info("Getting all rental occupant properties");
            listOfOccupantProperties = utitlityServices.getOccupantProperties4Billing(dataFetchType);
            log.info("Total records  occupant properties found : " + listOfOccupantProperties.size());
            if (null == listOfOccupantProperties) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                AppLogger.printPayload(log, "", response);
                return response;
            }
            if (listOfOccupantProperties.isEmpty()) {
                msg = "No properties occupied as Rental;please assisgn properties to occupants";
                log.info(msg);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            Date startDate = AdvantageDateUtils.getFirstDayDateFromMonthAndYear(AdvantageDateUtils.getMonthNumberFromMonthName(request.getStartMonth()), request.getStartYear());
            Date endDate = AdvantageDateUtils.getFirstDayDateFromMonthAndYear(AdvantageDateUtils.getMonthNumberFromMonthName(request.getEndMonth()), request.getEndYear());
            log.info("bill startDate " + startDate);
            log.info("bill endDate " + endDate);
            int months = AdvantageDateUtils.getMonthDifference(startDate, endDate) + 1;
            log.info("number of months for billing " + months);
            log.info("Run through each occupant property get last rental billed amount and creat a debit ledger");
            ArrayList<BillMonth> billMonths = DateTimeUtils.getMonthsBetweenDates(startDate, endDate);
            AppLogger.printPayload(log, "billMonths:: ", billMonths);
            for (OccupantProperty eachOne : listOfOccupantProperties) {
                for (BillMonth eachMonthBill : billMonths) {
                    int ledgerYear = eachMonthBill.getYear();
                    String monthName = eachMonthBill.getMonthName();
                    PropertyLedger propertyLedger = new PropertyLedger();
                    propertyLedger.setEstateProperty(eachOne.getEstateProperty());
                    propertyLedger.setLedgerYear(ledgerYear);
                    propertyLedger.setamountPaid(request.getRentAmount());
                    propertyLedger.setDateOfRecordEntry(new Date());
                    propertyLedger.setDateOfRecordTransaction(eachMonthBill.getFirstDay());
                    propertyLedger.setDebitCreditEntry(DebitCredit.DEBIT.getLabel());
                    propertyLedger.setPaymentType(PaymentType.HOUSE_RENT.getLabel());
                    propertyLedger.setOccupant(eachOne.getOccupant());
                    propertyLedger.setPayeeName(AdConstants.SHC);
                    propertyLedger.setMediumOfPayment(AdConstants.NONE);
                    propertyLedger.setMediumOfPaymentNumber(AdConstants.NONE);
                    propertyLedger.setPaymentFor("Property rent ," + MsgFormatter.sentenceCase(monthName) + " " + ledgerYear);
//                  propertyLedger.setPaymentFor(MsgFormatter.sentenceCase("Property Rent ," + monthName + " " + ledgerYear));

                    String id = IdGeneratorServices.generateEstateLedgerId(propertyLedger, monthName);
                    propertyLedger.setRecordId(id);
                    log.info(propertyLedger + "\t");
                    List<PropertyLedger> propertyLedgers = basicServices.searchRecordsStrict(PropertyLedger.class, EntityFields.recordId, propertyLedger.getRecordId());
                    if (null == propertyLedgers) {
                        log.error("Error finding property ledger " + eachOne.getRecordId() + " skip");
                        continue;
                    }
                    if (propertyLedgers.isEmpty()) {
                        log.info("First time rent bill for " + monthName + "," + ledgerYear);
                        propertyLedger.setCreatedBy(request.getCreatedBy());
                        propertyLedger.setCreatedDate(new Date());
                    } else {
                        PropertyLedger ledgerAlreadyExist = propertyLedgers.get(0);
                        log.info("Updated existing rent bill for " + monthName + "," + ledgerYear);
                        propertyLedger.setCreatedBy(ledgerAlreadyExist.getCreatedBy());
                        propertyLedger.setCreatedDate(ledgerAlreadyExist.getCreatedDate());
                        propertyLedger.setLastModifiedDate(new Date());
                        propertyLedger.setLastModifiedBy(request.getCreatedBy());
                    }

                    String recordId = propertyLedger.getRecordId();
                    Bills bills = new Bills();
                    bills = basicServices.find(Bills.class, recordId);
                    log.info(" record id " + recordId);

                    if (Objects.isNull(bills)) {
                        log.info("Bill not found;first time billing");
                        log.info(" record id " + recordId);
                        bills = new Bills();
                        bills.setRecordId(recordId);
                        bills.setCreatedBy(request.getCreatedBy());
                        bills.setCreatedDate(new Date());

                        bills.setBillStatus(BillStatus.UNPAID.getLabel());
                        bills.setEntryType(DebitCredit.DEBIT.getLabel());
                        bills.setBillAmountPaid(0.0);
                        bills.setBillAmount(request.getRentAmount());

                    } else {
                        log.info("Bill found;existing billing");
                        bills.setBillAmount(request.getRentAmount());
                        Double arrears = bills.getBillAmount() - bills.getBillAmountPaid();
                        if (arrears > 0 && bills.getBillAmountPaid() > 0) {
                            log.info("some payment has been made before but "
                                    + "there is still arrears, set bill status=PART PAYMENT & entry type=DEBIT BAL");
                            bills.setBillStatus(BillStatus.PART_PAYMENT.getLabel());
                            bills.setEntryType(DebitCredit.DEBIT_BAL.getLabel());
                        } else if (arrears < 0) {
                            log.info("there is more payment than arrears "
                                    + "set bill status=PAID & entry type=CREDIT BAL");
                            bills.setBillStatus(BillStatus.PAID.getLabel());
                            bills.setEntryType(DebitCredit.CREDIT_BAL.getLabel());
                        } else if (arrears == 0) {
                            log.info("bill amount is equal to bill amount paid, no arrears "
                                    + "set bill status=PAID & entry type=NONE");
                            bills.setBillStatus(BillStatus.PAID.getLabel());
                            bills.setEntryType(DebitCredit.NONE.getLabel());
                        }
                        bills.setLastModifiedBy(request.getCreatedBy());
                        bills.setLastModifiedDate(new Date());
                    }
                    bills.setBillMonth(eachMonthBill.getMonthName().toUpperCase());
                    bills.setDeleted(false);
                    bills.setLastDrDate(new Date());
                    bills.setLastDrRecordedBy(request.getCreatedBy());
                    bills.setBillYear(ledgerYear);
                    bills.setBillType(PaymentType.HOUSE_RENT.getLabel());
                    bills.setBillPenaltyAmount(0.0);
                    bills.setOccupant(eachOne.getOccupant());
                    bills.setEstateProperty(eachOne.getEstateProperty());
                    basicServices.update(bills);
                    propertyLedger.setBill(bills);
                    basicServices.update(propertyLedger);
                }
            }

//            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage("Applying back rental bills for " + listOfOccupantProperties.size()
                    + " occupants between " + request.getStartMonth() + "," + request.getStartYear() + " and "
                    + request.getEndMonth() + "," + request.getEndYear() + " completed");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateGroundRentBill IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse processBackRentalbillOld(RentalBackBillRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            log.info("about to validateRentalBillRequest");
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            headerResponse = RentalBillValidator.validateBackRentalBillRequest(request);
            AppLogger.printPayload(log, "validateRentalBillRequest response ", headerResponse);
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
                msg = ResponseCodes.NO_PROPERTY_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            log.info("About to applyBackRentalbill ");
            DataFetchType dataFetchType = new DataFetchType(request);
            dataFetchType.setOccupationType(OccupationType.RENTAL.getLabel());
            AppLogger.printPayload(log, "dataFetchType for rentalBackBillRequest ", dataFetchType);
            List<OccupantProperty> listOfOccupantProperties = new ArrayList<>();
            log.info("Getting all rental occupant properties");
            listOfOccupantProperties = utitlityServices.getOccupantProperties4Billing(dataFetchType);
            log.info("Total records  occupant properties found : " + listOfOccupantProperties.size());
            if (null == listOfOccupantProperties) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                AppLogger.printPayload(log, "", response);
                return response;
            }
            if (listOfOccupantProperties.isEmpty()) {
                msg = "No properties occupied as Rental;please assisgn properties to occupants";
                log.info(msg);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            Date startDate = AdvantageDateUtils.getFirstDayDateFromMonthAndYear(AdvantageDateUtils.getMonthNumberFromMonthName(request.getStartMonth()), request.getStartYear());
            Date endDate = AdvantageDateUtils.getFirstDayDateFromMonthAndYear(AdvantageDateUtils.getMonthNumberFromMonthName(request.getEndMonth()), request.getEndYear());
            log.info("bill startDate " + startDate);
            log.info("bill endDate " + endDate);
            int months = AdvantageDateUtils.getMonthDifference(startDate, endDate) + 1;
//            DateTimeUtils.g
            log.info("number of months for billing " + months);
            log.info("Run through each occupant property get last rental billed amount and creat a debit ledger");
            for (OccupantProperty eachOne : listOfOccupantProperties) {

                for (int eachMonth = 1; eachMonth <= months; eachMonth++) {

                    Date dt = DateTimeUtils.addMonthsToDate(startDate, eachMonth);

                    int ledgerYear = DateTimeUtils.getYearInDate(dt);
                    String monthName = AdvantageDateUtils.getMonthNameFromMonthNumber(eachMonth);
                    PropertyLedger propertyLedger = new PropertyLedger();
                    propertyLedger.setEstateProperty(eachOne.getEstateProperty());
                    propertyLedger.setLedgerYear(ledgerYear);
                    propertyLedger.setamountPaid(request.getRentAmount());
                    propertyLedger.setDateOfRecordEntry(new Date());
                    propertyLedger.setDateOfRecordTransaction(dt);
                    propertyLedger.setDebitCreditEntry(DebitCredit.DEBIT.getLabel());
                    propertyLedger.setPaymentType(PaymentType.HOUSE_RENT.getLabel());
                    propertyLedger.setOccupant(eachOne.getOccupant());
                    propertyLedger.setPayeeName(AdConstants.SHC);
                    propertyLedger.setMediumOfPayment(AdConstants.NONE);
                    propertyLedger.setMediumOfPaymentNumber(AdConstants.NONE);
                    propertyLedger.setPaymentFor("Property rent ," + MsgFormatter.sentenceCase(monthName) + " " + ledgerYear);
//                                        propertyLedger.setPaymentFor(MsgFormatter.sentenceCase("Property Rent ," + monthName + " " + ledgerYear));

                    String id = IdGeneratorServices.generateEstateLedgerId(propertyLedger, monthName);
                    propertyLedger.setRecordId(id);
                    log.info(propertyLedger + "\t");
                    List<PropertyLedger> propertyLedgers = basicServices.searchRecordsStrict(PropertyLedger.class, EntityFields.recordId, propertyLedger.getRecordId());
                    if (null == propertyLedgers) {
                        log.error("Error finding property ledger " + eachOne.getRecordId() + " skip");
                        continue;
                    }
                    if (propertyLedgers.isEmpty()) {
                        log.info("First time rent bill for " + monthName + "," + ledgerYear);
                        propertyLedger.setCreatedBy(request.getCreatedBy());
                        propertyLedger.setCreatedDate(new Date());
                    } else {
                        PropertyLedger ledgerAlreadyExist = propertyLedgers.get(0);
                        log.info("Updated existing rent bill for " + monthName + "," + ledgerYear);
                        propertyLedger.setCreatedBy(ledgerAlreadyExist.getCreatedBy());
                        propertyLedger.setCreatedDate(ledgerAlreadyExist.getCreatedDate());
                        propertyLedger.setLastModifiedDate(new Date());
                        propertyLedger.setLastModifiedBy(request.getCreatedBy());
                    }

                    String recordId = propertyLedger.getRecordId();
                    Bills bills = new Bills();
                    bills = basicServices.find(Bills.class, recordId);
                    log.info(" record id " + recordId);

                    if (Objects.isNull(bills)) {
                        log.info("Bill not found;first time billing");
                        log.info(" record id " + recordId);
                        bills = new Bills();
                        bills.setRecordId(recordId);
                        bills.setCreatedBy(request.getCreatedBy());
                        bills.setCreatedDate(new Date());

                        bills.setBillStatus(BillStatus.UNPAID.getLabel());
                        bills.setEntryType(DebitCredit.DEBIT.getLabel());
                        bills.setBillAmountPaid(0.0);
                        bills.setBillAmount(request.getRentAmount());

                    } else {
                        log.info("Bill found;existing billing");
                        bills.setBillAmount(request.getRentAmount());
                        Double arrears = bills.getBillAmount() - bills.getBillAmountPaid();
                        if (arrears > 0 && bills.getBillAmountPaid() > 0) {
                            log.info("some payment has been made before but "
                                    + "there is still arrears, set bill status=PART PAYMENT & entry type=DEBIT BAL");
                            bills.setBillStatus(BillStatus.PART_PAYMENT.getLabel());
                            bills.setEntryType(DebitCredit.DEBIT_BAL.getLabel());
                        } else if (arrears < 0) {
                            log.info("there is more payment than arrears "
                                    + "set bill status=PAID & entry type=CREDIT BAL");
                            bills.setBillStatus(BillStatus.PAID.getLabel());
                            bills.setEntryType(DebitCredit.CREDIT_BAL.getLabel());
                        } else if (arrears == 0) {
                            log.info("bill amount is equal to bill amount paid, no arrears "
                                    + "set bill status=PAID & entry type=NONE");
                            bills.setBillStatus(BillStatus.PAID.getLabel());
                            bills.setEntryType(DebitCredit.NONE.getLabel());
                        }
                        bills.setLastModifiedBy(request.getCreatedBy());
                        bills.setLastModifiedDate(new Date());
                    }
                    bills.setDeleted(false);
                    bills.setLastDrDate(new Date());
                    bills.setLastDrRecordedBy(request.getCreatedBy());
                    bills.setBillYear(ledgerYear);
                    bills.setBillType(PaymentType.HOUSE_RENT.getLabel());
                    bills.setBillPenaltyAmount(0.0);
                    bills.setOccupant(eachOne.getOccupant());
                    bills.setEstateProperty(eachOne.getEstateProperty());
                    basicServices.update(propertyLedger);
                    basicServices.update(bills);
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage("Applying back rental bills for " + listOfOccupantProperties.size()
                    + " occupants between " + request.getStartMonth() + "," + request.getStartYear() + " and "
                    + request.getEndMonth() + "," + request.getEndYear() + " completed");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateGroundRentBill IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GroundRentalBillListResponse getRentalBills(RentalBillListRequest request) throws IOException {
        GroundRentalBillListResponse response = new GroundRentalBillListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            String msg = "";
            if (null == request.getRentMonth()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.RENT_MONTH_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (null == request.getRentYear()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.RENT_YEAR_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.printPayload(log, "header validation response after", headerResponse);
            List<PropertyLedger> listOfGroundRentBills = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, PropertyLedger.class);
            builder.addStringQryParam(EntityFields.paymentType, PaymentType.HOUSE_RENT.getLabel(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addNumberParam(EntityFields.ledgerYear, request.getRentYear(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addStringQryParam(EntityFields.recordId, request.getRentMonth().toUpperCase(), QryBuilder.ComparismCriteria.LIKE);
//            builder.addDateRange(utitlityServices.getDateRangeFromMonthName(request.getRentMonth()), EntityFields.dateOfRecordTransaction);
            builder.addObjectParam(EntityFields.deleted, false);
            builder.orderByAsc(EntityFields._occupantName);
            builder.orderByDesc(EntityFields.createdDate);

            log.info(" getOccupantProperties " + builder.getQryInfo());
            listOfGroundRentBills = builder.buildQry().getResultList();
            if (null == listOfGroundRentBills) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            log.info("total staff retrieved " + listOfGroundRentBills.size());
            List<PropertyLedgerInfo> ledgers = new ArrayList<>();
            if (!listOfGroundRentBills.isEmpty()) {
                for (PropertyLedger eachOne : listOfGroundRentBills) {
                    ledgers.add(new PropertyLedgerInfo(eachOne));
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setPropertyLedgers(ledgers);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

}
