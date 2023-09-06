package com.sabonay.advantageservices.services;

import com.sabonay.advantageservices.entities.estatesetup.EstateZone;
import com.sabonay.advantageservices.entities.estatesetup.Region;
import com.sabonay.advantageservices.models.estatesetup.RegionInfo;
import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.RegionListResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.RegionRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.requestvalidators.DeleteDataValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Mon 03 Jul, 2023 21.22.59 pm
 */
@Stateless
public class RegionServices {

    private static final Logger log = Logger.getLogger(RegionServices.class.getName());

    @Inject
    private BasicServices basicServices;
    @Inject
    IdGeneratorServices idGeneratorJPA;

    public GenericResponse createRegion(RegionRequest request) throws IOException {
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
            AppLogger.printPayloadCompact(log, "createRegion validation response ", headerResponse);
            if (StringUtils.isEmpty(request.getRegionName())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.REGION_NAME_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.REGION_NAME_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getCreatedBy())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.CREATED_BY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.CREATED_BY_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("Passed validation,about to save region details");
            EstateZone zone = basicServices.find(EstateZone.class, request.getZoneId());
            if (null == zone) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.ZONE_NOT_FOUND);
                headerResponse.setResponseCode(ResponseCodes.ZONE_NOT_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            Region region = new Region();
            region.setRecordId(request.getRegionName().toUpperCase().replaceAll(" ", "_"));
            region.setRegionName(request.getRegionName());
            region.setRemarks(request.getRemarks());
            region.setZone(zone);
            region.setCreatedBy(request.getCreatedBy());
            AppLogger.printPayload(log, "final create region request", region);
            Region saved = basicServices.save(region);
            if (null == saved) {
                headerResponse.setResponseCode(ResponseCodes.BRANCH_CREATION_FAILED);
                headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.BRANCH_CREATION_FAILED));
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(region.getRegionName() + " created successfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createRegion IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse updateRegion(RegionRequest request) throws IOException {
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
            AppLogger.printPayloadCompact(log, "updateRegion validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getRegionName())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.REGION_NAME_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.REGION_NAME_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getZoneId())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.ZONE_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.ZONE_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            Region region = basicServices.find(Region.class, request.getRecordId());
            if (null == region) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.NO_REGION_FOUND);
                headerResponse.setResponseCode(ResponseCodes.NO_REGION_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.info(log, "found region " + region.toString() + " for update");

            EstateZone zone = basicServices.find(EstateZone.class, request.getZoneId());
            if (null == zone) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.ZONE_NOT_FOUND);
                headerResponse.setResponseCode(ResponseCodes.ZONE_NOT_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.info(log, "found zone " + region.toString() + " for update");

            log.info("Passed validation,about to update region details");
            region.setRegionName(request.getRegionName());
            region.setRemarks(request.getRemarks());
            region.setZone(zone);
            region.setLastModifiedBy(request.getLastModifiedBy());
            region.setLastModifiedDate(new Date());
            if (!basicServices.update(region)) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.REGION_UPDATE_FAILED);
                headerResponse.setResponseCode(ResponseCodes.REGION_UPDATE_FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(region.getRegionName() + " updated sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateRegion IOException");
            headerResponse.setResponseMessage(e.getMessage());
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public GenericResponse deleteRegion(GenericDeleteRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = DeleteDataValidator.validateDeleteRequest(request);
            AppLogger.printPayloadCompact(log, "updateRegion validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("about to find branch to be deleted");
            Region region = basicServices.find(Region.class, request.getRecordId());
            if (Objects.isNull(region)) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.NO_REGION_FOUND);
                headerResponse.setResponseCode(ResponseCodes.NO_REGION_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.info(log, "found zone " + region.toString() + " for update");
            log.info("Passed validation,about to false delete this branch details");
            region.setDeleted(true);
            region.setDeletedBy(request.getDeletedBy());
            region.setDeletedDate(new Date());
            if (!basicServices.update(region)) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.BRANCH_DELETION_FAILED);
                headerResponse.setResponseCode(ResponseCodes.BRANCH_DELETION_FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(region.getRegionName() + " deleted sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deleteRegion IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public RegionListResponse getRegions(GenericRequest request) throws IOException {
        RegionListResponse response = new RegionListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.printPayload(log, "header validation response after", headerResponse);
            List<Region> listOfRegions = basicServices.findAll(Region.class, false);
            if (null == listOfRegions) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            List<RegionInfo> Regions = new ArrayList<>();
            if (!listOfRegions.isEmpty()) {
                for (Region eachOne : listOfRegions) {
                    RegionInfo info = new RegionInfo(eachOne);
                    Regions.add(info);
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setRegions(Regions);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getRegions IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }
}
