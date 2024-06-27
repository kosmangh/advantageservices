package com.sabonay.advantageservices.services;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatesetup.Estate;
import com.sabonay.advantageservices.entities.estatesetup.EstateBlock;
import com.sabonay.advantageservices.models.estatesetup.EstateBlockInfo;
import com.sabonay.advantageservices.requestvalidators.DeleteDataValidator;
import com.sabonay.advantageservices.requestvalidators.EstateBlockValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericSearchRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstateBlockListResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstateBlockRequest;
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
 * @date Tue 01 Aug, 2023 08:17 am
 */
@Stateless
public class EstateBlockServices implements Serializable {

    private static final Logger log = Logger.getLogger(EstateBlockServices.class.getName());

    @Inject
    private BasicServices basicServices;

    public GenericResponse createEstateBlock(EstateBlockRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        EstateBlock createEstateBlock = new EstateBlock();
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
            log.info("about to validateEstateBlockCommonFields");
            createEstateBlock = EstateBlockValidator.validateEstateBlockCommonFields(request);
            if (!createEstateBlock.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = createEstateBlock.getResponseMsg();
                headerResponse.setResponseCode(createEstateBlock.getResponseCode());
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
            AppLogger.printPayloadCompact(log, "validateEstateBlockRequest response ", headerResponse);
            if (StringUtils.isEmpty(request.getCreatedBy())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.CREATED_BY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            Estate estate = basicServices.find(Estate.class, request.getEstateId());
            if (estate == null) {
                msg = ResponseCodes.NO_ESTATE_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            createEstateBlock.setRecordId(request.getRecordId().toUpperCase());
            createEstateBlock.setEstate(estate);
            createEstateBlock.setCreatedBy(request.getCreatedBy());
            createEstateBlock.setCreatedDate(new Date());
            log.info("Passed validation,about to save estate details");
            EstateBlock saved = basicServices.save(createEstateBlock);
            if (null == saved) {
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(ResponseCodes.ESTATE_BLOCK_CREATION_FAILED);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(request.getBlock() + " created successfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateEstateBlock IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse updateEstateBlock(EstateBlockRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        EstateBlock updateEstateBlock = new EstateBlock();
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
                AppLogger.printPayloadCompact(log, "updateEstateBlock validation response ", headerResponse);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            updateEstateBlock = EstateBlockValidator.validateEstateBlockCommonFields(request);
            log.info("createEstateBlock.getResponseCode() " + updateEstateBlock.getResponseCode() + " " + ResponseCodes.SUCCESS);
            if (!updateEstateBlock.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = updateEstateBlock.getResponseMsg();
                headerResponse.setResponseCode(updateEstateBlock.getResponseCode());
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
            EstateBlock estateBlock = basicServices.find(EstateBlock.class, request.getRecordId().toUpperCase());
            if (null == estateBlock) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.FAILED);
                headerResponse.setResponseCode(ResponseCodes.NO_ESTATE_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            Estate estate = basicServices.find(Estate.class, request.getEstateId());
            if (null == estate) {
                msg = ResponseCodes.NO_ESTATE_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("Passed updateEstateBlock validations,about to update estate block details");
            updateEstateBlock.setRecordId(request.getRecordId().toUpperCase());
            updateEstateBlock.setEstate(estate);
            updateEstateBlock.setCreatedBy(estateBlock.getCreatedBy());
            updateEstateBlock.setCreatedDate(estateBlock.getCreatedDate());
            updateEstateBlock.setLastModifiedBy(request.getLastModifiedBy());
            updateEstateBlock.setLastModifiedDate(new Date());
            if (!basicServices.update(updateEstateBlock)) {
                msg = ResponseCodes.ESTATE_BLOCK_UPDATE_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(estateBlock.getBlockName() + " updated sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updatedepartment IOException");
            headerResponse.setResponseMessage(e.getMessage());
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public GenericResponse deleteEstateBlock(GenericDeleteRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = DeleteDataValidator.validateDeleteRequest(request);
            AppLogger.printPayloadCompact(log, "deleteEstateBlock validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("about to find branch to be deleted");
            EstateBlock estateBlock = basicServices.find(EstateBlock.class, request.getRecordId());
            if (Objects.isNull(estateBlock)) {
                msg = ResponseCodes.NO_ESTATE_BLOCK_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
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
            headerResponse.setResponseMessage(estateBlock.getBlockName() + " deleted sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deletedepartment IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public EstateBlockListResponse getEstateBlocks(GenericSearchRequest request) throws IOException {

        EstateBlockListResponse response = new EstateBlockListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            List<EstateBlock> listOfEstateBlocks = new ArrayList<>();
            listOfEstateBlocks = basicServices.searchRecords(EstateBlock.class, request.getSearchBy(),
                    request.getSearchValue(), EntityFields.blockName);
            if (null == listOfEstateBlocks) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            log.info("total staff retrieved " + listOfEstateBlocks.size());
            List<EstateBlockInfo> blocks = new ArrayList<>();
            if (!listOfEstateBlocks.isEmpty()) {
                for (EstateBlock eachOne : listOfEstateBlocks) {
                    blocks.add(new EstateBlockInfo(eachOne));
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setEstateBlocks(blocks);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }
}
