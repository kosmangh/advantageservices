package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.auth.AuditLogListRequest;
import com.sabonay.advantageservices.restmodels.auth.AuditLogListResponse;
import com.sabonay.advantageservices.restmodels.auth.AuditLogRequest;
import com.sabonay.advantageservices.restmodels.auth.LoginRequest;
import com.sabonay.advantageservices.restmodels.auth.LoginResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.services.AuthServices;
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
 * @date Sun 02 Jul, 2023 06:58 am
 */
@RequestScoped
@Path("auth")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class AuthController implements Serializable {

    private static final Logger log = Logger.getLogger(AuthController.class.getName());
    @Inject
    private AuthServices authServices;

    @POST
    @Path(value = "/login")
    public LoginResponse doValidateUserLogin(LoginRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        LoginResponse response = new LoginResponse();
        try {
            AppLogger.printPayload(log, "LoginRequest:: ", request);
            response = authServices.login(request);
            AppLogger.printPayload(log, "LoginResponse:: ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "login validation IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/passwordreset")
    public GenericResponse resetPassword(LoginRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "passwordresetrequest:: ", request);
            response = authServices.resetPassword(request);
            AppLogger.printPayload(log, "passwordresetResponse:: ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "auditLog IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/logaudit")
    public GenericResponse logAudit(AuditLogRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            AppLogger.printPayload(log, "AuditLogRequest:: ", request);
            response = authServices.logAudit(request);
            AppLogger.printPayload(log, "AuditLogsResponse:: ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "auditLog IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/auditlogs")
    public AuditLogListResponse auditLogs(AuditLogListRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        AuditLogListResponse response = new AuditLogListResponse();
        try {
            AppLogger.printPayload(log, "AuditLogListRequest:: ", request);
            response = authServices.searchAuditLogs(request);
            AppLogger.printPayload(log, "AuditLogListResponse:: ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "auditLog IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

}
