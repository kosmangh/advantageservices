package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.billpayment.BillPaymentRequest;
import com.sabonay.advantageservices.restmodels.billpayment.DemandNoticeRequest;
import com.sabonay.advantageservices.restmodels.billpayment.DemandNoticeResponse;
import com.sabonay.advantageservices.restmodels.billpayment.OccupantBillsRequest;
import com.sabonay.advantageservices.restmodels.billpayment.OccupantBillsResponse;
import com.sabonay.advantageservices.restmodels.billpayment.PropertyLedgerEntriesRequest;
import com.sabonay.advantageservices.restmodels.billpayment.PropertyLedgerEntriesResponse;
import com.sabonay.advantageservices.restmodels.billpayment.ReversePayBillRequest;
import com.sabonay.advantageservices.restmodels.billpayment.UpdatePayBillRequest;
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
    @Path(value = "/pay-bill")
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
    @Path(value = "/update-pay-bill")
    public GenericResponse updateBillPaymentRequest(UpdatePayBillRequest request) {
        log.info("inside billPaymentRequest method");
        long startTime = System.currentTimeMillis();
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "BillPaymentRequest ", request);
            response = billPaymentServices.processUpdatePayBillRequest(request);
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
    @Path(value = "/reverse-pay-bill")
    public GenericResponse reverseBillPaymentRequest(ReversePayBillRequest request) {
        log.info("inside reverseBillPaymentRequest method");
        long startTime = System.currentTimeMillis();
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "reverseBillPaymentRequest ", request);
            response = billPaymentServices.processReversePayBillRequest(request);
            AppLogger.printPayload(log, "ReverseBillPaymentRequestResponse ", response);
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
    @Path(value = "/outstanding-bills")
    public OccupantBillsResponse fetchOutstandingBills(OccupantBillsRequest request) {
        log.info("inside fetchOutstandingBills method");
        long startTime = System.currentTimeMillis();
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        OccupantBillsResponse response = new OccupantBillsResponse();
        try {
            AppLogger.printPayload(log, "OccupantBillsRequest ", request);
            response = billPaymentServices.fetchOutstandingBills(request);
            AppLogger.printPayload(log, "OccupantBillsResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "fetchOutstandingBills IOException");
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
    @Path(value = "/property-ledgers")
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
    @Path(value = "/allpropertledgerentries")
    public PropertyLedgerEntriesResponse fetchAllPropertyLedgerEntries(PropertyLedgerEntriesRequest request) {
        log.info("inside fetchPropertyLedgerEntries method");
        long startTime = System.currentTimeMillis();
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        PropertyLedgerEntriesResponse response = new PropertyLedgerEntriesResponse();
        try {
            AppLogger.printPayload(log, "PropertyLedgerEntriesRequest ", request);
            response = billPaymentServices.fetchAllOccupantLedger(request);
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
    @Path(value = "/bill-payments")
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

    @POST
    @Path(value = "/demandnotice")
    public DemandNoticeResponse generateDemandNotice(DemandNoticeRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        DemandNoticeResponse response = new DemandNoticeResponse();
        try {
            AppLogger.printPayload(log, "DemandNoticeRequest ", request);
            response = billPaymentServices.generateDemandNotice(request);
            AppLogger.printPayload(log, "DemandNoticeResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "generateDemandNotice IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
