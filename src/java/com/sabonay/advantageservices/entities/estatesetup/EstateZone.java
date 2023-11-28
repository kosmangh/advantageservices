package com.sabonay.advantageservices.entities.estatesetup;

import com.ctrloption.entities.super_classes.EntityCrud;
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Daud Ainoo
 * @Company CtrlOption
 * @Contact 0245 293945
 * @Website ctrlOpton.com
 * @date Sat 01 Jul, 2023 07:34 am
 */
@Entity
@Cacheable(false)
@Table(name = "estate_zone")
public class EstateZone extends EntityCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "zone_name")
    private String zoneName;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "other_contact_no")
    private String otherContactNo;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_branch")
    private String bankBranch;

    @Column(name = "website")
    private String website;

    @Column(name = "email")
    private String email;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstateZone)) {
            return false;
        }
        EstateZone other = (EstateZone) object;
        return !((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId)));
    }

    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getOtherContactNo() {
        return otherContactNo;
    }

    public void setOtherContactNo(String otherContactNo) {
        this.otherContactNo = otherContactNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
//</editor-fold>

}
