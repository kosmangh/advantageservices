package com.sabonay.advantageservices.services;

import com.ctrloption.constants.DebitCredit;
import com.ctrloption.formating.DateTimeUtils;
import com.ctrloption.formating.NumberFormattingUtils;
import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.ctrloption.utils.MsgFormatter;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
import com.sabonay.advantageservices.entities.estatesetup.Estate;
import com.sabonay.advantageservices.entities.occupancy.OccupantProperty;
import com.sabonay.advantageservices.models.DataFetchType;
import com.sabonay.advantageservices.models.estatebilling.PropertyLedgerInfo;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatebilling.GroundRentBillListRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.GroundRentBillRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.GroundRentalBillListResponse;
import com.sabonay.advantageservices.utils.AdConstants;
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
 * @date Tue 15 Aug, 2023 03:39 am
 */
@Stateless
public class GroundRentBillServices extends CrudController implements Serializable {

    private static final Logger log = Logger.getLogger(GroundRentBillServices.class.getName());
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

    public GenericResponse processGroundRentBill(GroundRentBillRequest request) throws IOException {
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
            log.info("about to validateGroundRentBillCommonFields");
//            headerResponse = GroundRentBillValidator.validateGroundRentBillCommonFields(request);

            AppLogger.printPayload(log, "validateGroundRentBillCommonFields response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            Estate estate = basicServices.find(Estate.class, request.getEstateId());
            if (estate == null) {
                msg = ResponseCodes.NO_ESTATE_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            if (request.getChargeYear() > DateTimeUtils.getCurrentYear()) {
                msg = "You cannot apply Ground Rent for years below " + (DateTimeUtils.getCurrentYear() - 1);
                log.info(msg);
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            DataFetchType dataFetchType = new DataFetchType(request);
            dataFetchType.setOccupationType(OccupationType.LEASEHOLD.getLabel());
            AppLogger.printPayload(log, "dataFetchType for groundRentBillRequest ", dataFetchType);
            List<OccupantProperty> listOfOccupantProperties = utitlityServices.getOccupantProperties4Billing(dataFetchType);
            log.info("Total records for occupant for processGroundRentBill " + listOfOccupantProperties.size());
            if (null == listOfOccupantProperties) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                AppLogger.printPayload(log, "", response);
                return response;
            }
            if (listOfOccupantProperties.isEmpty()) {
                msg = "No occupant properties found for LEASEHOLD ";
                log.info(msg);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
            calendar.set(Calendar.YEAR, request.getChargeYear());
            double amtToBeCharge = 0.0;
            for (OccupantProperty eachOne : listOfOccupantProperties) {
                amtToBeCharge = NumberFormattingUtils.formatDecimalNumberTo_2(utitlityServices.getGroundRentBill(eachOne.getEstateProperty(), request.getChargeYear()));
                PropertyLedger propertyLedger = new PropertyLedger();
                propertyLedger.setEstateProperty(eachOne.getEstateProperty());
                propertyLedger.setLedgerYear(request.getChargeYear());
                propertyLedger.setAmountInvolved(amtToBeCharge);
                propertyLedger.setDateOfRecordEntry(new Date());
                propertyLedger.setDateOfRecordTransaction(calendar.getTime());
                propertyLedger.setDebitCreditEntry(DebitCredit.DEBIT.getLabel());
                propertyLedger.setPaymentType(PaymentType.GROUND_RENT.getLabel());
                propertyLedger.setOccupant(eachOne.getOccupant());
                propertyLedger.setPayeeName(AdConstants.SHC);
                propertyLedger.setMediumOfPayment(AdConstants.NONE);
                propertyLedger.setMediumOfPaymentNumber(AdConstants.NONE);
                propertyLedger.setPaymentFor("Ground Rent Bill, " + request.getChargeYear());
                log.info(propertyLedger + "\t");
                propertyLedger.setCreatedBy(request.getCreatedBy());
                propertyLedger.setCreatedDate(new Date());
                propertyLedger.setLastModifiedDate(new Date());
                propertyLedger.setLastModifiedBy(request.getCreatedBy());
                IdGeneratorServices.setEstateLedgerId(propertyLedger, null);

                basicServices.update(propertyLedger);
            }

            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage("Applying Ground Rent For "
                    + estate.getEstateName() + " for "
                    + request.getChargeYear() + " completed");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateGroundRentBill IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GroundRentalBillListResponse getGroundRentBills(GroundRentBillListRequest request) throws IOException {
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
            if (null == request.getBlockId()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.ESTATE_BLOCK_REQUIRED);
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

            AppLogger.printPayload(log, "header validation response after", headerResponse);
            List<PropertyLedger> listOfGroundRentBills = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, PropertyLedger.class);
            builder.addStringQryParam("estateProperty.estateBlock.recordId", request.getBlockId(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addStringQryParam("debitCreditEntry", DebitCredit.DEBIT.getLabel(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addStringQryParam(EntityFields.paymentType, PaymentType.GROUND_RENT.getLabel(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addNumberParam(EntityFields.ledgerYear, request.getChargeYear(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addObjectParam(EntityFields.deleted, false);
            builder.orderByAsc("occupant.occupantName");
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
