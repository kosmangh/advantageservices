package com.sabonay.advantageservices.services;

import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.estatesetup.Estate;
import com.sabonay.advantageservices.entities.estatesetup.EstateBlock;
import com.sabonay.advantageservices.entities.estatesetup.Region;
import com.sabonay.advantageservices.models.estatesetup.EstateInfo;
import com.sabonay.advantageservices.requestvalidators.DeleteDataValidator;
import com.sabonay.advantageservices.requestvalidators.EstateValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericSearchRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstateListResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstateRequest;
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
 * @date Sun 30 Jul, 2023 14:38 pm
 */
@Stateless
public class EstateServices extends CrudController implements Serializable {

    private static final Logger log = Logger.getLogger(EstateServices.class.getName());

    @Inject
    private BasicServices basicServices;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        setEm(em);
        setEnviroment(Enviroment.JAVA_EE);
    }

    public GenericResponse createEstate(EstateRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        Estate createEstate = new Estate();
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
            log.info("about to validateEstateCommonFields");
            createEstate = EstateValidator.validateEstateCommonFields(request);
            AppLogger.printPayload(log, "validateEstateCommonFields response ", createEstate);
            if (!createEstate.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = createEstate.getResponseMsg();
                headerResponse.setResponseCode(createEstate.getResponseCode());
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
            AppLogger.printPayloadCompact(log, "validateEstateRequest response ", headerResponse);
            if (StringUtils.isEmpty(request.getCreatedBy())) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.CREATED_BY_REQUIRED);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            Region region = basicServices.find(Region.class, request.getRegionId());
            if (region == null) {
                msg = ResponseCodes.NO_REGION_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            createEstate.setRecordId(request.getRecordId().toUpperCase());
            createEstate.setRegion(region);
            createEstate.setCreatedBy(request.getCreatedBy());
            createEstate.setCreatedDate(new Date());
            log.info("Passed validation,about to save estate details");
            AppLogger.printPayload(log, "final payload create estate ", createEstate);
            Estate saved = basicServices.save(createEstate);
//            AppLogger.printPayloadCompact(log, "final create estate payload ", createEstate);
            if (null == saved) {
                headerResponse.setResponseCode(ResponseCodes.USER_CREATION_FAILED);
                headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.USER_CREATION_FAILED));
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(request.getEstateName() + " created successfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreateEstate IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse updateEstate(EstateRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        Estate updateEstate = new Estate();
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
            updateEstate = EstateValidator.validateEstateCommonFields(request);
            AppLogger.printPayload(log, "validateEstateRequest response ", updateEstate);
            log.info("createEstate.getResponseCode() " + updateEstate.getResponseCode() + " " + ResponseCodes.SUCCESS);
            if (!updateEstate.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = updateEstate.getResponseMsg();
                headerResponse.setResponseCode(updateEstate.getResponseCode());
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
            Estate estate = basicServices.find(Estate.class, request.getRecordId().toUpperCase());
            if (null == estate) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.FAILED);
                headerResponse.setResponseCode(ResponseCodes.NO_ESTATE_FOUND);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            Region region = basicServices.find(Region.class, request.getRegionId());
            if (null == region) {
                msg = ResponseCodes.NO_REGION_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("Passed validation,about to update department details");

//            estate = updateEstate;
//            estate.setRecordId(request.getRecordId().toUpperCase());
//            estate.setRegion(region);
//            estate.setLastModifiedBy(request.getLastModifiedBy());
//            estate.setLastModifiedDate(new Date());
//            estate = updateEstate;
            updateEstate.setRecordId(request.getRecordId().toUpperCase());
            updateEstate.setCreatedBy(estate.getCreatedBy());
            updateEstate.setCreatedDate(estate.getCreatedDate());
            updateEstate.setRegion(region);
            updateEstate.setLastModifiedBy(request.getLastModifiedBy());
            updateEstate.setLastModifiedDate(new Date());

            if (!basicServices.update(updateEstate)) {
                msg = ResponseCodes.ESTATE_UPDATE_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(estate.getEstateName() + " updated sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updatedepartment IOException");
            headerResponse.setResponseMessage(e.getMessage());
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public GenericResponse deleteEstate(GenericDeleteRequest request) throws IOException {
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
            Estate estate = basicServices.find(Estate.class, request.getRecordId());
            if (Objects.isNull(estate)) {
                msg = ResponseCodes.NO_ESTATE_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.info(log, "found department " + estate.toString() + " for update");
            log.info("Passed validation,about to false delete this branch details");
            estate.setDeleted(true);
            estate.setDeletedBy(request.getDeletedBy());
            estate.setDeletedDate(new Date());
            if (!basicServices.update(estate)) {
                msg = ResponseCodes.ESTATE_DELETION_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(estate.getEstateName() + " deleted sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "deletedepartment IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public EstateListResponse getEstates(GenericSearchRequest request) throws IOException {

        EstateListResponse response = new EstateListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.printPayload(log, "header validation response after", headerResponse);
            List<Estate> listOfEstates = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, Estate.class);
            listOfEstates = builder.buildQry().getResultList();
            if (null == listOfEstates) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
//            AppLogger.printPayload(log, "estates", listOfEstates);
            log.info("total staff retrieved " + listOfEstates.size());
            List<EstateInfo> staffs = new ArrayList<>();
            if (!listOfEstates.isEmpty()) {
                for (Estate eachOne : listOfEstates) {
                    List<EstateBlock> blocks = basicServices.
                            searchRecordsStrict(EstateBlock.class, "estate.recordId", eachOne.getRecordId());
                    staffs.add(new EstateInfo(eachOne, blocks));

                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setEstates(staffs);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getEstates IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

}
