package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.billpayment.BillPaymentRequest;
import com.sabonay.advantageservices.restmodels.billpayment.PropertyLedgerEntriesRequest;
import com.sabonay.advantageservices.restmodels.billpayment.PropertyLedgerEntriesResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.services.BillPaymentServices;
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
 * @date Thu 24 Aug, 2023 06:11 am
 */
@RequestScoped
@Path("billpayment")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class BillPaymentController implements Serializable {

    private static final Logger log = Logger.getLogger(BillPaymentController.class.getName());
    @Inject
    private BillPaymentServices billPaymentServices;

    @POST
    @Path(value = "/paybill")
    public GenericResponse billPaymentRequest(BillPaymentRequest request) {
        log.info("inside billPaymentRequest method");
        long startTime = System.currentTimeMillis();
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "BillPaymentRequest ", request);
            response = billPaymentServices.processPayRentBillRequest(request);
            AppLogger.printPayload(log, "BillPaymentResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "billPaymentRequest IOException");
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
    @Path(value = "/propertledgerentries")
    public PropertyLedgerEntriesResponse fetchPropertyLedgerEntries(PropertyLedgerEntriesRequest request) {
        log.info("inside fetchPropertyLedgerEntries method");
        long startTime = System.currentTimeMillis();
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        PropertyLedgerEntriesResponse response = new PropertyLedgerEntriesResponse();
        try {
            AppLogger.printPayload(log, "PropertyLedgerEntriesRequest ", request);
            response = billPaymentServices.fetchOccupantLedger(request);
            AppLogger.printPayload(log, "PropertyLedgerEntriesResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "fetchPropertyLedgerEntries IOException");
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
    @Path(value = "/billpayments")
    public PropertyLedgerEntriesResponse fetchBillPayments(PropertyLedgerEntriesRequest request) {
        log.info("inside fetchBillPayments method");
        long startTime = System.currentTimeMillis();
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        PropertyLedgerEntriesResponse response = new PropertyLedgerEntriesResponse();
        try {
            AppLogger.printPayload(log, "BillPaymentRequest ", request);
            response = billPaymentServices.fetchBillPayments(request);
            AppLogger.printPayload(log, "BillPaymentResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "fetchBillPayments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            log.info("Execution time for " + request.getHeaderRequest().getRequestType() + " : " + executionTime + " milliseconds");
            MDC.remove("requestid");

        }
    }
}
