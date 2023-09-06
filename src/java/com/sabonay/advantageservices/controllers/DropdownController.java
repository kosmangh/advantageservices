package com.sabonay.advantageservices.controllers;

import com.ctrloption.utils.MsgFormatter;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.restmodels.commons.DropdownResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.services.DropdownServices;
import com.sabonay.advantageservices.utils.AppConstants;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Wed 30 Aug, 2023 20:33 pm
 */
@RequestScoped
@Path("dropdowns")
public class DropdownController implements Serializable {

    private static final Logger log = Logger.getLogger(BillPaymentController.class.getName());
    @Inject
    private DropdownServices dropdownServices;

    @GET
    @Path(value = "/estates")
    @Produces(MediaType.APPLICATION_JSON)
    public DropdownResponse fetchZoneEstates(@QueryParam("zoneId") String zoneId) {
        MDC.put("requestid", zoneId);
        DropdownResponse response = new DropdownResponse();
        try {
            AppLogger.printPayload(log, "zoneId:: ", zoneId);
            response = dropdownServices.getEstatesDropdown(zoneId);
            AppLogger.printPayload(log, "DropdownResponse:: ", response);
            return response;
        } catch (Exception e) {
            AppLogger.error(log, e, "login validation IOException");
            HeaderResponse headerResponse = new HeaderResponse();
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(AppConstants.ERROR_PROCESSING_REQUEST));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }

    @GET
    @Path(value = "/blocks")
    public DropdownResponse fetchEstateBlocks(@QueryParam("estateId") String estateId) {
        MDC.put("requestid", estateId);
        DropdownResponse response = new DropdownResponse();
        try {
            AppLogger.printPayload(log, "estateId:: ", estateId);
            response = dropdownServices.getEstatesDropdown(estateId);
            AppLogger.printPayload(log, "DropdownResponse:: ", response);
            return response;
        } catch (Exception e) {
            AppLogger.error(log, e, "login validation IOException");
            HeaderResponse headerResponse = new HeaderResponse();
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(AppConstants.ERROR_PROCESSING_REQUEST));
            return response;
        } finally {
            MDC.remove("requestid");
        }
    }
}
