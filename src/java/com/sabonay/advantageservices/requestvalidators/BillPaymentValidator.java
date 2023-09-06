package com.sabonay.advantageservices.requestvalidators;

import com.ctrloption.utils.MsgFormatter;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.restmodels.billpayment.BillPaymentRequest;
import com.sabonay.advantageservices.restmodels.billpayment.PropertyLedgerEntriesRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Thu 24 Aug, 2023 05:22 am
 */
public class BillPaymentValidator {

    private static final Logger log = Logger.getLogger(BillPaymentValidator.class.getName());

    public static HeaderResponse validateBillPaymentRequest(BillPaymentRequest request) throws IOException {
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getAmountInvolved()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.AMOUNT_REQUEIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }

            if (null == request.getDateOfTransaction()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.PAYMENT_DATE_REQUIRED);
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

            if (null == request.getPropertyId()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.PROPERTY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }

            if (null == request.getOccupantId()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.OCCUPANT_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            log.info("validateBillPaymentRequest passed validation;set commons fields to BillPayment entity");
            AppLogger.printPayload(log, "After validation ", headerResponse);
            msg = ResponseCodes.SUCCESS;
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(msg);
            return headerResponse;
        } catch (IOException e) {
            AppLogger.error(log, e, "validateBillPaymentRequest exception error");
            msg = ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST);
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(msg);
            return headerResponse;
        }
    }

    public static HeaderResponse validatePropetyLedgerEntriesRequest(PropertyLedgerEntriesRequest request) throws IOException {
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }
            if (null == request.getEndDate()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.END_DATE_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }

            if (!request.getHeaderRequest().getRequestType().equalsIgnoreCase("BILL_PAYMENTS")) {
                if (null == request.getPropertyId()) {
                    msg = MsgFormatter.sentenceCase(ResponseCodes.PROPERTY_REQUIRED);
                    headerResponse.setResponseCode(ResponseCodes.FAILED);
                    headerResponse.setResponseMessage(msg);
                    return headerResponse;
                }
                if (null == request.getOccupantId()) {
                    msg = MsgFormatter.sentenceCase(ResponseCodes.OCCUPANT_REQUIRED);
                    headerResponse.setResponseCode(ResponseCodes.FAILED);
                    headerResponse.setResponseMessage(msg);
                    return headerResponse;
                }
            }

            if (!request.getHeaderRequest().getRequestType().equalsIgnoreCase("BILL_PAYMENTS")
                    && !request.getSearchBy().equalsIgnoreCase("BAB")) {
                if (null == request.getSearchValue()) {
                    msg = MsgFormatter.sentenceCase(ResponseCodes.SEARCH_VALUE_REQUIRED);
                    headerResponse.setResponseCode(ResponseCodes.FAILED);
                    headerResponse.setResponseMessage(msg);
                    return headerResponse;
                }
            }

            if (null == request.getSearchBy()) {
                msg = MsgFormatter.sentenceCase(ResponseCodes.SEARCH_BY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                return headerResponse;
            }

            log.info("validatePropetyLedgerEntriesRequest passed validation;set commons fields to BillPayment entity");
            AppLogger.printPayload(log, "After validation ", headerResponse);
            msg = ResponseCodes.SUCCESS;
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(msg);
            return headerResponse;
        } catch (IOException e) {
            AppLogger.error(log, e, "validatePropetyLedgerEntriesRequest exception error");
            msg = ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST);
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(msg);
            return headerResponse;
        }
    }

}
