package com.sabonay.advantageservices.services;

import com.sabonay.advantageservices.entities.estatesetup.EstateZone;
import com.sabonay.advantageservices.models.estatesetup.ZoneInfo;
import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.ZoneListReponse;
import com.sabonay.advantageservices.restmodels.estatesetup.ZoneRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.requestvalidators.DeleteDataValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import java.io.IOException;
import java.io.Serializable;
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
 * @date Sun 02 Jul, 2023 07:43 am
 */
@Stateless
public class ZoneServices implements Serializable {

    private static final Logger log = Logger.getLogger(ZoneServices.class.getName());

    @Inject
    private BasicServices basicJPA;
    @Inject
    IdGeneratorServices idGeneratorServices;

    ZoneRequest request = new ZoneRequest();

    public ZoneServices() {
    }

    public GenericResponse createZone(ZoneRequest request) throws IOException {
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
            AppLogger.printPayloadCompact(log, "createZone validation response ", headerResponse);

            if (StringUtils.isEmpty(request.getZoneName())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.ZONE_NAME_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.ZONE_NAME_REQUIRED);
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
            log.info("Passed validation,about to save zone details");
            EstateZone zone = new EstateZone();
            zone.setRecordId(request.getZoneName().toUpperCase().replaceAll(" ", "_"));
            zone.setZoneName(request.getZoneName());
            zone.setRemarks(request.getRemarks());
            zone.setAddress(request.getAddress());
            zone.setContactNo(request.getContactNo());
            zone.setCreatedBy(request.getCreatedBy());
            AppLogger.printPayload(log, "final create zone request", zone);
            EstateZone saved = basicJPA.save(zone);
            if (null == saved) {
                headerResponse.setResponseCode(ResponseCodes.ZONE_CREATION_FAILED);
                headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.ZONE_CREATION_FAILED));
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(zone.getZoneName() + " created successfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createZone IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse updateZone(ZoneRequest request) throws IOException {
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
            AppLogger.printPayloadCompact(log, "updateZone validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getLastModifiedBy())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.MODIFIED_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.MODIFIED_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            EstateZone zone = basicJPA.find(EstateZone.class, request.getRecordId());
            if (null == zone) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.ZONE_NOT_FOUND);
                headerResponse.setResponseCode(ResponseCodes.ZONE_NOT_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.info(log, "found zone " + zone.toString() + " for update");
            log.info("Passed validation,about to update zone details");
            zone.setZoneName(request.getZoneName());
            zone.setRemarks(request.getRemarks());
            zone.setAddress(request.getAddress());
            zone.setContactNo(request.getContactNo());
            zone.setLastModifiedBy(request.getLastModifiedBy());
            zone.setLastModifiedDate(new Date());
            if (!basicJPA.update(zone)) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.ZONE_UPDATE_FAILED);
                headerResponse.setResponseCode(ResponseCodes.ZONE_UPDATE_FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(zone.getZoneName() + " updated sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateZone IOException");
            headerResponse.setResponseMessage(e.getMessage());
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public GenericResponse deleteZone(GenericDeleteRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = DeleteDataValidator.validateDeleteRequest(request);
            AppLogger.printPayloadCompact(log, "updateZone validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("about to find branch to be deleted");
            EstateZone zone = basicJPA.find(EstateZone.class, request.getRecordId());
            if (Objects.isNull(zone)) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.ZONE_NOT_FOUND);
                headerResponse.setResponseCode(ResponseCodes.ZONE_NOT_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.info(log, "found zone " + zone.toString() + " for update");
            log.info("Passed validation,about to false delete this branch details");
            zone.setDeleted(true);
            zone.setDeletedBy(request.getDeletedBy());
            zone.setDeletedDate(new Date());
            if (!basicJPA.update(zone)) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.BRANCH_DELETION_FAILED);
                headerResponse.setResponseCode(ResponseCodes.BRANCH_DELETION_FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(zone.getZoneName() + " deleted sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deleteZone IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public ZoneListReponse getZones(GenericRequest request) throws IOException {
        ZoneListReponse response = new ZoneListReponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.printPayload(log, "header validation response after", headerResponse);
            List<EstateZone> listOfZones = basicJPA.findAll(EstateZone.class, false);
            if (null == listOfZones) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            List<ZoneInfo> Zones = new ArrayList<>();
            if (!listOfZones.isEmpty()) {
                for (EstateZone eachOne : listOfZones) {
                    ZoneInfo info = new ZoneInfo(eachOne);
                    Zones.add(info);
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setZones(Zones);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getZones IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }
}
