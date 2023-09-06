package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantListRequest;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantListResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantRequest;
import com.sabonay.advantageservices.services.OccupantServices;
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
 * @date Sat 05 Aug, 2023 19:58 pm
 */
@RequestScoped
@Path("occupancy")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class OccupantController implements Serializable {

    private static final Logger log = Logger.getLogger(OccupantController.class.getName());
    @Inject
    private OccupantServices occupantServices;

    @POST
    @Path(value = "/createoccupant")
    public GenericResponse createOccupant(OccupantRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "createOccupantRequest ", request);
            response = occupantServices.createOccupant(request);
            AppLogger.printPayload(log, "createOccupantResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createOccupant IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/updateoccupant")
    public GenericResponse updateOccupant(OccupantRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            response = occupantServices.updateOccupant(request);
            AppLogger.printPayload(log, "UpdateOccupantResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateOccupant IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/deleteoccupant")
    public GenericResponse deleteOccupant(GenericDeleteRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "DeleteOccupantRequest ", request);
            response = occupantServices.deleteOccupant(request);
            AppLogger.printPayload(log, "DeleteOccupantesResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deleteOccupant IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/occupants")
    public OccupantListResponse getOccupants(OccupantListRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        OccupantListResponse response = new OccupantListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "OccupantesListRequest ", request);
            response = occupantServices.getOccupants(request);
            AppLogger.printPayload(log, "OccupantesListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "OccupantesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
