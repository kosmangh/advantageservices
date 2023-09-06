package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatebilling.PropertyChargeListRepsonse;
import com.sabonay.advantageservices.restmodels.estatebilling.PropertyChargeListRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.PropertyChargeRequest;
import com.sabonay.advantageservices.services.PropertyChargeServices;
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
 * @date Sat 12 Aug, 2023 18:48 pm
 */
@RequestScoped
@Path("estatebilling")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class PropertyChargeController implements Serializable {

    private static final Logger log = Logger.getLogger(PropertyChargeController.class.getName());
    @Inject
    private PropertyChargeServices propertychargeServices;

    @POST
    @Path(value = "/createpropertycharge")
    public GenericResponse createPropertyCharge(PropertyChargeRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "createPropertyChargeRequest ", request);
            response = propertychargeServices.createPropertyCharge(request);
            AppLogger.printPayload(log, "createPropertyChargeResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createPropertyCharge IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/updatepropertycharge")
    public GenericResponse updatePropertyCharge(PropertyChargeRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            response = propertychargeServices.updatePropertyCharge(request);
            AppLogger.printPayload(log, "UpdatePropertyChargeResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updatePropertyCharge IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/deletepropertycharge")
    public GenericResponse deletePropertyCharge(GenericDeleteRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "DeletePropertyChargeRequest ", request);
            response = propertychargeServices.deletePropertyCharge(request);
            AppLogger.printPayload(log, "DeletePropertyChargeesResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deletePropertyCharge IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/propertycharges")
    public PropertyChargeListRepsonse getOccupantProperties(PropertyChargeListRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        PropertyChargeListRepsonse response = new PropertyChargeListRepsonse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "PropertyChargeesListRequest ", request);
            response = propertychargeServices.getPropertyCharges(request);
            AppLogger.printPayload(log, "PropertyChargeesListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "PropertyChargeesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
