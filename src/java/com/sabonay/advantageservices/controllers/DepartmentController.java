package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.useraccount.DepartmentListResponse;
import com.sabonay.advantageservices.restmodels.useraccount.DepartmentRequest;
import com.sabonay.advantageservices.services.DepartmentServices;
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
 * @date Sun 16 Jul, 2023 06:50 am
 */
@RequestScoped
@Path("useraccounts")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class DepartmentController implements Serializable {

    private static final Logger log = Logger.getLogger(DepartmentController.class.getName());
    @Inject
    private DepartmentServices departmentServices;

    @POST
    @Path(value = "/createdepartment")
    public GenericResponse createDepartment(DepartmentRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        try {
            response = departmentServices.createDepartment(request);
            AppLogger.printPayload(log, "createDepartmentResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "createDepartment IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }

    }

    @POST
    @Path(value = "/updatedepartment")
    public GenericResponse updateDepartment(DepartmentRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            response = departmentServices.updateDepartment(request);
            AppLogger.printPayload(log, "UpdateDepartmentResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateDepartment IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/deletedepartment")
    public GenericResponse deleteDepartment(GenericDeleteRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "DeleteDepartmentRequest ", request);
            response = departmentServices.deleteDepartment(request);
            AppLogger.printPayload(log, "DeleteDepartmentesResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deleteDepartment IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @POST
    @Path(value = "/departments")
    public DepartmentListResponse getDepartments(GenericRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        DepartmentListResponse response = new DepartmentListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            AppLogger.printPayload(log, "DepartmentesListRequest ", request);
            response = departmentServices.getDepartments(request);
            AppLogger.printPayload(log, "DepartmentesListResponse ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "DepartmentesListRequest IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
