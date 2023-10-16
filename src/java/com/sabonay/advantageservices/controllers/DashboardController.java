package com.sabonay.advantageservices.controllers;

import com.sabonay.advantageservices.restmodels.DashboardSummaryResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericSearchRequest;
import com.sabonay.advantageservices.services.DashboardServices;
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
 * @date Sun 15 Oct, 2023 13:27 pm
 */
@RequestScoped
@Path("dash")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class DashboardController implements Serializable {

    private static final Logger log = Logger.getLogger(DashboardController.class.getName());
    @Inject
    private DashboardServices dashboardServices;

    @POST
    @Path(value = "/dashboard")
    public DashboardSummaryResponse fetechDashboardSummary(GenericSearchRequest request) {
        MDC.put("requestid", request.getHeaderRequest().getRequestId());
        DashboardSummaryResponse response = new DashboardSummaryResponse();
        try {
            AppLogger.printPayload(log, "DashboardSummaryRequest:: ", request);
            response = dashboardServices.getDashboardSummary(request);
            AppLogger.printPayload(log, "DashboardSummaryResponse:: ", response);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "fetechDashboardSummary IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
