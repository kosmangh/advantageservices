package com.sabonay.advantageservices.services;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatesetup.EstateBlock;
import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.models.estatesetup.EstatePropertyInfo;
import com.sabonay.advantageservices.requestvalidators.DeleteDataValidator;
import com.sabonay.advantageservices.requestvalidators.EstatePropertyValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericSearchRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstatePropertyListResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstatePropertyRequest;
import com.sabonay.advantageservices.services.utils.UtitlityServices;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
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
 * @date Fri 04 Aug, 2023 10:51 am
 */
@Stateless
public class EstatePropertyServices implements Serializable {

    private static final Logger log = Logger.getLogger(EstatePropertyServices.class.getName());

    @Inject
    private BasicServices basicServices;
    @Inject
    private UtitlityServices utitlityServices;

    public GenericResponse createEstateProperty(EstatePropertyRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        EstateProperty createEstateProperty = new EstateProperty();
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
            log.info("about to validateEstatePropertyCommonFields");
            createEstateProperty = EstatePropertyValidator.validateEstatePropertyCommonFields(request);
            if (!createEstateProperty.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                AppLogger.printPayload(log, "validateEstatePropertyCommonFields response ", createEstateProperty);
                log.info("not valid staff validation");
                msg = createEstateProperty.getResponseMsg();
                headerResponse.setResponseCode(createEstateProperty.getResponseCode());
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
            if (StringUtils.isEmpty(request.getCreatedBy())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.CREATED_BY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            EstateBlock estateBlock = basicServices.find(EstateBlock.class, request.getBlockId());
            if (estateBlock == null) {
                msg = ResponseCodes.NO_ESTATE_BLOCK_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            createEstateProperty.setRecordId(request.getRecordId().toUpperCase());
            createEstateProperty.setEstateBlock(estateBlock);
            createEstateProperty.setCreatedBy(request.getCreatedBy());
            createEstateProperty.setCreatedDate(new Date());
            log.info("Passed validation,about to save estate details");
            EstateProperty saved = basicServices.save(createEstateProperty);
            if (null == saved) {
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(ResponseCodes.ESTATE_BLOCK_CREATION_FAILED);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage("Property " + createEstateProperty + " created successfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateEstateProperty IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse updateEstateProperty(EstatePropertyRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        EstateProperty updateEstateProperty = new EstateProperty();
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
                AppLogger.printPayloadCompact(log, "updateEstateProperty validation response ", headerResponse);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            updateEstateProperty = EstatePropertyValidator.validateEstatePropertyCommonFields(request);
            log.info("createEstateProperty.getResponseCode() " + updateEstateProperty.getResponseCode() + " " + ResponseCodes.SUCCESS);
            if (!updateEstateProperty.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = updateEstateProperty.getResponseMsg();
                headerResponse.setResponseCode(updateEstateProperty.getResponseCode());
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
            EstateProperty estateProperty = basicServices.find(EstateProperty.class, request.getRecordId().toUpperCase());
            if (null == estateProperty) {
                msg = ResponseCodes.NO_ESTATE_PROPERTY_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            EstateBlock estateBlock = basicServices.find(EstateBlock.class, request.getBlockId());
            if (null == estateBlock) {
                msg = ResponseCodes.NO_ESTATE_BLOCK_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("Passed updateEstateProperty validations,about to update estate block details");
            updateEstateProperty.setCreatedBy(estateProperty.getCreatedBy());
            updateEstateProperty.setCreatedDate(estateProperty.getCreatedDate());
            updateEstateProperty.setRecordId(estateProperty.getRecordId().toUpperCase());
            updateEstateProperty.setEstateBlock(estateBlock);
            updateEstateProperty.setLastModifiedBy(request.getLastModifiedBy());
            updateEstateProperty.setLastModifiedDate(new Date());
            if (!basicServices.update(updateEstateProperty)) {
                msg = ResponseCodes.ESTATE_BLOCK_UPDATE_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage("Property " + updateEstateProperty.getPropertyNumber() + " updated sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updateEstateProperty IOException");
            headerResponse.setResponseMessage(e.getMessage());
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public GenericResponse deleteEstateProperty(GenericDeleteRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = DeleteDataValidator.validateDeleteRequest(request);
            AppLogger.printPayloadCompact(log, "deleteEstateProperty validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("about to find branch to be deleted");
            EstateProperty estateBlock = basicServices.find(EstateProperty.class, request.getRecordId());
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
            headerResponse.setResponseMessage("Property " + estateBlock.getPropertyNumber() + " deleted sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "delete property IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public EstatePropertyListResponse getEstateProperties(GenericSearchRequest request) throws IOException {

        EstatePropertyListResponse response = new EstatePropertyListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.printPayload(log, "header validation response after", headerResponse);
            List<EstateProperty> listOfEstateProperties = new ArrayList<>();

            if (request.getOccupiedProps()) {
                log.info("for occupied properties (rental or ground rent)");
                listOfEstateProperties = utitlityServices.getPropertiesOccupiedByBlock(request.getSearchValue(), request.getOccupationType());
            } else {
                listOfEstateProperties = basicServices.searchRecords(EstateProperty.class, request.getSearchBy(),
                        request.getSearchValue(), EntityFields.createdDate);
            }

            if (null == listOfEstateProperties) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            AppLogger.printPayload(log, "estates", listOfEstateProperties);
            log.info("total staff retrieved " + listOfEstateProperties.size());
            List<EstatePropertyInfo> blocks = new ArrayList<>();
            if (!listOfEstateProperties.isEmpty()) {
                for (EstateProperty eachOne : listOfEstateProperties) {
                    blocks.add(new EstatePropertyInfo(eachOne));
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setProperties(blocks);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public EstatePropertyListResponse getProperties(GenericSearchRequest request) throws IOException {

        EstatePropertyListResponse response = new EstatePropertyListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.printPayload(log, "header validation response after", headerResponse);
            List<EstateProperty> listOfEstateProperties = new ArrayList<>();
            listOfEstateProperties = basicServices.searchRecords(EstateProperty.class, request.getSearchBy(),
                    request.getSearchValue(), EntityFields.createdDate);

            if (null == listOfEstateProperties) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            AppLogger.printPayload(log, "estates", listOfEstateProperties);
            log.info("total staff retrieved " + listOfEstateProperties.size());
            List<EstatePropertyInfo> blocks = new ArrayList<>();
            if (!listOfEstateProperties.isEmpty()) {
                for (EstateProperty eachOne : listOfEstateProperties) {
                    blocks.add(new EstatePropertyInfo(eachOne));
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setProperties(blocks);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }
}
