package com.sabonay.advantageservices.services;

import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.ctrloption.models.DateRange;
import com.ctrloption.utils.UserAccountUtil;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.occupancy.Occupant;
import com.sabonay.advantageservices.models.occupancy.OccupantInfo;
import com.sabonay.advantageservices.requestvalidators.DeleteDataValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.requestvalidators.OccupantValidator;
import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantListRequest;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantListResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 05 Aug, 2023 19:53 pm
 */
@Stateless
public class OccupantServices extends CrudController implements Serializable {

    private static final Logger log = Logger.getLogger(OccupantServices.class.getName());
    @Inject
    private BasicServices basicServices;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        setEm(em);
        setEnviroment(Enviroment.JAVA_EE);
    }

    public GenericResponse createOccupant(OccupantRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        Occupant createOccupant = new Occupant();
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
            log.info("about to validateOccupantCommonFields");
            createOccupant = OccupantValidator.validateOccupantCommonFields(request);
            AppLogger.printPayload(log, "validateOccupantCommonFields response ", createOccupant);
            if (!createOccupant.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = createOccupant.getResponseMsg();
                headerResponse.setResponseCode(createOccupant.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getCreatedBy())) {
                msg = ResponseCodes.CREATED_BY_REQUIRED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            createOccupant.setRecordId(UserAccountUtil.createDate_uuidPart(request.getOccupantName()));
            createOccupant.setCreatedBy(request.getCreatedBy());
            createOccupant.setCreatedDate(new Date());
            log.info("Passed validation,about to save estate details");
            Occupant saved = basicServices.save(createOccupant);
            if (null == saved) {
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(ResponseCodes.ESTATE_BLOCK_CREATION_FAILED);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(createOccupant + " created successfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateOccupant IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse updateOccupant(OccupantRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        Occupant updateOccupant = new Occupant();
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
            updateOccupant = OccupantValidator.validateOccupantCommonFields(request);
            log.info("createOccupant.getResponseCode() " + updateOccupant.getResponseCode() + " " + ResponseCodes.SUCCESS);
            if (!updateOccupant.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = updateOccupant.getResponseMsg();
                headerResponse.setResponseCode(updateOccupant.getResponseCode());
                headerResponse.setResponseMessage(msg);
                response.setHeaderResponse(headerResponse);
                AppLogger.printPayload(log, msg, headerResponse);
                return response;
            }
            if (StringUtils.isEmpty(request.getLastModifiedBy())) {
                msg = ResponseCodes.MODIFIED_REQUIRED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("occupant id " + request.getRecordId().toUpperCase());
            Occupant occupant = basicServices.find(Occupant.class, request.getRecordId().toUpperCase());
            if (null == occupant) {
                msg = ResponseCodes.NO_OCCUPANT_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            log.info("Passed updateOccupant validations,about to update estate block details");
            updateOccupant.setRecordId(occupant.getRecordId());
            updateOccupant.setCreatedBy(occupant.getCreatedBy());
            updateOccupant.setCreatedDate(occupant.getCreatedDate());
            updateOccupant.setLastModifiedBy(request.getLastModifiedBy());
            updateOccupant.setLastModifiedDate(new Date());
            if (!basicServices.update(updateOccupant)) {
                msg = ResponseCodes.OCCUPANT_UPDATE_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(updateOccupant.getOccupantName() + " updated sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateOccupant IOException");
            headerResponse.setResponseMessage(e.getMessage());
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public GenericResponse deleteOccupant(GenericDeleteRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = DeleteDataValidator.validateDeleteRequest(request);
            AppLogger.printPayloadCompact(log, "deleteOccupant validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("about to find branch to be deleted");
            Occupant estateBlock = basicServices.find(Occupant.class, request.getRecordId());
            if (Objects.isNull(estateBlock)) {
                msg = ResponseCodes.NO_ESTATE_BLOCK_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.info(log, "found department " + estateBlock.toString() + " for update");
            log.info("Passed validation,about to false delete this branch details");
            estateBlock.setDeleted(true);
            estateBlock.setDeletedBy(request.getDeletedBy());
            estateBlock.setDeletedDate(new Date());
            if (!basicServices.update(estateBlock)) {
                msg = ResponseCodes.ESTATE_DELETION_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(estateBlock.getOccupantName() + " deleted sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "delete property IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public OccupantListResponse getOccupants(OccupantListRequest request) throws IOException {

        OccupantListResponse response = new OccupantListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                AppLogger.printPayload(log, "header validation response before", headerResponse);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.printPayload(log, "header validation response after", headerResponse);
            List<Occupant> listOfOccupants = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, Occupant.class);
            DateRange dateRange = new DateRange();
            dateRange.setToDate(request.getEndDate());
            dateRange.setFromDate(request.getStartDate());
            if (request.getOccupantType().equalsIgnoreCase("IND")) {
                builder.addObjectParam(EntityFields.institutional, false);
            }
            if (request.getOccupantType().equalsIgnoreCase("INS")) {
                builder.addObjectParam(EntityFields.institutional, true);
            }
            if (request.isDateRange()) {
                builder.addDateRange(dateRange, EntityFields.createdDate);
            }
            builder.orderByAsc(EntityFields.occupantName);
            builder.orderByDesc(EntityFields.createdDate);
            listOfOccupants = builder.buildQry().getResultList();
            if (null == listOfOccupants) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            AppLogger.printPayload(log, "estates", listOfOccupants);
            log.info("total staff retrieved " + listOfOccupants.size());
            List<OccupantInfo> occupants = new ArrayList<>();
            if (!listOfOccupants.isEmpty()) {
                for (Occupant eachOne : listOfOccupants) {
                    occupants.add(new OccupantInfo(eachOne));
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setOccupants(occupants);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }
}
