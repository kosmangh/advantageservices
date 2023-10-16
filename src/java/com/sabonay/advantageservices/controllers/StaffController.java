package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericSearchRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.useraccount.LoginAccountRequest;
import com.sabonay.advantageservices.restmodels.useraccount.StaffListResponse;
import com.sabonay.advantageservices.restmodels.useraccount.StaffRequest;
import com.sabonay.advantageservices.services.StaffServices;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import java.io.IOException;
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
 * @date Mon 17 Jul, 2023 06:17 am
 */
@RequestScoped
@Path("useraccounts")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class StaffController {

    private static final Logger log = Logger.getLogger(StaffController.class.getName());
    @Inject
    private StaffServices staffServices;

    @POST
    @Path(value = "/createstaff")
    public GenericResponse createStaff(StaffRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "createStaffRequest ", request);
            response = staffServices.createStaff(request);
            AppLogger.printPayload(log, "createStaffResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createStaff IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/updatestaff")
    public GenericResponse updateStaff(StaffRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            response = staffServices.updateStaff(request);
            AppLogger.printPayload(log, "UpdateStaffResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateStaff IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/deletestaff")
    public GenericResponse deleteStaff(GenericDeleteRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "DeleteStaffRequest ", request);
            response = staffServices.deleteStaff(request);
            AppLogger.printPayload(log, "DeleteStaffesResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deleteStaff IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/staffs")
    public StaffListResponse getStaffs(GenericSearchRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        StaffListResponse response = new StaffListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "StaffesListRequest ", request);
            response = staffServices.getStaffs(request);
            AppLogger.printPayload(log, "StaffesListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "StaffesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/createaccount")
    public GenericResponse createUserAccount(LoginAccountRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "LoginAccountRequest ", request);
            response = staffServices.createLoginAccount(request);
            AppLogger.printPayload(log, "LoginAccountResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createStaff IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/adminresetpassword")
    public GenericResponse adminResetPassword(LoginAccountRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "adminResetPassword request ", request);
            response = staffServices.adminResetUserPassword(request);
            AppLogger.printPayload(log, "adminresetpassword response ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "adminResetPassword IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

}
