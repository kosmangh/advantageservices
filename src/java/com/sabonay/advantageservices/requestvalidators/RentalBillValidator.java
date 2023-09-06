package com.sabonay.advantageservices.requestvalidators;

import com.ctrloption.utils.MsgFormatter;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatebilling.RentalBackBillRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.RentalBillRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 19 Aug, 2023 04:56 am
 */
public class RentalBillValidator {

    private static final Logger log = Logger.getLogger(RentalBillValidator.class.getName());

    public static HeaderResponse validateRentalBillRequest(RentalBillRequest request) throws IOException {
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
//        RentalBill headerResponse = new RentalBill();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getBillingType() || StringUtils.isEmpty(request.getBillingType())) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.BILLING_TYPE_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }

//            if (null == request.getBlockId() || StringUtils.isEmpty(request.getBlockId())) {
//                msg = MsgFormatter.sentenceCase(ResponseCodes.ESTATE_BLOCK_REQUIRED);
//                headerResponse.setResponseCode(ResponseCodes.FAILED);
//                headerResponse.setResponseMessage(msg);
//                return headerResponse;
//            }
            if (null == request.getBillDate()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.BILL_DATE_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getCreatedBy()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.CREATED_BY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
//            if (null == request.getEstateId()) {
//                msg = MsgFormatter.sentenceCase(ResponseCodes.ESTATE_REQUIRED);
//                headerResponse.setResponseCode(ResponseCodes.FAILED);
//                headerResponse.setResponseMessage(msg);
//                return headerResponse;
//            }
            if (null == request.getPropertyId()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.PROPERTY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }

            log.info("validateRentalBillCommonFields passed validation;set commons fields to RentalBill entity");
            AppLogger.printPayload(log, "After validation ", headerResponse);
            msg = ResponseCodes.SUCCESS;
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(msg);
            return headerResponse;
        } catch (IOException e) {
            AppLogger.error(log, e, "validateRolerUsers exception error");
            msg = ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST);
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(msg);
            return headerResponse;
        }
    }

    public static HeaderResponse validateBackRentalBillRequest(RentalBackBillRequest request) throws IOException {
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
//        RentalBill headerResponse = new RentalBill();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getBillingType() || StringUtils.isEmpty(request.getBillingType())) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.BILLING_TYPE_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getPropertyId()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.PROPERTY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getEstateId()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.ESTATE_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getBlockId() || StringUtils.isEmpty(request.getBlockId())) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.ESTATE_BLOCK_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getStartMonth()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.START_MONTH_REQQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getStartYear()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.START_YEAR_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getEndMonth()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.END_MONTH_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getEndYear()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.END_YEAH_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getCreatedBy()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.CREATED_BY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }

            log.info("validateRentalBillCommonFields passed validation;set commons fields to RentalBill entity");
            AppLogger.printPayload(log, "After validation ", headerResponse);
            msg = ResponseCodes.SUCCESS;
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(msg);
            return headerResponse;
        } catch (IOException e) {
            AppLogger.error(log, e, "validateRolerUsers exception error");
            msg = ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST);
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(msg);
            return headerResponse;
        }
    }
}
