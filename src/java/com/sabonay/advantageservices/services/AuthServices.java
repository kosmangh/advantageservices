package com.sabonay.advantageservices.services;

import com.ctrloption.formating.DateTimeUtils;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.useraccounts.AuditLog;
import com.sabonay.advantageservices.entities.useraccounts.Staff;
import com.sabonay.advantageservices.models.AuditLogInfo;
import com.sabonay.advantageservices.restmodels.auth.AuditLogListRequest;
import com.sabonay.advantageservices.restmodels.auth.AuditLogListResponse;
import com.sabonay.advantageservices.restmodels.auth.AuditLogRequest;
import com.sabonay.advantageservices.restmodels.auth.LoginRequest;
import com.sabonay.advantageservices.restmodels.auth.LoginResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.utils.AppConstants;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author dainoo
 */
@Stateless
public class AuthServices implements Serializable {

    private static final Logger log = Logger.getLogger(AuthServices.class.getName());

    @Inject
    private BasicServices basicServices;

    @Inject
    private UserAccountServices userAccountServices;

    public LoginResponse login(LoginRequest request) throws IOException {
        LoginResponse response = new LoginResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        String lang = "";
        try {

            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                AppLogger.printPayload(log, msg, headerResponse);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            lang = request.getHeaderRequest().getRegion();
            log.info("lang " + lang);
            if (StringUtils.isEmpty(request.getUsername())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.USERNAME_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.USERNAME_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getPassword())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.PASSWORD_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.PASSWORD_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            Staff staff = basicServices.validateUser(request.getUsername(), request.getPassword());
            if (null == staff) {
                msg = ResponseCodes.INVALID_CREDENTIALS;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (staff.getStatus().equalsIgnoreCase(AppConstants.INACTIVE)) {
                msg = ResponseCodes.INACTIVE_ACCOUNT;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("valid user login");
            response.setDepartmentName(staff.getDepartment().getDepartmentName());
            response.setEmailAddress(staff.getEmail());
            response.setFullName(staff.getFullName());
            response.setRegionId(staff.getRegion().getRecordId());
            response.setRegionName(staff.getRegion().getRegionName());
            response.setResetPassword(String.valueOf(staff.isResetPassword()));
            response.setStatus(staff.getStatus());
            response.setUserRole(staff.getUserRole());
            response.setUsername(staff.getUsername());
            response.setZoneId(staff.getRegion().getZone().getRecordId());
            response.setZoneName(staff.getRegion().getZone().getZoneName());
            response.setZoneAddress(staff.getRegion().getZone().getAddress());
            response.setZoneContact(staff.getRegion().getZone().getContactNo());

            //when it's not password reset
            if (staff.getStatus().equalsIgnoreCase(AppConstants.ACTIVE)) {
                staff.setLastLoginDate(new Date());
                response.setLastLoginDate(DateTimeUtils.formatDateFully(staff.getLastLoginDate()));
                if (basicServices.update(staff)) {
                    log.info("Last login date updated");
                }
            }

            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS, lang));
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createCompany IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse resetPassword(LoginRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        String lang = "";
        try {

            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                AppLogger.printPayload(log, msg, headerResponse);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            lang = request.getHeaderRequest().getRegion();
            log.info("lang " + lang);
            if (StringUtils.isEmpty(request.getUsername())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.USERNAME_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.USERNAME_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getPassword())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.PASSWORD_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.PASSWORD_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            Staff staff = basicServices.find(Staff.class, request.getUsername());
            if (staff == null) {
                log.error("staff not found");
                msg = ResponseCodes.NO_STAFF_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            staff.setPassword(request.getPassword());
            staff.setStatus(AppConstants.ACTIVE);
            staff.setResetPassword(false);
            if (!basicServices.update(staff)) {
                log.error("password resetfailed");
                msg = ResponseCodes.PASSWORD_RESET_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.PASSWORD_RESET_SUCCESSFUL);
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createCompany IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse logAudit(AuditLogRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayloadCompact(log, "audit log response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            String lang = request.getHeaderRequest().getRegion();
            log.info("Passed validation,about to save company details");

            AuditLog auditLog = new AuditLog();
            auditLog.setRecordId(IdGeneratorServices.generateLongUniqueId());
            auditLog.setIpAddress(request.getHeaderRequest().getIpAddress());
            auditLog.setUserActivity(request.getUserActivity());
            auditLog.setUsername(request.getUsername());
            auditLog.setFullName(request.getFullName());
            auditLog.setUserRole(request.getUserRole());
            auditLog.setUi(request.getUi());
            auditLog.setCreatedBy(request.getUsername());
            auditLog.setCreatedDate(new Date());
            Staff staff = basicServices.find(Staff.class, request.getUsername());
            if (staff == null) {
                msg = ResponseCodes.NO_STAFF_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            auditLog.setEstateZone(staff.getRegion().getZone());
            auditLog.setRegion(staff.getRegion());
            AppLogger.printPayload(log, "final request", auditLog);
            AuditLog saved = basicServices.save(auditLog);
            if (null == saved) {
                msg = "audit logging " + ResponseCodes.getAppMsg(ResponseCodes.FAILED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS, lang));
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "logAudit IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public AuditLogListResponse searchAuditLogs(AuditLogListRequest request) throws IOException {

        AuditLogListResponse response = new AuditLogListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }

            if (StringUtils.isEmpty(request.getSearchBy())) {
                msg = ResponseCodes.SEARCH_BY_REQUIRED;
                headerResponse.setResponseCode(ResponseCodes.SEARCH_BY_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (!request.getSearchBy().equalsIgnoreCase("DR")) {
                if (StringUtils.isEmpty(request.getSearchValue())) {
                    msg = ResponseCodes.SEARCH_VALUE_REQUIRED;
                    headerResponse.setResponseCode(ResponseCodes.SEARCH_VALUE_REQUIRED);
                    headerResponse.setResponseMessage(msg);
                    AppLogger.warn(log, msg);
                    response.setHeaderResponse(headerResponse);
                    return response;
                }
            }
            List<AuditLogInfo> listOfAuditLogs = new ArrayList<>();
            listOfAuditLogs = userAccountServices.fetchAuditLogs(request);
            if (null == listOfAuditLogs) {
                log.error("error occurred while getting audit logs");
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
//            AppLogger.printPayload(log, "audit data", listOfAuditLogs);
            log.info(" total audit logs " + listOfAuditLogs.size());
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setAuditLogs(listOfAuditLogs);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

}
