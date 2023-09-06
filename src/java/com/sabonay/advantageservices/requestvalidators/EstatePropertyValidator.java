package com.sabonay.advantageservices.requestvalidators;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstatePropertyRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Fri 04 Aug, 2023 10:13 am
 */
public class EstatePropertyValidator {

    private static final Logger log = Logger.getLogger(EstatePropertyValidator.class.getName());

    public static EstateProperty validateEstatePropertyCommonFields(EstatePropertyRequest request) throws IOException {
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        EstateProperty estateProperty = new EstateProperty();
        try {

            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                estateProperty.setResponseCode(ResponseCodes.FAILED);
                estateProperty.setResponseMsg(msg);
                return estateProperty;
            }
            if (null == request.getRecordId() || StringUtils.isEmpty(request.getRecordId())) {
                msg = ResponseCodes.ESTATE_PROPERTY_ID_REQUIRED;
                estateProperty.setResponseCode(ResponseCodes.FAILED);
                estateProperty.setResponseMsg(msg);
                return estateProperty;
            }
            if (null == request.getPropertyName() || StringUtils.isEmpty(request.getPropertyName())) {
                msg = ResponseCodes.BLOCK_NAME_REQUIRED;
                estateProperty.setResponseCode(ResponseCodes.FAILED);
                estateProperty.setResponseMsg(msg);
                return estateProperty;
            }
            if (null == request.getBlockId() || StringUtils.isEmpty(request.getBlockId())) {
                msg = ResponseCodes.ESTATE_BLOCK_ID_REQUIRED;
                estateProperty.setResponseCode(ResponseCodes.FAILED);
                estateProperty.setResponseMsg(msg);
                return estateProperty;
            }
            if (null == request.getCategory() || StringUtils.isEmpty(request.getCategory())) {
                msg = ResponseCodes.PROPERTY_CATEGORY_REQUIRED;
                estateProperty.setResponseCode(ResponseCodes.FAILED);
                estateProperty.setResponseMsg(msg);
                return estateProperty;
            }
            if (null == request.getPropertySize() || request.getPropertySize() <= 0) {
                msg = ResponseCodes.LAND_SIZE_REQUIRED;
                estateProperty.setResponseCode(ResponseCodes.FAILED);
                estateProperty.setResponseMsg(msg);
                return estateProperty;
            }
            if (null == request.getPropertyNo() || StringUtils.isEmpty(request.getPropertyNo())) {
                msg = ResponseCodes.PROPERTY_NO_REQUIRED;
                estateProperty.setResponseCode(ResponseCodes.FAILED);
                estateProperty.setResponseMsg(msg);
                return estateProperty;
            }
            if (null == request.getUsage() || StringUtils.isEmpty(request.getUsage())) {
                msg = ResponseCodes.PROPERTY_USAGE_REQUIRED;
                estateProperty.setResponseCode(ResponseCodes.FAILED);
                estateProperty.setResponseMsg(msg);
                return estateProperty;
            }
            log.info("validateEstatePropertyCommonFields passed validation;set commons fields to EstateBlock entity");

            estateProperty.setPropertyCategory(request.getCategory());
            estateProperty.setPropertyName(request.getPropertyName());
            estateProperty.setPropertyNumber(request.getPropertyNo());
            estateProperty.setPropertyLandSize(request.getPropertySize());
            estateProperty.setBlocked(request.isBlocked());
            estateProperty.setPropertyUsage(request.getUsage());
            AppLogger.printPayload(log, "After validation ", estateProperty);
            msg = ResponseCodes.SUCCESS;
            estateProperty.setResponseCode(ResponseCodes.SUCCESS);
            estateProperty.setResponseMsg(msg);
            return estateProperty;
        } catch (IOException e) {
            AppLogger.error(log, e, "validateRolerUsers exception error");
            msg = ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST);
            estateProperty.setResponseCode(ResponseCodes.FAILED);
            estateProperty.setResponseMsg(msg);
            return estateProperty;
        }
    }
}
