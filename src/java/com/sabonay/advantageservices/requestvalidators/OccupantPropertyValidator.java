package com.sabonay.advantageservices.requestvalidators;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.occupancy.OccupantProperty;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantPropertyRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 06 Aug, 2023 16:46 pm
 */
public class OccupantPropertyValidator {

    private static final Logger log = Logger.getLogger(OccupantPropertyValidator.class.getName());

    public static OccupantProperty validateOccupantPropertyCommonFields(OccupantPropertyRequest request) throws IOException {
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        OccupantProperty occupantProperty = new OccupantProperty();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                occupantProperty.setResponseCode(ResponseCodes.FAILED);
                occupantProperty.setResponseMsg(msg);
                return occupantProperty;
            }

            if (null == request.getOccupantId()) {
                msg = ResponseCodes.OCCUPANT_NAME_REQUIRED;
                occupantProperty.setResponseCode(ResponseCodes.FAILED);
                occupantProperty.setResponseMsg(msg);
                return occupantProperty;
            }
            if (null == request.getFirstDateOfOccupancy()) {
                msg = ResponseCodes.DATE_OF_BIRTH_REQUIRED;
                occupantProperty.setResponseCode(ResponseCodes.FAILED);
                occupantProperty.setResponseMsg(msg);
                return occupantProperty;
            }
            if (null == request.getLastDateOfOccupancy()) {
                msg = ResponseCodes.DATE_OF_BIRTH_REQUIRED;
                occupantProperty.setResponseCode(ResponseCodes.FAILED);
                occupantProperty.setResponseMsg(msg);
                return occupantProperty;
            }

            if ((null == request.getOccupationType() || StringUtils.isEmpty(request.getOccupationType()))) {
                msg = ResponseCodes.SUPERVISOR_EMAIL_REQUIRED;
                occupantProperty.setResponseCode(ResponseCodes.FAILED);
                occupantProperty.setResponseMsg(msg);
                return occupantProperty;
            }
            if ((null == request.getPropertyId() || StringUtils.isEmpty(request.getPropertyId()))) {
                msg = ResponseCodes.SUPERVISOR_MOBILE_NO_REQUIRED;
                occupantProperty.setResponseCode(ResponseCodes.FAILED);
                occupantProperty.setResponseMsg(msg);
                return occupantProperty;
            }

            log.info("request.getFirstDateOfOccupancy()" + request.getFirstDateOfOccupancy());
            log.info("validateOccupantPropertyCommonFields passed validation;set commons fields to OccupantProperty entity");
            occupantProperty.setFirstDateOfOccupancy(request.getFirstDateOfOccupancy());
            occupantProperty.setLastDateOfOccupancy(request.getLastDateOfOccupancy());
            occupantProperty.setOccupationType(request.getOccupationType());
            AppLogger.printPayload(log, "After validation ", occupantProperty);
            msg = ResponseCodes.SUCCESS;
            occupantProperty.setResponseCode(ResponseCodes.SUCCESS);
            occupantProperty.setResponseMsg(msg);
            return occupantProperty;
        } catch (IOException e) {
            AppLogger.error(log, e, "validateRolerUsers exception error");
            msg = ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST);
            occupantProperty.setResponseCode(ResponseCodes.FAILED);
            occupantProperty.setResponseMsg(msg);
            return occupantProperty;
        }
    }
}
