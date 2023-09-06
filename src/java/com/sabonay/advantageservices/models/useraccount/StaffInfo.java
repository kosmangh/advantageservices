package com.sabonay.advantageservices.models.useraccount;

import com.ctrloption.formating.DateTimeUtils;
import com.sabonay.advantageservices.entities.useraccounts.Staff;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 16 Jul, 2023 07:52 am
 */
public class StaffInfo extends StaffCommonInfo {

    private static final Logger log = Logger.getLogger(StaffInfo.class.getName());

    private String createdBy;
    private String lastModifiedBy;
    private String userRole;
    private String lastLoginDate;
    private String password;
    private boolean resetPassword;
    private String accessLevel;
    private String fullName;
    private String regionName;
    private String departmentName;
    private String responseMsg;
    private String responseCode;
    private String accountCreatedBy;
    private String createdDate;
    private String lastModifiedDate;
    private String accountCreatedDate;

    public StaffInfo() {
        super();
    }

    public StaffInfo(Staff data) {
        try {
            recordId = data.getRecordId();
            accessLevel = data.getAccessLevel();
            address = data.getAddress();
            createdBy = data.getCreatedBy();
            createdDate = DateTimeUtils.formatDate(data.getCreatedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
            lastLoginDate = DateTimeUtils.formatDate(data.getLastLoginDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
            departmentId = data.getDepartment().getRecordId();
            departmentName = data.getDepartment().getDepartmentName();
            dob = data.getDob();
            username = data.getUsername();
            userRole = data.getUserRole();
            email = data.getEmail();
            firstName = data.getFirstName();
            fullName = data.getFullName();
            gender = data.getGender();
            lastModifiedBy = data.getLastModifiedBy();
            lastName = data.getLastName();
            mobileNo = data.getMobileNo();
            officeContactNo = data.getOfficeContactNo();
            regionId = data.getRegion().getRecordId();
            regionName = data.getRegion().getRegionName();
            resetPassword = data.isResetPassword();
            status = data.getStatus();
            accountCreatedBy = data.getAccountCreatedBy();
            accountCreatedDate = DateTimeUtils.formatDate(data.getAccountCreatedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
        } catch (Exception e) {
            AppLogger.error(log, e, "error StaffInfo contructor");
        }
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getAccountCreatedBy() {
        return accountCreatedBy;
    }

    public void setAccountCreatedBy(String accountCreatedBy) {
        this.accountCreatedBy = accountCreatedBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(String accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }

}
