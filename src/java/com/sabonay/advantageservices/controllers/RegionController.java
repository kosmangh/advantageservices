package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.RegionListResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.RegionRequest;
import com.sabonay.advantageservices.services.RegionServices;
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
 * @date Tue 04 Jul, 2023 05:57 am
 */
@RequestScoped
@Path("settings")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class RegionController implements Serializable {

    private static final Logger log = Logger.getLogger(RegionController.class.getName());
    @Inject
    private RegionServices regionServices;

    @POST
    @Path(value = "/createregion")
    public GenericResponse createRegions(RegionRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            response = regionServices.createRegion(request);
            AppLogger.printPayload(log, "createRegionResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createRegion IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }

    }

    @POST
    @Path(value = "/updateregion")
    public GenericResponse updateRegion(RegionRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            response = regionServices.updateRegion(request);
            AppLogger.printPayload(log, "UpdateRegionResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateRegion IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/deleteregion")
    public GenericResponse deleteRegion(GenericDeleteRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "DeleteRegionRequest ", request);
            response = regionServices.deleteRegion(request);
            AppLogger.printPayload(log, "DeleteRegionesResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deleteRegion IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/regions")
    public RegionListResponse doGetRegions(GenericRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        RegionListResponse response = new RegionListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "RegionesListRequest ", request);
            response = regionServices.getRegions(request);
            AppLogger.printPayload(log, "RegionesListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "RegionesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
