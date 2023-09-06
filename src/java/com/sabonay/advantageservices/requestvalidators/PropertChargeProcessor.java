package com.sabonay.advantageservices.requestvalidators;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.estatebilling.PropertyCharge;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatebilling.PropertyChargeRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 12 Aug, 2023 11:06 am
 */
public class PropertChargeProcessor {

    private static final Logger log = Logger.getLogger(PropertChargeProcessor.class.getName());

    public static PropertyCharge validatePropertyChargeCommonFields(PropertyChargeRequest request) throws IOException {
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        PropertyCharge propertyCharge = new PropertyCharge();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                propertyCharge.setResponseCode(ResponseCodes.FAILED);
                propertyCharge.setResponseMsg(msg);
                return propertyCharge;
            }
            if (null == request.getRegionId() || StringUtils.isEmpty(request.getRegionId())) {
                msg = ResponseCodes.REGION_REQUIRED;
                propertyCharge.setResponseCode(ResponseCodes.FAILED);
                propertyCharge.setResponseMsg(msg);
                return propertyCharge;
            }
            if (null == request.getPropertyUsage() || StringUtils.isEmpty(request.getPropertyUsage())) {
                msg = ResponseCodes.PROPERTY_USAGE_REQUIRED;
                propertyCharge.setResponseCode(ResponseCodes.FAILED);
                propertyCharge.setResponseMsg(msg);
                return propertyCharge;
            }
            if (null == request.getChargeYear()) {
                msg = ResponseCodes.RENT_YEAR_REQUIRED;
                propertyCharge.setResponseCode(ResponseCodes.FAILED);
                propertyCharge.setResponseMsg(msg);
                return propertyCharge;
            }
            if (null == request.getFirstClassCharge()) {
                msg = ResponseCodes.FIRST_CLASS_CHARGE_REQUIRED;
                propertyCharge.setResponseCode(ResponseCodes.FAILED);
                propertyCharge.setResponseMsg(msg);
                return propertyCharge;
            }
            if (null == request.getSecondClassCharge()) {
                msg = ResponseCodes.SECOND_CLASS_CHARGE_REQUIRED;
                propertyCharge.setResponseCode(ResponseCodes.FAILED);
                propertyCharge.setResponseMsg(msg);
                return propertyCharge;
            }
            if (null == request.getThirdClassCharge()) {
                msg = ResponseCodes.THIRD_CLASS_CHARGE_REQUIRED;
                propertyCharge.setResponseCode(ResponseCodes.FAILED);
                propertyCharge.setResponseMsg(msg);
                return propertyCharge;
            }

            log.info("validatePropertyChargeCommonFields passed validation;set commons fields to PropertyCharge entity");
            propertyCharge.setChargeYear(request.getChargeYear());
            propertyCharge.setFirstClassCharge(request.getFirstClassCharge());
            propertyCharge.setSecondClassCharge(request.getSecondClassCharge());
            propertyCharge.setThirdClassCharge(request.getThirdClassCharge());
            propertyCharge.setPropertyUsage(request.getPropertyUsage());
            AppLogger.printPayload(log, "After validation ", propertyCharge);
            msg = ResponseCodes.SUCCESS;
            propertyCharge.setResponseCode(ResponseCodes.SUCCESS);
            propertyCharge.setResponseMsg(msg);
            return propertyCharge;
        } catch (IOException e) {
            AppLogger.error(log, e, "validateRolerUsers exception error");
            msg = ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST);
            propertyCharge.setResponseCode(ResponseCodes.FAILED);
            propertyCharge.setResponseMsg(msg);
            return propertyCharge;
        }
    }
}
