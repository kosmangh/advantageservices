package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericSearchRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstateListResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstateRequest;
import com.sabonay.advantageservices.services.EstateServices;
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
 * @date Sun 30 Jul, 2023 23:01 pm
 */
@RequestScoped
@Path("settings")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class EstateController implements Serializable {

    private static final Logger log = Logger.getLogger(EstateController.class.getName());
    @Inject
    private EstateServices estateServices;

    @POST
    @Path(value = "/createestate")
    public GenericResponse createEstate(EstateRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "createEstateRequest ", request);
            response = estateServices.createEstate(request);
            AppLogger.printPayload(log, "createEstateResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createEstate IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/updateestate")
    public GenericResponse updateEstate(EstateRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            response = estateServices.updateEstate(request);
            AppLogger.printPayload(log, "UpdateEstateResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateEstate IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/deleteestate")
    public GenericResponse deleteEstate(GenericDeleteRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "DeleteEstateRequest ", request);
            response = estateServices.deleteEstate(request);
            AppLogger.printPayload(log, "DeleteEstateesResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deleteEstate IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/estates")
    public EstateListResponse getEstates(GenericSearchRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        EstateListResponse response = new EstateListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "EstateesListRequest ", request);
            response = estateServices.getEstates(request);
            AppLogger.printPayload(log, "EstateesListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "EstateesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
