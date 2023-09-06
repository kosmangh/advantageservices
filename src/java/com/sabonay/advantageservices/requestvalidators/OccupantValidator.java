package com.sabonay.advantageservices.requestvalidators;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.occupancy.Occupant;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 05 Aug, 2023 16:42 pm
 */
public class OccupantValidator {

    private static final Logger log = Logger.getLogger(OccupantValidator.class.getName());

    public static Occupant validateOccupantCommonFields(OccupantRequest request) throws IOException {
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        Occupant occupant = new Occupant();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                occupant.setResponseCode(ResponseCodes.FAILED);
                occupant.setResponseMsg(msg);
                return occupant;
            }

            if (null == request.getInstitutional()) {
                msg = ResponseCodes.INDIVIDUAL_OR_INSTITUTION_REQUIRED;
                occupant.setResponseCode(ResponseCodes.FAILED);
                occupant.setResponseMsg(msg);
                return occupant;
            }
            if (request.getInstitutional()) {
                if ((null == request.getSupervisorAddress() || StringUtils.isEmpty(request.getSupervisorAddress()))) {
                    msg = ResponseCodes.SUPERVISOR_ADDRESS_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }
                if ((null == request.getSupervisorEmail() || StringUtils.isEmpty(request.getSupervisorEmail()))) {
                    msg = ResponseCodes.SUPERVISOR_EMAIL_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }
                if ((null == request.getSupervisorMobileNo() || StringUtils.isEmpty(request.getSupervisorMobileNo()))) {
                    msg = ResponseCodes.SUPERVISOR_MOBILE_NO_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }
                if ((null == request.getSupervisorName() || StringUtils.isEmpty(request.getSupervisorName()))) {
                    msg = ResponseCodes.SUPERVISOR_NAME_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }
            }
            if (!request.getInstitutional()) {
                log.info("individual occcupant validations");
                if (null == request.getGender() || StringUtils.isEmpty(request.getGender())) {
                    msg = ResponseCodes.GENDER_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }
                if (null == request.getHomeTown() || StringUtils.isEmpty(request.getHomeTown())) {
                    msg = ResponseCodes.HOME_TOWN_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }
                if (!request.getInstitutional() && (null == request.getIdNo() || StringUtils.isEmpty(request.getIdNo()))) {
                    msg = ResponseCodes.ID_NO_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }

                if (null == request.getIdType() || StringUtils.isEmpty(request.getIdType())) {
                    msg = ResponseCodes.ID_TYPE_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }
                if (null == request.getNextOfKin() || StringUtils.isEmpty(request.getMobileNo())) {
                    msg = ResponseCodes.NOK_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }

                if ((null == request.getMaritalStatus() || StringUtils.isEmpty(request.getMaritalStatus()))) {
                    msg = ResponseCodes.MARITAL_STATUS_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }

                if ((null == request.getNokAddress() || StringUtils.isEmpty(request.getNokAddress()))) {
                    msg = ResponseCodes.NOK_ADDRESS_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }
                if ((null == request.getNokEmail() || StringUtils.isEmpty(request.getNokEmail()))) {
                    msg = ResponseCodes.NOK_EMAIL_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }
                if ((null == request.getNokPhone() || StringUtils.isEmpty(request.getNokPhone()))) {
                    msg = ResponseCodes.NOK_PHONE_NO_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }

                if ((null == request.getRelationship() || StringUtils.isEmpty(request.getRelationship()))) {
                    msg = ResponseCodes.NOK_RELATIONSHIP_REQUIRED;
                    occupant.setResponseCode(ResponseCodes.FAILED);
                    occupant.setResponseMsg(msg);
                    return occupant;
                }

            }

            if (null == request.getDateOfBirth()) {
                msg = ResponseCodes.DATE_OF_BIRTH_REQUIRED;
                if (request.getInstitutional()) {
                    msg = ResponseCodes.BUSINESS_REGISTRATION_DATE_REQUIRED;
                }
                occupant.setResponseCode(ResponseCodes.FAILED);
                occupant.setResponseMsg(msg);
                return occupant;
            }

