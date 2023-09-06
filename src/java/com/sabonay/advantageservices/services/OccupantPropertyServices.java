package com.sabonay.advantageservices.services;

import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.entities.occupancy.Occupant;
import com.sabonay.advantageservices.entities.occupancy.OccupantProperty;
import com.sabonay.advantageservices.models.occupancy.OccupantPropertyInfo;
import com.sabonay.advantageservices.requestvalidators.DeleteDataValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.requestvalidators.OccupantPropertyValidator;
import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantPropertyListRequest;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantPropertyListResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantPropertyRequest;
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
 * @date Sun 06 Aug, 2023 17:03 pm
 */
@Stateless
public class OccupantPropertyServices extends CrudController implements Serializable {

    private static final Logger log = Logger.getLogger(OccupantPropertyServices.class.getName());
    @Inject
    private BasicServices basicServices;
    @Inject
    private UtitlityServices utitlityServices;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        setEm(em);
        setEnviroment(Enviroment.JAVA_EE);
    }

    public GenericResponse createOccupantProperty(OccupantPropertyRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        OccupantProperty createOccupantProperty = new OccupantProperty();
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
            log.info("about to validateOccupantPropertyCommonFields");
            createOccupantProperty = OccupantPropertyValidator.validateOccupantPropertyCommonFields(request);
            AppLogger.printPayload(log, "validateOccupantPropertyCommonFields response ", createOccupantProperty);
            if (!createOccupantProperty.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = createOccupantProperty.getResponseMsg();
                headerResponse.setResponseCode(createOccupantProperty.getResponseCode());
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

            EstateProperty property = basicServices.find(EstateProperty.class, request.getPropertyId());
            if (property == null) {
                msg = ResponseCodes.NO_ESTATE_PROPERTY_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            Occupant occupant = basicServices.find(Occupant.class, request.getOccupantId());
            if (occupant == null) {
                msg = ResponseCodes.NO_OCCUPANT_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            AppLogger.printPayloadCompact(log, "validateOccupantPropertyRequest response ", headerResponse);
            createOccupantProperty.setRecordId((request.getPropertyId() + "#" + request.getOccupantId()).toUpperCase());
            createOccupantProperty.setEstateProperty(property);
            createOccupantProperty.setOccupant(occupant);
            createOccupantProperty.setCreatedBy(request.getCreatedBy());
            createOccupantProperty.setCreatedDate(new Date());
            log.info("Passed validation,about to save estate details");
            AppLogger.printPayload(log, "final payload create estate property ", createOccupantProperty);
            OccupantProperty saved = basicServices.save(createOccupantProperty);
            if (null == saved) {
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(ResponseCodes.ESTATE_BLOCK_CREATION_FAILED);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(createOccupantProperty + " created successfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateOccupantProperty IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse updateOccupantProperty(OccupantPropertyRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        OccupantProperty updateOccupantProperty = new OccupantProperty();
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
            AppLogger.printPayloadCompact(log, "updateOccupantProperty validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            updateOccupantProperty = OccupantPropertyValidator.validateOccupantPropertyCommonFields(request);
            AppLogger.printPayload(log, "validateOccupantPropertyRequest response ", updateOccupantProperty);
            log.info("createOccupantProperty.getResponseCode() " + updateOccupantProperty.getResponseCode() + " " + ResponseCodes.SUCCESS);
            if (!updateOccupantProperty.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = updateOccupantProperty.getResponseMsg();
                headerResponse.setResponseCode(updateOccupantProperty.getResponseCode());
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
            OccupantProperty occupantProperty = basicServices.find(OccupantProperty.class, request.getRecordId().toUpperCase());
            if (null == occupantProperty) {
                msg = ResponseCodes.NO_ESTATE_PROPERTY_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            EstateProperty property = basicServices.find(EstateProperty.class, request.getPropertyId());
            if (property == null) {
                msg = ResponseCodes.NO_ESTATE_PROPERTY_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            Occupant occupant = basicServices.find(Occupant.class, request.getOccupantId());
            if (occupant == null) {
                msg = ResponseCodes.NO_OCCUPANT_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            log.info("Passed updateOccupantProperty validations,about to update estate block details");
            updateOccupantProperty.setEstateProperty(property);
            updateOccupantProperty.setOccupant(occupant);
            updateOccupantProperty.setCreatedBy(occupantProperty.getCreatedBy());
            updateOccupantProperty.setCreatedDate(occupantProperty.getCreatedDate());
            updateOccupantProperty.setRecordId(occupantProperty.getRecordId());
            updateOccupantProperty.setLastModifiedBy(request.getLastModifiedBy());
            updateOccupantProperty.setLastModifiedDate(new Date());
            if (!basicServices.update(updateOccupantProperty)) {
                msg = ResponseCodes.OCCUPANT_PROPERTY_UPDATE_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(updateOccupantProperty.getEstateProperty().getPropertyName()
                    + " assigned to  " + occupant.getOccupantName() + " sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateOccupantProperty IOException");
            headerResponse.setResponseMessage(e.getMessage());
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public GenericResponse deleteOccupantProperty(GenericDeleteRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = DeleteDataValidator.validateDeleteRequest(request);
            AppLogger.printPayloadCompact(log, "deleteOccupantProperty validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("about to find branch to be deleted");
            OccupantProperty occupantProperty = basicServices.find(OccupantProperty.class, request.getRecordId());
            if (Objects.isNull(occupantProperty)) {
                msg = ResponseCodes.NO_OCCUPANT_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.info(log, "found department " + occupantProperty.toString() + " for update");
            log.info("Passed validation,about to false delete this branch details");
            occupantProperty.setDeleted(true);
            occupantProperty.setDeletedBy(request.getDeletedBy());
            occupantProperty.setDeletedDate(new Date());
            if (!basicServices.update(occupantProperty)) {
                msg = ResponseCodes.OCCUPANT_PROPERTY_DELETION_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(occupantProperty.getOccupant().getOccupantName() + "'s property deleted sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "delete property IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public OccupantPropertyListResponse getOccupantProperties(OccupantPropertyListRequest request) throws IOException {

        OccupantPropertyListResponse response = new OccupantPropertyListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.printPayload(log, "header validation response after", headerResponse);
            List<OccupantProperty> listOfOccupantProperties = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, OccupantProperty.class);
            if (request.isActiveOccupant()) {
                listOfOccupantProperties = utitlityServices.getOccupantPropertiesByBlock(request.getBlock(), request.getOccupationType());
            } else {

                if (request.getSearchBy().equalsIgnoreCase("BAB")) {
                    builder.addStringQryParam("estateProperty.estateBlock.recordId", request.getBlock(), QryBuilder.ComparismCriteria.EQUAL);
                }
                if (request.getSearchParameter().equalsIgnoreCase("MOB")) {
                    builder.addStringQryParam("occupant.mobileNo", request.getSearchValue(), QryBuilder.ComparismCriteria.LIKE);
                }
                if (request.getSearchParameter().equalsIgnoreCase("NAM")) {
                    builder.addStringQryParam("occupant.occupantName", request.getSearchValue(), QryBuilder.ComparismCriteria.LIKE);
                }
                if (request.getSearchParameter().equalsIgnoreCase("EMAIL")) {
                    builder.addStringQryParam("occupant.emailAddress", request.getSearchValue(), QryBuilder.ComparismCriteria.LIKE);
                }
                builder.addObjectParam(EntityFields.deleted, false);
                builder.orderByAsc(EntityFields._occupantName);
                builder.orderByDesc(EntityFields.createdDate);
                log.info(" getOccupantIdProperties " + builder.getQryInfo());
                listOfOccupantProperties = builder.buildQry().getResultList();
            }

            if (null == listOfOccupantProperties) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            log.info("total staff retrieved " + listOfOccupantProperties.size());
            List<OccupantPropertyInfo> occupants = new ArrayList<>();
            if (!listOfOccupantProperties.isEmpty()) {
                for (OccupantProperty eachOne : listOfOccupantProperties) {
                    occupants.add(new OccupantPropertyInfo(eachOne));
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setOccupantProperties(occupants);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }
}
