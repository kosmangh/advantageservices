package com.sabonay.advantageservices.models.estatesetup;

import com.sabonay.advantageservices.entities.estatesetup.EstateZone;
import com.sabonay.advantageservices.models.CommonFields;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company CtrlOpton
 * @Contact 0245 293945
 * @Website ctrlOpton.com
 * @date Sat 18 Mar, 2023
 * @time 04.54.56 am
 */
public class ZoneInfo extends CommonFields {

    private static final Logger log = Logger.getLogger(ZoneInfo.class.getName());

    private String zoneName;
    private String remarks;
    private String address;
    private String contactNo;
    private String otherContactNo;
    private String accountNo;
    private String bankName;
    private String bankBranch;
    private String website;
    private String email;

    public ZoneInfo() {
    }

    public ZoneInfo(EstateZone data) {
        try {
            this.recordId = data.getRecordId();
            this.zoneName = data.getZoneName();
            this.remarks = data.getRemarks();
            this.address = data.getAddress();
            this.contactNo = data.getContactNo();
            this.otherContactNo = data.getOtherContactNo();
            this.accountNo = data.getAccountNo();
            this.bankName = data.getBankName();
            this.bankBranch = data.getBankBranch();
            this.website = data.getWebsite();
            this.email = data.getEmail();
            this.createdBy = data.getCreatedBy();
            this.createdDate = data.getCreatedDate();
            this.lastModifiedBy = data.getLastModifiedBy();
            this.lastModifiedDate = data.getLastModifiedDate();
        } catch (Exception e) {
            AppLogger.error(log, e, "ZoneInfo constructor exeception");
        }
    }

//<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public String getOtherContactNo() {
        return otherContactNo;
    }

    public void setOtherContactNo(String otherContactNo) {
        this.otherContactNo = otherContactNo;
    }

    public String getAccountNo() {
        return accountNo;
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

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
//</editor-fold>

}
