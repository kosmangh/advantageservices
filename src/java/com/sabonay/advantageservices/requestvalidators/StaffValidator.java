package com.sabonay.advantageservices.requestvalidators;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.useraccounts.Staff;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.useraccount.StaffRequest;
import com.sabonay.advantageservices.utils.AppConstants;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 16 Jul, 2023 12:01 pm
 */
public class StaffValidator {

    private static final Logger log = Logger.getLogger(StaffValidator.class.getName());

    public static Staff validateStaffCommonFields(StaffRequest request) throws IOException {

        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        Staff staff = new Staff();

        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                staff.setResponseCode(ResponseCodes.FAILED);
                staff.setResponseMsg(msg);
                return staff;
            }

            if (null == request.getDob()) {
                msg = ResponseCodes.getAppMsg("DOB_REQUIRED");
                staff.setResponseCode(ResponseCodes.FAILED);
                staff.setResponseMsg(msg);
                return staff;
            }
            if (StringUtils.isEmpty(request.getAddress())) {
                msg = ResponseCodes.getAppMsg("ADDRESS_REQUIRED");
                staff.setResponseCode(ResponseCodes.FAILED);
                staff.setResponseMsg(msg);
                return staff;
            }

            if (StringUtils.isEmpty(request.getEmail())) {
                msg = ResponseCodes.getAppMsg("EMAIL_REQUIRED");
                staff.setResponseCode(ResponseCodes.FAILED);
                staff.setResponseMsg(msg);
                return staff;
            }
            if (StringUtils.isEmpty(request.getFirstName())) {
                msg = ResponseCodes.getAppMsg("FIRST_NAME_REQUIRED");
                staff.setResponseCode(ResponseCodes.FAILED);
                staff.setResponseMsg(msg);
                return staff;
            }
            if (StringUtils.isEmpty(request.getGender())) {
                msg = ResponseCodes.getAppMsg("GENDER_REQUIRED");
                staff.setResponseCode(ResponseCodes.FAILED);
                staff.setResponseMsg(msg);
                return staff;
            }
            if (StringUtils.isEmpty(request.getLastName())) {
                msg = ResponseCodes.getAppMsg("LAST_NAME_REQUIRED");
                staff.setResponseCode(ResponseCodes.FAILED);
                staff.setResponseMsg(msg);
                return staff;
            }
            if (StringUtils.isEmpty(request.getMobileNo())) {
                msg = ResponseCodes.getAppMsg("MOBILE_REQUIRED");
                staff.setResponseCode(ResponseCodes.FAILED);
                staff.setResponseMsg(msg);
                return staff;
            }
            if (StringUtils.isEmpty(request.getOfficeContactNo())) {
                msg = ResponseCodes.getAppMsg("OFFICE_MOBILE_REQUIRED");
                staff.setResponseCode(ResponseCodes.FAILED);
                staff.setResponseMsg(msg);
                return staff;
            }

            if (StringUtils.isEmpty(request.getDepartmentId())) {
                msg = ResponseCodes.getAppMsg("DEPARTMENT_REQUIRED");
                staff.setResponseCode(ResponseCodes.FAILED);
                staff.setResponseMsg(msg);
                return staff;
            }
            if (StringUtils.isEmpty(request.getRegionId())) {
                msg = ResponseCodes.getAppMsg("REGION_REQUIRED");
                staff.setResponseCode(ResponseCodes.FAILED);
                staff.setResponseMsg(msg);
                return staff;
            }
            staff.setAddress(request.getAddress());
            staff.setDob(request.getDob());
            log.info("staff.getDob() :: " + staff.getDob());

            staff.setEmail(request.getEmail());
            staff.setFirstName(request.getFirstName());
            staff.setGender(request.getGender());
            staff.setLastName(request.getLastName());
            staff.setMobileNo(request.getMobileNo());
            staff.setOfficeContactNo(request.getOfficeContactNo());
            staff.setStatus(AppConstants.NEW);
            staff.setGender(request.getGender());
            AppLogger.printPayload(log, "After validation ", staff);
            log.info("CreateStaffrRequest validation ends");
            msg = ResponseCodes.getAppMsg(ResponseCodes.SUCCESS);
            staff.setResponseCode(ResponseCodes.SUCCESS);
            staff.setResponseMsg(msg);
            return staff;
        } catch (IOException e) {
            AppLogger.error(log, e, "validateRolerUsers exception error");
            msg = ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST);
            staff.setResponseCode(ResponseCodes.FAILED);
            staff.setResponseMsg(msg);
            return staff;
        }
    }
}
