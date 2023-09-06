package com.sabonay.advantageservices.requestvalidators;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author dainoo
 */
public class HeaderValidator {

    private static final Logger log = Logger.getLogger(HeaderValidator.class.getName());

    public static HeaderResponse validateHeaderRequest(HeaderRequest request) throws IOException {
        log.info("validateHeader validate begins ");
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "validateHeader response";
        String lang = null;
        try {
            request.setRegion("EN");
            headerResponse.setZone(request.getZone());
            headerResponse.setRequestId(request.getRequestId());
            headerResponse.setSourceCode(request.getSourceCode());
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.FAILED));
            request.setRegion("EN");
            if (null == request.getRegion() || request.getRegion().isEmpty()) {
                msg = ResponseCodes.getAppMsg(msg, "");
                headerResponse.setResponseCode(ResponseCodes.LANG_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                return headerResponse;
            }

            lang = request.getRegion();
            log.info("lang " + lang);

//            if (StringUtils.isEmpty(request.getCompany())) {
//                msg = ResponseCodes.getAppMsg(ResponseCodes.COMPANY_REQUIRED);
//                headerResponse.setResponseCode(ResponseCodes.FAILED);
//                headerResponse.setResponseMessage(msg);
//                AppLogger.printPayload(log, msg, headerResponse);
//                return headerResponse;
//            }
            if (StringUtils.isEmpty(request.getRequestId())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.REQUEST_ID_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.printPayload(log, msg, headerResponse);
                return headerResponse;
            }
            if (StringUtils.isEmpty(request.getSourceCode())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.SOURCE_CODE_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.printPayload(log, msg, headerResponse);
                return headerResponse;
            }
            if (StringUtils.isEmpty(request.getRequestType())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.REQUIRED_TYPE_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.printPayload(log, msg, headerResponse);
                return headerResponse;
            }
            msg = ResponseCodes.getAppMsg(ResponseCodes.SUCCESS);
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(msg);
            AppLogger.printPayload(log, " header validation status " + msg, headerResponse);
            log.info("validateHeader validate ends");
            return headerResponse;
        } catch (Exception e) {
            AppLogger.error(log, e, "validateHeader exception error");
            headerResponse = AppUtils.getErrorHeaderResponse(request);
            return headerResponse;
        }
    }

}
