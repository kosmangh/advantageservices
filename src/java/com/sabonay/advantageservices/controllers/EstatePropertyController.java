package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericSearchRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstatePropertyListResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstatePropertyRequest;
import com.sabonay.advantageservices.services.EstatePropertyServices;
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
 * @date Fri 04 Aug, 2023 11:50 am
 */
@RequestScoped
@Path("settings")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class EstatePropertyController implements Serializable {

    private static final Logger log = Logger.getLogger(EstatePropertyController.class.getName());
    @Inject
    private EstatePropertyServices estatePropertyServices;

    @POST
    @Path(value = "/createproperty")
    public GenericResponse createEstateProperty(EstatePropertyRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "createEstatePropertyRequest ", request);
            response = estatePropertyServices.createEstateProperty(request);
            AppLogger.printPayload(log, "createEstatePropertyResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createEstateProperty IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/updateproperty")
    public GenericResponse updateEstateProperty(EstatePropertyRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            response = estatePropertyServices.updateEstateProperty(request);
            AppLogger.printPayload(log, "UpdateEstatePropertyResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateEstateProperty IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/deleteproperty")
    public GenericResponse deleteEstateProperty(GenericDeleteRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "DeleteEstatePropertyRequest ", request);
            response = estatePropertyServices.deleteEstateProperty(request);
            AppLogger.printPayload(log, "DeleteEstatePropertyesResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deleteEstateProperty IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/estateproperties")
    public EstatePropertyListResponse getEstateProperties(GenericSearchRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        EstatePropertyListResponse response = new EstatePropertyListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "EstatePropertyesListRequest ", request);
            response = estatePropertyServices.getEstateProperties(request);
            AppLogger.printPayload(log, "EstatePropertyesListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "EstatePropertyesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
    @POST
    @Path(value = "/properties")
    public EstatePropertyListResponse geProperties(GenericSearchRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        EstatePropertyListResponse response = new EstatePropertyListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "EstatePropertyesListRequest ", request);
            response = estatePropertyServices.getProperties(request);
            AppLogger.printPayload(log, "EstatePropertyesListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "EstatePropertyesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
