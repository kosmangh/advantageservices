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
    @Path(value = "/zone-dropdowns")
    @Produces(MediaType.APPLICATION_JSON)
    public DropdownResponse fetchZones() {
        log.info("fetching zone dropdowns");
        DropdownResponse response = new DropdownResponse();
        try {
            response = dropdownServices.getZonesDropdowns();
            AppLogger.printPayload(log, "Zone DropdownResponse:: ", response);
            return response;
        } catch (Exception e) {
            AppLogger.error(log, e, "error fetching zone dropdowns");
            HeaderResponse headerResponse = new HeaderResponse();
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(AppConstants.ERROR_PROCESSING_REQUEST));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
        }
    }

    @GET
    @Path(value = "/estate-dropdowns")
    @Produces(MediaType.APPLICATION_JSON)
    public DropdownResponse fetchZoneEstates(@QueryParam("zoneId") String zoneId) {
        MDC.put("requestId", zoneId);
        DropdownResponse response = new DropdownResponse();
        try {
            log.info("fetching estate dropdowns for zoneId: " + zoneId);
            response = dropdownServices.getEstatesDropdown(zoneId);
            AppLogger.printPayload(log, "fetchZoneEstates DropdownResponse:: ", response);
            return response;
        } catch (Exception e) {
            AppLogger.error(log, e, "fetchZoneEstates IOException");
            HeaderResponse headerResponse = new HeaderResponse();
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(AppConstants.ERROR_PROCESSING_REQUEST));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestId");
        }
    }

    @GET
    @Path(value = "/block-dropdowns")
    @Produces(MediaType.APPLICATION_JSON)
    public DropdownResponse fetchEstateBlocks(@QueryParam("estateId") String estateId) throws Exception {
        MDC.put("requestId", estateId);
        DropdownResponse response = new DropdownResponse();
        try {
            AppLogger.printPayload(log, "estateId:: ", estateId);
            response = dropdownServices.getBlocksDropdown(estateId);
            AppLogger.printPayload(log, " fetchEstateBlocks DropdownResponse:: ", response);
            return response;
        } catch (Exception e) {
            AppLogger.error(log, e, "fetchEstateBlocks IOException");
            HeaderResponse headerResponse = new HeaderResponse();
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(AppConstants.ERROR_PROCESSING_REQUEST));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestId");
        }
    }

    @GET
    @Path(value = "/property-dropdowns")
    @Produces(MediaType.APPLICATION_JSON)
    public DropdownResponse fetchProperties(@QueryParam("blockId") String estateId) throws Exception {
        MDC.put("requestId", estateId);
        DropdownResponse response = new DropdownResponse();
        try {
            AppLogger.printPayload(log, "estateId:: ", estateId);
            response = dropdownServices.getBlocksDropdown(estateId);
            AppLogger.printPayload(log, " fetchEstateBlocks DropdownResponse:: ", response);
            return response;
        } catch (Exception e) {
            AppLogger.error(log, e, "fetchEstateBlocks IOException");
            HeaderResponse headerResponse = new HeaderResponse();
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(AppConstants.ERROR_PROCESSING_REQUEST));
            response.setHeaderResponse(headerResponse);
            return response;
        } finally {
            MDC.remove("requestId");
        }
    }

}
