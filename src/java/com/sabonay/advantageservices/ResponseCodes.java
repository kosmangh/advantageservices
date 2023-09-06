package com.sabonay.advantageservices;

import com.ctrloption.utils.MsgFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class ResponseCodes {

    private static final Logger log = Logger.getLogger(ResponseCodes.class.getName());

    public static final String SUCCESS = "000";
    public static final String FAILED = "999";

    public static final String LANG_REQUIRED = "LANG_REQUIRED";
    public static final String ZONE_REQUIRED = "ZONE_REQUIRED";
    public static final String ZONE_UPDATE_FAILED = "ZONE_UPDATE_FAILED";
    public static final String ZONE_NOT_FOUND = "ZONE_NOT_FOUND";
    public static final String ZONE_CREATION_FAILED = "ZONE_CREATION_FAILED";
    public static final String ZONE_NAME_REQUIRED = "ZONE_NAME_REQUIRED";

    public static final String DEPARTMENT_NAME_REQUIRED = "DEPARTMENT_NAME_REQUIRED";
    public static final String DEPARTMENT_NOT_FOUND = "DEPARTMENT_NOT_FOUND";
    public static final String DEPARTMENT_DELETION_FAILED = "DEPARTMENT_DELETION_FAILED";
    public static final String DEPARTMENT_UPDATE_FAILED = "DEPARTMENT_UPDATE_FAILED";
    public static final String REMARKS_REQUIRED = "REMARKS_REQUIRED";

    public static final String REGION_UPDATE_FAILED = "REGION_UPDATE_FAILED";
    public static final String REGION_NAME_REQUIRED = "FIRST_NAME_REQUIRED";
    public static final String NO_REGION_FOUND = "Region not found.";

    public static final String NO_STAFF_FOUND = "No staff found";
    public static final String DOB_REQUIRED = "Date of birth required";
    public static final String USER_CREATION_FAILED = "USER_CREATION_FAILED";
    public static final String ACCOUNT_CREATION_FAILED = "Account creation failed";

    public static final String CITY_REQUIRED = "city required";
    public static final String LANGUAGE_NOT_SUPPORTED = "language not supported";
    public static final String MODIFIED_REQUIRED = "modified by required";
    public static final String CREATED_BY_REQUIRED = "Created by required";
    public static final String CREATED_BY_ROLE_REQUIRED = "created by role required";
    public static final String EMAIL_REQUIRED = "email required";
    public static final String EMAIL_INVALID = "email invalid";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String STATUS_REQUIRED = "Status is required";
    public static final String USER_ROLE_REQUIRED = "User role is required";
    public static final String PASSWORD_RESET_FAILED = "Password reset failed";
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String INACTIVE_ACCOUNT = "Inactive account;contact admin";
    public static final String SEARCH_BY_REQUIRED = "Search by is required";
    public static final String SEARCH_VALUE_REQUIRED = "Search value is required";
    public static final String PASSWORD_RESET_SUCCESSFUL = "Passworrd reset successful";

    public static final String ESTATE_CLASS_REQUIRED = "Estate class is required";
    public static final String ESTATE_LOCATION_REQUIRED = "Estate location is required";
    public static final String ESTATE_NAME_REQUIRED = "Estate name is required";
    public static final String LEASE_START_DATE_REQUIRED = "Lease start date is required";
    public static final String LEASE_END_DATE_REQUIRED = "Lease nnd date required";
    public static final String REGION_REQUIRED = "Region required";
    public static final String LAND_SIZE_REQUIRED = "Land size required.";
    public static final String ESTATE_ID_REQUIRED = "Estate id required";
    public static final String NO_ESTATE_FOUND = "No estate found.";
    public static final String ESTATE_UPDATE_FAILED = "Estate Update Failed";
    public static final String ESTATE_DELETION_FAILED = "Estate Deletion Failed";

    public static final String BLOCK_NAME_REQUIRED = "Block Name Required";
    public static final String ESTATE_BLOCK_CREATION_FAILED = "Estate Block Creation Failed";
    public static final String ESTATE_BLOCK_UPDATE_FAILED = "Estate Block Update Failed";
    public static final String NO_ESTATE_BLOCK_FOUND = "No Estate Block Found";
    public static final String ESTATE_BLOCK_ID_REQUIRED = "Estate Block Id Required";

    public static final String PROPERTY_CATEGORY_REQUIRED = "Property Category Required";
    public static final String PROPERTY_NO_REQUIRED = "Property No Required";
    public static final String PROPERTY_USAGE_REQUIRED = "Property Usage Required";
    public static final String ESTATE_PROPERTY_ID_REQUIRED = "Estate Property Id Required";

    public static final String OCCUPANT_TYPE_REQUIRED = "Occupant Type Required.";
    public static final String SUPERVISOR_ADDRESS_REQUIRED = "Supervisor Address Required";
    public static final String SUPERVISOR_EMAIL_REQUIRED = "Supervisor Email Required";
    public static final String SUPERVISOR_MOBILE_NO_REQUIRED = "Supervisor Mobile No Required";
    public static final String SUPERVISOR_NAME_REQUIRED = "Supervisor Name Required";
    public static final String GENDER_REQUIRED = "Gender Required";
    public static final String HOME_TOWN_REQUIRED = "Home Town Required";
    public static final String ID_NO_REQUIRED = "Id No Required";
    public static final String ID_TYPE_REQUIRED = "Id Type Required";
    public static final String NOK_REQUIRED = "Next of kin required";
    public static final String MARITAL_STATUS_REQUIRED = "Marital Status Required";
    public static final String NOK_EMAIL_REQUIRED = "NOK Email Required";
    public static final String NOK_ADDRESS_REQUIRED = "NOK Address Required";
    public static final String NOK_PHONE_NO_REQUIRED = "NOK Phone No Required";
    public static final String NOK_RELATIONSHIP_REQUIRED = "NOK Relationship Required";
    public static final String DATE_OF_BIRTH_REQUIRED = "Date Of Birth Required";
    public static final String BUSINESS_REGISTRATION_DATE_REQUIRED = "Business Registration Date Required";
    public static final String OCCUPANT_NAME_REQUIRED = "Occupant Name Required";
    public static final String OCCUPATION_REQUIRED = "Occupation Required";
    public static final String CORE_BUSINESS_REQUIRED = "Core Business Required";
    public static final String INDIVIDUAL_OR_INSTITUTION_REQUIRED = "Individual Or Institution Required";
    public static final String OCCUPANT_PROPERTY_UPDATE_FAILED = "Occupant property update failed";
    public static final String NO_OCCUPANT_FOUND = "No Occupant Found";
    public static final String NO_OCCUPANT_PROPERTY_FOUND = "No Occupant property found";
    public static final String OCCUPANT_PROPERTY_DELETION_FAILED = "Occupant Property Deletion Failed";
    public static final String OCCUPANT_UPDATE_FAILED = "Occupant Update Failed";

    public static final String RENT_YEAR_REQUIRED = "RENT_YEAR_REQUIRED";
    public static final String FIRST_CLASS_CHARGE_REQUIRED = "First Class Charge Required";
    public static final String SECOND_CLASS_CHARGE_REQUIRED = "Second Class Charge Required";
    public static final String THIRD_CLASS_CHARGE_REQUIRED = "Third Class Charge Required";
    public static final String NO_PROPERTY_CHARGE_FOUND = "No Property Charge Found";
    public static final String PROPERTY_REQUIRED = "PROPERTY_REQUIRED";
    public static final String ESTATE_REQUIRED = "ESTATE_REQUIRED";
    public static final String ESTATE_BLOCK_REQUIRED = "ESTATE_BLOCK_REQUIRED";
    public static final String BILLING_TYPE_REQUIRED = "BILLING_TYPE_REQUIRED";
    public static final String ERROR_SERVICING_REQUEST = "ERROR_SERVICING_REQUEST";
    public static final String BILL_DATE_REQUIRED = "BILL_DATE_REQUIRED";
    public static final String NO_PROPERTY_FOUND = "NO_PROPERTY_FOUND";
    public static final String RENT_MONTH_REQUIRED = "RENT_MONTH_REQUIRED";
    public static final String START_MONTH_REQQUIRED = "START_MONTH_REQQUIRED";
    public static final String START_YEAR_REQUIRED = "START_YEAR_REQUIRED";
    public static final String END_MONTH_REQUIRED = "END_MONTH_REQUIRED";
    public static final String END_YEAH_REQUIRED = "END_YEAH_REQUIRED";
    public static final String OCCUPANT_REQUIRED = "OCCUPANT_REQUIRED";
    public static final String PAYMENT_DATE_REQUIRED = "PAYMENT_DATE_REQUIRED";
    public static final String AMOUNT_REQUEIRED = "OCCUPANT_REQUIRED";

    public static final String ADDRESS_REQUIRED = "ADDRESS_REQUIRED";
    public static final String PIN_CHANGE_FAILED = "PIN_CHANGE_FAILED";
    public static final String CONTACT_PERSON = "CONTACT_PERSON";
    public static final String BRANCH_DELETION_FAILED = "BRANCH_DELETION_FAILED";
    public static final String WAREHOUSE_UPDATE_FAILED = "WAREHOUSE_UPDATE_FAILED";
    public static final String PROFILE_INACTIVE = "E13";
    public static final String TERMINAL_ID_REQUIRED = "E14";
    public static final String TIN_NO_REQUIRED = "E15";
    public static final String MERCHANT_INFO_NOT_FOUND = "E16";
    public static final String MERCHANT_ACTIVATION_OPT_MSG = "M17";
    public static final String OPT_REQUIRED = "E18";
    public static final String MERCHANT_NOT_ACTIVATED = "E19";
    public static final String MERCHANT_RESET_OPT_MSG = "M23";
    public static final String SELL_PRICE_REQUIRED = "SELL_PRICE_REQUIRED";
    public static final String MERCHANT_PIN_RESET_FAILED = "MERCHANT_PIN_RESET_FAILED";
    public static final String RECORD_ID_REQUIRED = "RECORD_ID_REQUIRED";
    public static final String MERCHANT_ACCOUNT_ACTIVATION_FAILED = "MERCHANT_ACCOUNT_ACTIVATION_FAILED";
    public static final String START_DATE_REQUIRED = "START_DATE_REQUIRED";
    public static final String END_DATE_REQUIRED = "END_DATE_REQUIRED";
    public static final String NO_TRANSACTION = "NO_TRANSACTION";
    public static final String DEPARTMENT_CREATION_FAILED = "DEPARTMENT_CREATION_FAILED";

    public static final String DELETED_BY_REQUIRED = "E44";
    public static final String PRODUCT_NAME_REQUIRED = "E45";

    public static final String NO_RECORD_FOUND = "E50";

    public static final String PURCHASE_STATUS_REQUIRED = "E51";
    public static final String WAREHOUSE_REQUIRED = "E52";

    public static final String PURCHASE_NOT_FOUND = "E54";

    public static final String PRODUCT_NOT_FOUND = "E58";

    public static final String USER_ROLE_CREATION_FAILED = "E59";
    public static final String STAFF_CREATION_FAILED = "E60";

    public static final String NO_WAREHOUSE_FOUND = "E61";
    public static final String PRODUCT_UPDATE_FAILED = "E62";

    public static final String BRANCH_CREATION_FAILED = "E63";
    public static final String COMPANY_NOT_FOUND = "E64";

    public static final String WAREHOUSE_DELETION_FAILED = "E65";

    public static final String PRODUCT_DELETION_FAILED = "E67";

    public static final String PRODUCT_REQUIRED = "E69";

    public static final String THIRD_PARTY_SYSTEM_ERROR = "E73";
    public static final String WAREHOUSE_NOT_FOUND = "E74";

    public static final String SAME_CR_DR_ACCOUNT = "E75";
    public static final String SURNAME_REQUIRED = "E76";

    public static final String COMPANY_ID_REQUIRED = "E79";
    public static final String TRANSACTION_LIMIT_EXCEEDED = "E80";
    public static final String COMPANY_ACCOUNT_INACTIVE = "E81";
    public static final String LOCAL_TO_FOREIGN_NOT_ALLOWED = "E83";

    public static final String RESTRICTION_ON_ACCOUNT = "E84";
    public static final String SENDING_RECEIVE_COUNTRY_NOT_SAME = "E85";

    public static final String COMPANY_CREATION_FAILED = "E88";
    public static final String OTHER_NAMES_REQUIRED = "E89";
    public static final String UNIT_REQUIRED = "E90";

    public static final String WAREHOUSE_NAME = "E93";
    public static final String MONTHLY_LIMIT_EXCEEDED = "E94";
    public static final String YEARLY_LIMIT_EXCEEDED = "E95";

    public static final String TRANSFER_NOT_ALLOWED_FROM_CHANNEL = "E96";

    public static final String PRODUCT_CREATION_FAILED = "E97";

    public static final String SUPPLIER_NOT_FOUND = "E98";
    public static final String SUPPLIER_UPDATE_FAILED = "E99";
    public static final String CONTACT_ADMINSTRATOR = "E100";

    public static final String FULL_NAME_REQUIRED = "E06";
    public static final String SOURCE_CODE_REQUIRED = "E07";
    public static final String ROLE_LEVEL_REQUIRED = "E08";
    public static final String CONTACT_NO_REQUIRED = "E09";
    public static final String MOBILE_NO_REQUIRED = "E10";
    public static final String USERNAME_REQUIRED = "E12";
    public static final String MODIFIED_BY_ROLE_REQUIRED = "E13";
    public static final String REQUEST_TOKEN_REQUIRED = "E14";
    public static final String NO_ESTATE_PROPERTY_FOUND = "E15";
    public static final String MERCHANT_NAME_REQUIRED = "E19";
    public static final String PERSONAL_MAIL_REQUIRED = "E20";
    public static final String MERCHANT_CITY_REQUIRED = "E21";
    public static final String COMPANY_UPDATE_FAILED = "E22";
    public static final String SHORT_NAME_REQUIRED = "E23";
    public static final String COMPANY_NAME_REQUIRED = "E24";
    public static final String LOCATION_REQUIRED = "E25";
    public static final String COMPANY_REQUIRED = "E26";
    public static final String TERMINAL_ID_GENERATION_FAILED = "E27";
    public static final String REQUEST_ID_REQUIRED = "E28";
    public static final String noRecordFound = "E30";
    public static final String MERCHANT_ADDRESS_REQUIRED = "E31";
    public static final String MERCHANT_ALREADY_EXIST = "E32";
    public static final String MERCHANT_EMAIL_REQUIRED = "E33";
    public static final String MERCHANT_MOBILE_NO_REQUIRED = "E34";
    public static final String VERIFY_STATUS_REQUIRED = "E37";
    public static final String REQUIRED_TYPE_REQUIRED = "E38";
    public static final String QR_STRING_BASE64_FAILED = "E39";
    public static final String TERMINAL_CREATION_FAILED = "E40";
    public static final String VERIFY_BY_REQUIRED = "E43";
    public static final String MERCHANT_CREATION_FAILED = "E45";

    public static void main(String[] args) {
        System.out.println(MsgFormatter.sentenceCase("LANG_REQUIRED"));
    }

    public static String getAppMsg(String msgCode, String langCode) {
        if (null == langCode || langCode.isEmpty()) {
//            log.info("Lang is empty or null;default to EN");
            langCode = "EN";
        }
        log.info("msg code " + msgCode + " lang " + langCode);
        Locale userLocale = new Locale(langCode.toLowerCase(), langCode.toUpperCase());
        ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);
        String msg = messages.getString(msgCode);
//        System.out.println("Msg:: " + msg);
        return msg;
    }

    public static String getAppMsg(String msgCode) {
        try {
            Locale userLocale = new Locale("EN", "EN");
            ResourceBundle messages = ResourceBundle.getBundle("messages", userLocale);
            String msg = messages.getString(msgCode);
            return msg;
        } catch (Exception e) {
            return "Unable to find language resource for " + msgCode;
        }
    }

    public static String formatResponseMsg(String msg) {
        try {
            if (null == msg || msg.isEmpty()) {
                return "";
            }
            return MsgFormatter.sentenceCase(msg);
        } catch (Exception e) {
            return "Unable to find language resource for " + msg;
        }
    }

}
