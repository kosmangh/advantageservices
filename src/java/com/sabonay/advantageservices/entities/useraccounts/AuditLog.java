package com.sabonay.advantageservices.entities.useraccounts;

import com.ctrloption.entities.super_classes.EntityCrud;
import com.sabonay.advantageservices.entities.estatesetup.EstateZone;
import com.sabonay.advantageservices.entities.estatesetup.Region;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Daud Ainoo
 * @Company CtrlOpton
 * @Contact 0245 293945
 * @Website ctrlOpton.com
 * @date Sun 12 Mar, 2023
 * @time 20.58.29 pm
 */
@Entity
@Table(name = "audit_log")
public class AuditLog extends EntityCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "region")
    @ManyToOne
    private Region region;

    @JoinColumn(name = "estate_zone")
    @ManyToOne
    private EstateZone estateZone;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username")
    private String username;

    @Column(name = "user_activity")
    private String userActivity;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "ui")
    private String ui;

    @Column(name = "ip_address")
    private String ipAddress;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public EstateZone getEstateZone() {
        return estateZone;
    }

    public void setEstateZone(EstateZone estateZone) {
        this.estateZone = estateZone;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }


}
