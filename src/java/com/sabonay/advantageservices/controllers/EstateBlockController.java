package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericSearchRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstateBlockListResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstateBlockRequest;
import com.sabonay.advantageservices.services.EstateBlockServices;
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
* @date Tue 01 Aug, 2023 08:46 am
*/
@RequestScoped
@Path("settings")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class EstateBlockController implements Serializable {

    private static final Logger log = Logger.getLogger(EstateBlockController.class.getName());
    @Inject
    private EstateBlockServices estateBlockServices;

    @POST
    @Path(value = "/createblock")
    public GenericResponse createEstateBlock(EstateBlockRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "createEstateBlockRequest ", request);
            response = estateBlockServices.createEstateBlock(request);
            AppLogger.printPayload(log, "createEstateBlockResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createEstateBlock IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/updateblock")
    public GenericResponse updateEstateBlock(EstateBlockRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            response = estateBlockServices.updateEstateBlock(request);
            AppLogger.printPayload(log, "UpdateEstateBlockResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateEstateBlock IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/deleteblock")
    public GenericResponse deleteEstateBlock(GenericDeleteRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "DeleteEstateBlockRequest ", request);
            response = estateBlockServices.deleteEstateBlock(request);
            AppLogger.printPayload(log, "DeleteEstateBlockesResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deleteEstateBlock IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/estateblocks")
    public EstateBlockListResponse getEstateBlocks(GenericSearchRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        EstateBlockListResponse response = new EstateBlockListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "EstateBlockesListRequest ", request);
            response = estateBlockServices.getEstateBlocks(request);
            AppLogger.printPayload(log, "EstateBlockesListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "EstateBlockesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