            if (null == request.getEmailAddress() || StringUtils.isEmpty(request.getEmailAddress())) {
                msg = ResponseCodes.EMAIL_INVALID;
                occupant.setResponseCode(ResponseCodes.FAILED);
                occupant.setResponseMsg(msg);
                return occupant;
            }

            if (null == request.getLocalAddress() || StringUtils.isEmpty(request.getLocalAddress())) {
                msg = ResponseCodes.ADDRESS_REQUIRED;
                occupant.setResponseCode(ResponseCodes.FAILED);
                occupant.setResponseMsg(msg);
                return occupant;
            }

            if (null == request.getMobileNo() || StringUtils.isEmpty(request.getMobileNo())) {
                msg = ResponseCodes.MOBILE_NO_REQUIRED;
                occupant.setResponseCode(ResponseCodes.FAILED);
                occupant.setResponseMsg(msg);
                return occupant;
            }

            if ((null == request.getOccupantName() || StringUtils.isEmpty(request.getOccupantName()))) {
                msg = ResponseCodes.OCCUPANT_NAME_REQUIRED;
                occupant.setResponseCode(ResponseCodes.FAILED);
                occupant.setResponseMsg(msg);
                return occupant;
            }
            if ((null == request.getOccupantType() || StringUtils.isEmpty(request.getOccupantType()))) {
                msg = ResponseCodes.OCCUPANT_TYPE_REQUIRED;
                occupant.setResponseCode(ResponseCodes.FAILED);
                occupant.setResponseMsg(msg);
                return occupant;
            }

            if (null == request.getOccupation() || StringUtils.isEmpty(request.getOccupation())) {
                msg = ResponseCodes.OCCUPATION_REQUIRED;
                if (request.getInstitutional()) {
                    msg = ResponseCodes.CORE_BUSINESS_REQUIRED;
                }
                occupant.setResponseCode(ResponseCodes.FAILED);
                occupant.setResponseMsg(msg);
                return occupant;
            }
            log.info("request.getDateOfBirth()" + request.getDateOfBirth());
            log.info("validateOccupantCommonFields passed validation;set commons fields to Occupant entity");
            occupant.setDateOfBirth(request.getDateOfBirth());
            occupant.setEmailAddress(request.getEmailAddress());
            occupant.setGender(request.getGender());
            occupant.setHomeTown(request.getHomeTown());
            occupant.setIdNo(request.getIdNo());
            occupant.setIdType(request.getIdType());
            occupant.setInstitutional(request.getInstitutional());
            occupant.setLocalAddress(request.getLocalAddress());
            occupant.setMaritalStatus(request.getMaritalStatus());
            occupant.setMobileNo(request.getMobileNo());
            occupant.setTelephoneNumber(request.getMobileNo());
            occupant.setNationality(request.getNationality());
            occupant.setNextOfKin(request.getNextOfKin());
            occupant.setNokAddress(request.getNokAddress());
            occupant.setNokEmail(request.getNokEmail());
            occupant.setNokPhone(request.getNokPhone());
            occupant.setOccupantName(request.getOccupantName());
            occupant.setOccupantType(request.getOccupantType());
            occupant.setOccupation(request.getOccupation());
            occupant.setRelationship(request.getRelationship());
            occupant.setSupervisorAddress(request.getSupervisorAddress());
            occupant.setSupervisorEmail(request.getSupervisorEmail());
            occupant.setSupervisorMobileNo(request.getSupervisorMobileNo());
            occupant.setSupervisorName(request.getSupervisorName());
            occupant.setRemarks(request.getRemarks());
            AppLogger.printPayload(log, "After validation ", occupant);
            msg = ResponseCodes.SUCCESS;
            occupant.setResponseCode(ResponseCodes.SUCCESS);
            occupant.setResponseMsg(msg);
            return occupant;
        } catch (IOException e) {
            AppLogger.error(log, e, "validateRolerUsers exception error");
            msg = ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST);
            occupant.setResponseCode(ResponseCodes.FAILED);
            occupant.setResponseMsg(msg);
            return occupant;
        }
    }
}
