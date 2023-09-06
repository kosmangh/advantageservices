package com.sabonay.advantageservices.requestvalidators;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company CtrlOpton
 * @Contact 0245 293945
 * @Website ctrlOpton.com
 * @date Wed 15 Mar, 2023
 * @time 01.35.33 am
 */
public class DeleteDataValidator {

    private static final Logger log = Logger.getLogger(DeleteDataValidator.class.getName());

    public static HeaderResponse validateDeleteRequest(GenericDeleteRequest request) throws IOException {

        log.info("DeleteRequest validation begins ");
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                AppLogger.printPayload(log, msg, headerResponse);
                return headerResponse;
            }
            if (StringUtils.isEmpty(request.getRecordId())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.RECORD_ID_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.RECORD_ID_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.printPayload(log, msg, headerResponse);
                return headerResponse;
            }
            if (StringUtils.isEmpty(request.getDeletedBy())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.DELETED_BY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.DELETED_BY_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.printPayload(log, msg, headerResponse);
                return headerResponse;
            }
            msg = ResponseCodes.getAppMsg(ResponseCodes.SUCCESS);
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(msg);
            AppLogger.printPayload(log, " CreateUserRequest validation status " + msg, headerResponse);
            log.info("CreateUserRequest validation ends");
            return headerResponse;
        } catch (IOException e) {
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.FAILED));
            AppLogger.error(log, e, "validateRolerUsers exception error");
            return headerResponse;
        }
    }
}
