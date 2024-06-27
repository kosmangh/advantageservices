package com.sabonay.advantageservices.services;

import com.ctrloption.security.SecurityHash;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatesetup.Region;
import com.sabonay.advantageservices.entities.useraccounts.Department;
import com.sabonay.advantageservices.entities.useraccounts.Staff;
import com.sabonay.advantageservices.models.useraccount.StaffInfo;
import com.sabonay.advantageservices.requestvalidators.DeleteDataValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.requestvalidators.StaffValidator;
import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericSearchRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.useraccount.LoginAccountRequest;
import com.sabonay.advantageservices.restmodels.useraccount.StaffListResponse;
import com.sabonay.advantageservices.restmodels.useraccount.StaffRequest;
import com.sabonay.advantageservices.utils.AppConstants;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 09 Jul, 2023 07.38.35 am
 */
@Stateless
public class StaffServices {

    private static final Logger log = Logger.getLogger(StaffServices.class.getName());

    @Inject
    private BasicServices basicServices;

    public GenericResponse createStaff(StaffRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        Staff createStaff = new Staff();
        System.out.println("About to save staff");
        log.info("Nice one");
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            log.info("about to validateStaffRequest");
            createStaff = StaffValidator.validateStaffCommonFields(request);
            AppLogger.printPayload(log, "validateStaffRequest response ", createStaff);
            log.info("createStaff.getResponseCode() " + createStaff.getResponseCode() + " " + ResponseCodes.SUCCESS);
            if (!createStaff.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = createStaff.getResponseMsg();
                headerResponse.setResponseCode(createStaff.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            AppLogger.printPayloadCompact(log, "validateStaffRequest response ", headerResponse);
            if (StringUtils.isEmpty(request.getCreatedBy())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.CREATED_BY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            Department department = basicServices.find(Department.class, request.getDepartmentId());
            if (department == null) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.DEPARTMENT_NOT_FOUND);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            Region region = basicServices.find(Region.class, request.getRegionId());
            if (region == null) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.NO_REGION_FOUND);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            createStaff.setRecordId(basicServices.generateUsername(createStaff.getFullName()));
            createStaff.setUsername(createStaff.getRecordId());
            createStaff.setDepartment(department);
            createStaff.setRegion(region);
            createStaff.setCreatedBy(request.getCreatedBy());
            createStaff.setCreatedDate(new Date());
            log.info("Passed validation,about to save department details");
            AppLogger.printPayload(log, "final payload create staff ", createStaff);
            Staff saved = basicServices.save(createStaff);
            AppLogger.printPayloadCompact(log, "DB respsonse ", createStaff);
            if (null == saved) {
                headerResponse.setResponseCode(ResponseCodes.USER_CREATION_FAILED);
                headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.USER_CREATION_FAILED));
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(request.getFullName() + " created successfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateStaff IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse updateStaff(StaffRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            Staff validatedStaff = StaffValidator.validateStaffCommonFields(request);
            log.info("createStaff.getResponseCode() " + validatedStaff.getResponseCode() + " " + ResponseCodes.SUCCESS);
            if (!validatedStaff.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = validatedStaff.getResponseMsg();
                headerResponse.setResponseCode(validatedStaff.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getLastModifiedBy())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.MODIFIED_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.MODIFIED_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            Staff staff = basicServices.find(Staff.class, request.getRecordId());
            if (null == staff) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.FAILED);
                headerResponse.setResponseCode(ResponseCodes.NO_STAFF_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            Department department = basicServices.find(Department.class, request.getDepartmentId());
            if (null == department) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.FAILED);
                headerResponse.setResponseCode(ResponseCodes.DEPARTMENT_NOT_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            Region region = basicServices.find(Region.class, request.getRegionId());
            if (null == region) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.FAILED);
                headerResponse.setResponseCode(ResponseCodes.NO_REGION_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("Passed validation,about to update department details");

            staff = validatedStaff;
            staff.setDepartment(department);
            staff.setRegion(region);
            staff.setLastModifiedBy(request.getLastModifiedBy());
            staff.setLastModifiedDate(new Date());
            if (!basicServices.update(department)) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.DEPARTMENT_UPDATE_FAILED);
                headerResponse.setResponseCode(ResponseCodes.DEPARTMENT_UPDATE_FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(staff.getFullName() + " updated sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updatedepartment IOException");
            headerResponse.setResponseMessage(e.getMessage());
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public GenericResponse deleteStaff(GenericDeleteRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = DeleteDataValidator.validateDeleteRequest(request);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("about to find branch to be deleted");
            Staff department = basicServices.find(Staff.class, request.getRecordId());
            if (Objects.isNull(department)) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.DEPARTMENT_NOT_FOUND);
                headerResponse.setResponseCode(ResponseCodes.DEPARTMENT_NOT_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.info(log, "found department " + department.toString() + " for update");
            log.info("Passed validation,about to false delete this branch details");
            department.setDeleted(true);
            department.setDeletedBy(request.getDeletedBy());
            department.setDeletedDate(new Date());
            if (!basicServices.update(department)) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.BRANCH_DELETION_FAILED);
                headerResponse.setResponseCode(ResponseCodes.BRANCH_DELETION_FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(department.getFullName() + " deleted sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deletedepartment IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public StaffListResponse getStaffs(GenericSearchRequest request) throws IOException {

        StaffListResponse response = new StaffListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            List<Staff> listOfStaffs = new ArrayList<>();
            if (request.getSearchBy().equalsIgnoreCase(AppConstants.ALL)) {
                listOfStaffs = basicServices.findAll(Staff.class, false, EntityFields.lastLoginDate);
            } else {
                listOfStaffs = basicServices.searchRecords(Staff.class, request.getSearchBy(),
                        request.getSearchValue(), EntityFields.lastLoginDate);
            }
            if (null == listOfStaffs) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            log.info("total staff retrieved " + listOfStaffs.size());
            List<StaffInfo> staffs = new ArrayList<>();
            if (!listOfStaffs.isEmpty()) {
                for (Staff eachOne : listOfStaffs) {
                    staffs.add(new StaffInfo(eachOne));
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setStaffs(staffs);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse createLoginAccount(LoginAccountRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        Staff loginAccount = new Staff();
        log.info("inside login account method");
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            if (StringUtils.isEmpty(request.getCreatedBy())) {
                log.error("created by was not found");
                msg = ResponseCodes.CREATED_BY_REQUIRED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getPassword())) {
                log.error("password was not found");
                msg = ResponseCodes.PASSWORD_REQUIRED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getStatus())) {
                log.error("status was not found");
                msg = ResponseCodes.STATUS_REQUIRED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getUserRole())) {
                log.error("user role  was not found");
                msg = ResponseCodes.USER_ROLE_REQUIRED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            log.info("about to find staff");
            loginAccount = basicServices.find(Staff.class, request.getRecordId());
            if (loginAccount == null) {
                log.error("staff was not found");
                msg = ResponseCodes.NO_STAFF_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("staff found account creation continue");
            loginAccount.setAccountCreatedDate(new Date());
            loginAccount.setAccessLevel("PENDING");
            loginAccount.setStatus(AppConstants.NEW);
            loginAccount.setUserRole(request.getUserRole());
            loginAccount.setResetPassword(true);
//            createLoginAccount.setPassword(request.getPassword());
            loginAccount.setPassword(SecurityHash.getInstance().shaHash(request.getPassword()));

            loginAccount.setAccountCreatedBy(request.getCreatedBy());
            if (!basicServices.update(loginAccount)) {
                log.error("User account creation failed");
                msg = ResponseCodes.ACCOUNT_CREATION_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(loginAccount.getFullName() + "'s account created successfully;Inform user to login and reset password");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateStaff IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse adminResetUserPassword(LoginAccountRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        Staff loginAccount = new Staff();
        log.info("inside adminResetUserPassword method");
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                headerResponse.setResponseCode(headerResponse.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }

            if (StringUtils.isEmpty(request.getCreatedBy())) {
                log.error("created by was not found");
                msg = ResponseCodes.CREATED_BY_REQUIRED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getPassword())) {
                log.error("password was not found");
                msg = ResponseCodes.PASSWORD_REQUIRED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            log.info("about to find staff");
            loginAccount = basicServices.find(Staff.class, request.getRecordId());
            if (loginAccount == null) {
                log.error("staff was not found");
                msg = ResponseCodes.NO_STAFF_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("staff found password reset continue");
            loginAccount.setStatus(AppConstants.RESET);
//            createLoginAccount.setPassword(request.getPassword());
            loginAccount.setPassword(SecurityHash.getInstance().shaHash(request.getPassword()));
            loginAccount.setResetPassword(true);
            if (!basicServices.update(loginAccount)) {
                log.error("User password reset failed");
                msg = ResponseCodes.PASSWORD_RESET_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(" User account of " + loginAccount.getFullName() + " has been reset with temporay passwor " + request.getPassword() + " ;Inform user to login and reset password");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateStaff IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }
}
