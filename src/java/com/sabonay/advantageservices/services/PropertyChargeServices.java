package com.sabonay.advantageservices.services;

import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatebilling.PropertyCharge;
import com.sabonay.advantageservices.entities.estatesetup.Region;
import com.sabonay.advantageservices.models.estatebilling.PropertyChargeInfo;
import com.sabonay.advantageservices.requestvalidators.DeleteDataValidator;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.requestvalidators.PropertChargeProcessor;
import com.sabonay.advantageservices.restmodels.commons.GenericDeleteRequest;
import com.sabonay.advantageservices.restmodels.commons.GenericResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatebilling.PropertyChargeListRepsonse;
import com.sabonay.advantageservices.restmodels.estatebilling.PropertyChargeListRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.PropertyChargeRequest;
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
 * @date Sat 12 Aug, 2023 11:48 am
 */
@Stateless
public class PropertyChargeServices extends CrudController implements Serializable {

    private static final Logger log = Logger.getLogger(PropertyChargeServices.class.getName());
    @Inject
    private BasicServices basicServices;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        setEm(em);
        setEnviroment(Enviroment.JAVA_EE);
    }

    public GenericResponse createPropertyCharge(PropertyChargeRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        PropertyCharge createPropertyCharge = new PropertyCharge();
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
            log.info("about to validatePropertyChargeCommonFields");
            createPropertyCharge = PropertChargeProcessor.validatePropertyChargeCommonFields(request);
            AppLogger.printPayload(log, "validatePropertyChargeCommonFields response ", createPropertyCharge);
            if (!createPropertyCharge.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = createPropertyCharge.getResponseMsg();
                headerResponse.setResponseCode(createPropertyCharge.getResponseCode());
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

            Region region = basicServices.find(Region.class, request.getRegionId());
            if (region == null) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.NO_REGION_FOUND);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            AppLogger.printPayloadCompact(log, "validatePropertyChargeRequest response ", headerResponse);
            createPropertyCharge.setRegion(region);
            createPropertyCharge.setRecordId(IdGeneratorServices.getPropertyChargeId(createPropertyCharge));
            createPropertyCharge.setCreatedBy(request.getCreatedBy());
            createPropertyCharge.setCreatedDate(new Date());
            log.info("Passed validation,about to save PropertyCharge details");
            AppLogger.printPayload(log, "final payload create estate property ", createPropertyCharge);
            PropertyCharge saved = basicServices.save(createPropertyCharge);
            if (null == saved) {
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(ResponseCodes.ESTATE_BLOCK_CREATION_FAILED);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage("Saved charge property of " + createPropertyCharge.getPropertyUsage()
                    + " for " + region.getRegionName() + " sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "CreatePropertyCharge IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public GenericResponse updatePropertyCharge(PropertyChargeRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        PropertyCharge updateCharge = new PropertyCharge();
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
            AppLogger.printPayloadCompact(log, "updatePropertyCharge validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            updateCharge = PropertChargeProcessor.validatePropertyChargeCommonFields(request);
            AppLogger.printPayload(log, "validatePropertyChargeRequest response ", updateCharge);
            log.info("createPropertyCharge.getResponseCode() " + updateCharge.getResponseCode() + " " + ResponseCodes.SUCCESS);
            if (!updateCharge.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                log.info("not valid staff validation");
                msg = updateCharge.getResponseMsg();
                headerResponse.setResponseCode(updateCharge.getResponseCode());
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
            PropertyCharge propertyCharge = basicServices.find(PropertyCharge.class, request.getRecordId().toUpperCase());
            if (null == propertyCharge) {
                msg = ResponseCodes.NO_PROPERTY_CHARGE_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            log.info("Passed updatePropertyCharge validations,about to update estate block details");
            Region region = basicServices.find(Region.class, request.getRegionId());
            if (region == null) {
                msg = ResponseCodes.getAppMsg(ResponseCodes.NO_REGION_FOUND);
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }

            updateCharge.setRegion(region);
            updateCharge.setCreatedBy(propertyCharge.getCreatedBy());
            updateCharge.setCreatedDate(propertyCharge.getCreatedDate());
            updateCharge.setRecordId(propertyCharge.getRecordId());
            updateCharge.setLastModifiedBy(request.getLastModifiedBy());
            updateCharge.setLastModifiedDate(new Date());
            if (!basicServices.update(updateCharge)) {
                msg = ResponseCodes.OCCUPANT_PROPERTY_UPDATE_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
//           StringUtils.capitalize(msg);
            headerResponse.setResponseMessage("Updated charge property of " + updateCharge.getPropertyUsage()
                    + " for " + region.getRegionName() + " sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "updatePropertyCharge IOException");
            headerResponse.setResponseMessage(e.getMessage());
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public GenericResponse deletePropertyCharge(GenericDeleteRequest request) throws IOException {
        GenericResponse response = new GenericResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        String msg;
        try {
            headerResponse = DeleteDataValidator.validateDeleteRequest(request);
            AppLogger.printPayloadCompact(log, "deletePropertyCharge validation response ", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            log.info("about to find branch to be deleted");
            PropertyCharge propertyCharge = basicServices.find(PropertyCharge.class, request.getRecordId());
            if (Objects.isNull(propertyCharge)) {
                msg = ResponseCodes.NO_OCCUPANT_FOUND;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.info(log, "found department " + propertyCharge.toString() + " for update");
            log.info("Passed validation,about to false delete this branch details");
            propertyCharge.setDeleted(true);
            propertyCharge.setDeletedBy(request.getDeletedBy());
            propertyCharge.setDeletedDate(new Date());
            if (!basicServices.update(propertyCharge)) {
                msg = ResponseCodes.OCCUPANT_PROPERTY_DELETION_FAILED;
                headerResponse.setResponseCode(ResponseCodes.FAILED);
                headerResponse.setResponseMessage(msg);
                AppLogger.warn(log, msg);
                response.setHeaderResponse(headerResponse);
                return response;
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage("Updated charge property of " + propertyCharge.getPropertyUsage()
                    + " for " + propertyCharge.getRegion().getRegionName() + " sucessfully");
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "delete property IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            response.setHeaderResponse(headerResponse);
            return response;
        }
    }

    public PropertyChargeListRepsonse getPropertyCharges(PropertyChargeListRequest request) throws IOException {
        PropertyChargeListRepsonse response = new PropertyChargeListRepsonse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.printPayload(log, "header validation response after", headerResponse);
            List<PropertyCharge> listOfPropertyCharges = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, PropertyCharge.class);
            builder.addStringQryParam(EntityFields._region, request.getRegionId(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addNumberParam(EntityFields.chargeYear, request.getChargeYear(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addObjectParam(EntityFields.deleted, false);
            builder.orderByAsc(EntityFields.propertyUsage);
            builder.orderByDesc(EntityFields.createdDate);

            log.info(" getOccupantProperties " + builder.getQryInfo());
            listOfPropertyCharges = builder.buildQry().getResultList();
            if (null == listOfPropertyCharges) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            log.info("total staff retrieved " + listOfPropertyCharges.size());
            List<PropertyChargeInfo> occupants = new ArrayList<>();
            if (!listOfPropertyCharges.isEmpty()) {
                for (PropertyCharge eachOne : listOfPropertyCharges) {
                    occupants.add(new PropertyChargeInfo(eachOne));
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setPropertyCharges(occupants);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getdepartments IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

}
