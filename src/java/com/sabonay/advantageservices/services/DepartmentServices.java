package com.sabonay.advantageservices.services;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.useraccounts.Department;
import com.sabonay.advantageservices.models.useraccount.DepartmentInfo;
import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.useraccount.DepartmentListResponse;
import com.sabonay.advantageservices.restmodels.useraccount.DepartmentRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import com.sabonay.advantageservices.requestvalidators.DeleteDataValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import java.io.IOException;
import java.io.Serializable;
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
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 16 Jul, 2023 06:32 am
 */
@Stateless
public class DepartmentServices implements Serializable {

    private static final Logger log = Logger.getLogger(DepartmentServices.class.getName());

    @Inject
    private BasicServices basicJPA;
    @Inject
    IdGeneratorServices idGeneratorJPA;

    public GenericResponse createDepartment(DepartmentRequest request) throws IOException {
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
            AppLogger.printPayloadCompact(log, "CreateDepartment validation response ", headerResponse);

            if (StringUtils.isEmpty(request.getDepartmentName())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.DEPARTMENT_NAME_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.DEPARTMENT_NAME_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getCreatedBy())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.CREATED_BY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.CREATED_BY_REQUIRED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("Passed validation,about to save department details");
            Department department = new Department();
            department.setRecordId(request.getDepartmentName().toUpperCase().replaceAll(" ", "_"));
            department.setDepartmentName(request.getDepartmentName());
            department.setRemarks(request.getRemarks());
            department.setCreatedBy(request.getCreatedBy());
            AppLogger.printPayload(log, "final create department request", department);
            Department saved = basicJPA.save(department);
            if (null == saved) {
                headerResponse.setResponseCode(ResponseCodes.DEPARTMENT_CREATION_FAILED);
                headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.DEPARTMENT_CREATION_FAILED));
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(department.getDepartmentName() + " created successfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateDepartment IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse updateDepartment(DepartmentRequest request) throws IOException {
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
            AppLogger.printPayloadCompact(log, "updatedepartment validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
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
            Department department = basicJPA.find(Department.class, request.getRecordId());
            if (null == department) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.DEPARTMENT_NOT_FOUND);
                headerResponse.setResponseCode(ResponseCodes.DEPARTMENT_NOT_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.info(log, "found department " + department.toString() + " for update");
            log.info("Passed validation,about to update department details");
            department.setDepartmentName(request.getDepartmentName());
            department.setRemarks(request.getRemarks());
            department.setLastModifiedBy(request.getLastModifiedBy());
            department.setLastModifiedDate(new Date());
            if (!basicJPA.update(department)) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.DEPARTMENT_UPDATE_FAILED);
                headerResponse.setResponseCode(ResponseCodes.DEPARTMENT_UPDATE_FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(department.getDepartmentName() + " updated sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updatedepartment IOException");
            headerResponse.setResponseMessage(e.getMessage());
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public GenericResponse deleteDepartment(GenericDeleteRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = DeleteDataValidator.validateDeleteRequest(request);
            AppLogger.printPayloadCompact(log, "updatedepartment validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("about to find branch to be deleted");
            Department department = basicJPA.find(Department.class, request.getRecordId());
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
            if (!basicJPA.update(department)) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.BRANCH_DELETION_FAILED);
                headerResponse.setResponseCode(ResponseCodes.BRANCH_DELETION_FAILED);
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(department.getDepartmentName() + " deleted sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deletedepartment IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public DepartmentListResponse getDepartments(GenericRequest request) throws IOException {
        DepartmentListResponse response = new DepartmentListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.printPayload(log, "header validation response after", headerResponse);
            List<Department> listOfdepartments = basicJPA.findAll(Department.class, false);
            if (null == listOfdepartments) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            List<DepartmentInfo> departments = new ArrayList<>();
            if (!listOfdepartments.isEmpty()) {
                for (Department eachOne : listOfdepartments) {
                    DepartmentInfo info = new DepartmentInfo(eachOne);
                    departments.add(info);
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setDepartments(departments);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }
}
