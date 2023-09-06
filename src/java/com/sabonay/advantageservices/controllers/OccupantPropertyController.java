package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantPropertyListRequest;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantPropertyListResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantPropertyRequest;
import com.sabonay.advantageservices.restmodels.occupancy.OccupiedPropertyRequest;
import com.sabonay.advantageservices.services.OccupantPropertyServices;
import com.sabonay.advantageservices.services.UtitlityServices;
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
 * @date Sun 06 Aug, 2023 17:48 pm
 */
@RequestScoped
@Path("occupancy")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class OccupantPropertyController implements Serializable {

    private static final Logger log = Logger.getLogger(OccupantPropertyController.class.getName());
    @Inject
    private OccupantPropertyServices occupantpropertyServices;
    @Inject
    private UtitlityServices utitlityServices;

    @POST
    @Path(value = "/createoccupantproperty")
    public GenericResponse createOccupantProperty(OccupantPropertyRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "createOccupantPropertyRequest ", request);
            response = occupantpropertyServices.createOccupantProperty(request);
            AppLogger.printPayload(log, "createOccupantPropertyResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createOccupantProperty IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/updateoccupantproperty")
    public GenericResponse updateOccupantProperty(OccupantPropertyRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            response = occupantpropertyServices.updateOccupantProperty(request);
            AppLogger.printPayload(log, "UpdateOccupantPropertyResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateOccupantProperty IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/deleteoccupantproperty")
    public GenericResponse deleteOccupantProperty(GenericDeleteRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "DeleteOccupantPropertyRequest ", request);
            response = occupantpropertyServices.deleteOccupantProperty(request);
            AppLogger.printPayload(log, "DeleteOccupantPropertyesResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deleteOccupantProperty IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/occupantproperties")
    public OccupantPropertyListResponse getOccupantProperties(OccupantPropertyListRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        OccupantPropertyListResponse response = new OccupantPropertyListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "OccupantPropertyesListRequest ", request);
            response = occupantpropertyServices.getOccupantProperties(request);
            AppLogger.printPayload(log, "OccupantPropertyesListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "OccupantPropertyesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/occupiedpropertiesquicksearch")
    public OccupantPropertyListResponse getOccupiedProperties(OccupiedPropertyRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        OccupantPropertyListResponse response = new OccupantPropertyListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "OccupiedPropertyRequest ", request);
            response = utitlityServices.fetchQuickSearchOccupiedProperties(request);
            AppLogger.printPayload(log, "OccupiedPropertyResponse ", response);
            return response;
        } catch (Exception e) {
            AppLogger.error(log, e, "OccupantPropertyesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
