package com.sabonay.advantageservices.models;

import com.ctrloption.formating.DateTimeUtils;
import com.sabonay.advantageservices.entities.useraccounts.AuditLog;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Tue 25 Jul, 2023 05:08 am
 */
public class AuditLogInfo {

    private static final Logger log = Logger.getLogger(AuditLogInfo.class.getName());

    private String regionId;
    private String regionName;
    private String zoneId;
    private String zoneName;
    private String fullName;
    private String username;
    private String userActivity;
    private String userRole;
    private String ui;
    private String ip;
    private String createdBy;
    private String createdDate;

    public AuditLogInfo(AuditLog data) {
        try {
//            regionId = data.getRecordId();
//            regionName = data.getRegion().getRegionName();
            fullName = data.getFullName();
            userActivity = data.getUserActivity();
            createdBy = data.getCreatedBy();
            createdDate = DateTimeUtils.formatForTimeAndDate(data.getCreatedDate());
            ip = data.getIpAddress();
            username = data.getUsername();
            userRole = data.getUserRole();
        } catch (Exception e) {
            AppLogger.error(log, e, "AuditLogInfo error");
        }
    }

    public AuditLogInfo() {
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(String userActivity) {
        this.userActivity = userActivity;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUi() {
        return ui;
    }

    public void setUi(String ui) {
        this.ui = ui;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}
