package com.sabonay.advantageservices.requestvalidators;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.estatesetup.Estate;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstateRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 30 Jul, 2023 14:42 pm
 */
public class EstateValidator {

    private static final Logger log = Logger.getLogger(EstateValidator.class.getName());

    public static Estate validateEstateCommonFields(EstateRequest request) throws IOException {
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        Estate estate = new Estate();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                estate.setResponseCode(ResponseCodes.FAILED);
                estate.setResponseMsg(msg);
                return estate;
            }
            if (null == request.getRecordId() || StringUtils.isEmpty(request.getRecordId())) {
                msg = ResponseCodes.ESTATE_ID_REQUIRED;
                estate.setResponseCode(ResponseCodes.FAILED);
                estate.setResponseMsg(msg);
                return estate;
            }
            if (null == request.getEstateClass() || StringUtils.isEmpty(request.getEstateClass())) {
                msg = ResponseCodes.ESTATE_CLASS_REQUIRED;
                estate.setResponseCode(ResponseCodes.FAILED);
                estate.setResponseMsg(msg);
                return estate;
            }
            if (null == request.getEstateLocation() || StringUtils.isEmpty(request.getEstateLocation())) {
                msg = ResponseCodes.ESTATE_LOCATION_REQUIRED;
                estate.setResponseCode(ResponseCodes.FAILED);
                estate.setResponseMsg(msg);
                return estate;
            }

            if (null == request.getEstateName() || StringUtils.isEmpty(request.getEstateName())) {
                msg = ResponseCodes.ESTATE_NAME_REQUIRED;
                estate.setResponseCode(ResponseCodes.FAILED);
                estate.setResponseMsg(msg);
                return estate;
            }
            if (null == request.getLeaseStartDate()) {
                msg = ResponseCodes.LEASE_START_DATE_REQUIRED;
                estate.setResponseCode(ResponseCodes.FAILED);
                estate.setResponseMsg(msg);
                return estate;
            }

            if (null == request.getLeaseEndDate()) {
                msg = ResponseCodes.LEASE_END_DATE_REQUIRED;
                estate.setResponseCode(ResponseCodes.FAILED);
                estate.setResponseMsg(msg);
                return estate;
            }

            if (null == request.getLandSize() || request.getLandSize() <= 0) {
                msg = ResponseCodes.LAND_SIZE_REQUIRED;
                estate.setResponseCode(ResponseCodes.FAILED);
                estate.setResponseMsg(msg);
                return estate;
            }

            if (null == request.getRegionId() || StringUtils.isEmpty(request.getRegionId())) {
                msg = ResponseCodes.REGION_REQUIRED;
                estate.setResponseCode(ResponseCodes.FAILED);
                estate.setResponseMsg(msg);
                return estate;
            }

            log.info("validateEstateCommonFields passed validation;set commons fields to Estate entity");

            estate.setAddDesc(request.getAdditionalInfo());
            estate.setDateInitialized(request.getLeaseStartDate());
            estate.setEstateClass(request.getEstateClass());
            estate.setEstateLocation(request.getEstateLocation());
            estate.setEstateName(request.getEstateName());
            estate.setExpirationdate(request.getLeaseEndDate());
            estate.setLandSize(request.getLandSize());
            AppLogger.printPayload(log, "After validation ", estate);
            log.info("CreateStaffrRequest validation ends");
            msg = ResponseCodes.SUCCESS;
            estate.setResponseCode(ResponseCodes.SUCCESS);
            estate.setResponseMsg(msg);
            return estate;
        } catch (IOException e) {
            AppLogger.error(log, e, "validateRolerUsers exception error");
            msg = ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST);
            estate.setResponseCode(ResponseCodes.FAILED);
            estate.setResponseMsg(msg);
            return estate;
        }
    }
}
