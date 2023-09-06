package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.estatebilling.GroundRentBillListRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.GroundRentalBillListResponse;
import com.sabonay.advantageservices.restmodels.estatebilling.GroundRentBillRequest;
import com.sabonay.advantageservices.services.GroundRentBillServices;
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
 * @date Wed 16 Aug, 2023 20:09 pm
 */
@RequestScoped
@Path("estatebilling")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class GroundRentBillingController implements Serializable {

    private static final Logger log = Logger.getLogger(GroundRentBillingController.class.getName());
    @Inject
    private GroundRentBillServices groundRentBillServices;

    @POST
    @Path(value = "/applygroundrentbill")
    public GenericResponse prepareGroundRentBills(GroundRentBillRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "GroundRentBillRequest ", request);
            response = groundRentBillServices.processGroundRentBill(request);
            AppLogger.printPayload(log, "GroundRentBillResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "prepareGroundRentBills IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/groundrentbills")
    public GroundRentalBillListResponse fetchGroundRentBills(GroundRentBillListRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GroundRentalBillListResponse response = new GroundRentalBillListResponse();
        try {
            AppLogger.printPayload(log, "GroundRentBillListRequest ", request);
            response = groundRentBillServices.getGroundRentBills(request);
            AppLogger.printPayload(log, "GroundRentBillListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "fetchGroundRentBills IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
