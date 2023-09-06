package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.ZoneListReponse;
import com.sabonay.advantageservices.restmodels.estatesetup.ZoneRequest;
import com.sabonay.advantageservices.services.ZoneServices;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 02 Jul, 2023 22:20 pm
 */
@RequestScoped
@Path("settings")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class ZoneController implements Serializable {

    private static final Logger log = Logger.getLogger(ZoneController.class.getName());
    @Inject
    private ZoneServices zoneServices;

    @POST
    @Path(value = "/savezone")
    public GenericResponse createZones(ZoneRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            response = zoneServices.createZone(request);
            AppLogger.printPayload(log, "createZoneResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createZone IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }

    }

    @POST
    @Path(value = "/updatezone")
    public GenericResponse updateZone(ZoneRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            response = zoneServices.updateZone(request);
            AppLogger.printPayload(log, "UpdateZoneResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateZone IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/deletezone")
    public GenericResponse deleteZone(GenericDeleteRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "DeleteZoneRequest ", request);
            response = zoneServices.deleteZone(request);
            AppLogger.printPayload(log, "DeleteZoneesResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deleteZone IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/zones")
    public ZoneListReponse doGetZones(GenericRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        ZoneListReponse response = new ZoneListReponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "ZoneesListRequest ", request);
            response = zoneServices.getZones(request);
            AppLogger.printPayload(log, "ZoneesListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "ZoneesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
