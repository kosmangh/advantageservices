package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.estatebilling.GroundRentalBillListResponse;
import com.sabonay.advantageservices.restmodels.estatebilling.RentalBackBillRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.RentalBillListRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.RentalBillRequest;
import com.sabonay.advantageservices.services.RentalBillServices;
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
 * @date Sat 19 Aug, 2023 19:22 pm
 */
@RequestScoped
@Path("estatebilling")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class RentalBillController implements Serializable {

    private static final Logger log = Logger.getLogger(RentalBillServices.class.getName());
    @Inject
    private RentalBillServices rentalBillServices;

    @POST
    @Path(value = "/applyrentalbill")
    public GenericResponse prepareRentalBill(RentalBillRequest request) {
        log.info("inside prepareRentalBill method");
        long startTime = System.currentTimeMillis();
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "RentalBillRequest ", request);
            response = rentalBillServices.processRentalBillRequest(request);
            AppLogger.printPayload(log, "RentalBillResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "prepareGroundRentBills IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            log.info("Execution time for " + request.getHeaderRequest().getRequestType() + " : " + executionTime + " milliseconds");
            MDC.remove("requestid");

        }
    }

    @POST
    @Path(value = "/applylastrentalbill")
    public GenericResponse prepareBatchRentalBills(RentalBillRequest request) {
        log.info("inside prepareBatchRentalBills method");
        long startTime = System.currentTimeMillis();
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "RentalBillRequest ", request);
            response = rentalBillServices.processLastRentalbill4CurrentMonth(request);
            AppLogger.printPayload(log, "RentalBillResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "prepareGroundRentBills IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            log.info("Execution time for " + request.getHeaderRequest().getRequestType() + " : " + executionTime + " milliseconds");
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/applybackrentalbill")
    public GenericResponse prepareBackRentalBills(RentalBackBillRequest request) {
        log.info("inside prepareBackRentalBills method");
        long startTime = System.currentTimeMillis();
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "RentalBackBillRequest ", request);
            response = rentalBillServices.processBackRentalbill(request);
            AppLogger.printPayload(log, "RentalBackBillResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "prepareBackRentalBills IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            log.info("Execution time for " + request.getHeaderRequest().getRequestType() + " : " + executionTime + " milliseconds");
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/rentalbills")
    public GroundRentalBillListResponse fetchRentalBills(RentalBillListRequest request) {
        log.info("inside fetchRentalBills method");
        long startTime = System.currentTimeMillis();
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GroundRentalBillListResponse response = new GroundRentalBillListResponse();
        try {
            AppLogger.printPayload(log, "RentalBillListRequest ", request);
            response = rentalBillServices.getRentalBills(request);
            AppLogger.printPayload(log, "RentalBillListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "fetchRentalBills IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = (endTime - startTime);
            log.info("Execution time for " + request.getHeaderRequest().getRequestType() + " : " + executionTime + " milliseconds");
            MDC.remove("requestid");

        }
    }

}
