package com.sabonay.advantageservices.models.occupancy;

import com.ctrloption.formating.DateTimeUtils;
import com.sabonay.advantageservices.entities.occupancy.OccupantProperty;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 06 Aug, 2023 15:48 pm
 */
public class OccupantPropertyInfo extends OccupantPropertySuper {

    private static final Logger log = Logger.getLogger(OccupantPropertyInfo.class.getName());
    private boolean institutional;

    private String estateId;
    private String blockId;

    private String occupantName;
    private String mobileNo;
    private String emailAddress;
    private String address;

    private String propertyName;
    private String propertySize;
    private String propertyNo;
    private String propertyUsage;
    private String propertyCategory;

    private String blockName;
    private String estateName;

    private String totalDuration;
    private String validity;
    private String paymentType;
    private String durationLeft;

    private String createdDate;
    private String lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
    private String recordId;
    private String institutionalDisplayName;

    public OccupantPropertyInfo() {
        super();
    }

    public OccupantPropertyInfo(OccupantProperty data) {
        try {
            recordId = data.getRecordId();
            mobileNo = data.getOccupant().getMobileNo();
            emailAddress = data.getOccupant().getEmailAddress();
            address = data.getOccupant().getLocalAddress();
            paymentType = data.getPaymentType();
            totalDuration = data.getTotalDuration();
            validity = data.getValidity();
            durationLeft = data.getDurationLeft();
            institutionalDisplayName = data.getOccupant().getInstitutionalDisplayName();
            propertySize = String.valueOf(data.getEstateProperty().getPropertyLandSize());
            propertyNo = data.getEstateProperty().getPropertyNumber();
            propertyUsage = data.getEstateProperty().getPropertyUsage();
            propertyCategory = data.getEstateProperty().getPropertyCategory();
            institutional = data.getOccupant().isInstitutional();
            blockId = data.getEstateProperty().getEstateBlock().getRecordId();
            blockName = data.getEstateProperty().getEstateBlock().getBlockName();
            estateId = data.getEstateProperty().getEstateBlock().getEstate().getRecordId();
            estateName = data.getEstateProperty().getEstateBlock().getEstate().getEstateName();
            firstDateOfOccupancy = data.getFirstDateOfOccupancy();
            lastDateOfOccupancy = data.getLastDateOfOccupancy();
            occupantId = data.getOccupant().getRecordId();
            occupantName = data.getOccupant().getOccupantName();
            occupationType = data.getOccupationType();
            propertyId = data.getEstateProperty().getRecordId();
            propertyName = data.getEstateProperty().getPropertyName();
            totalDuration = data.getTotalDuration();
            createdBy = data.getCreatedBy();
            createdDate = DateTimeUtils.formatDate(data.getCreatedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
            lastModifiedBy = data.getLastModifiedBy();
            lastModifiedDate = DateTimeUtils.formatDate(data.getLastModifiedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
        } catch (Exception e) {
            AppLogger.error(log, e, "OccupantPropertyInfo error");
        }
    }
    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">

    public boolean isInstitutional() {
        return institutional;
    }

    public void setInstitutional(boolean institutional) {
        this.institutional = institutional;
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getOccupantName() {
        return occupantName;
    }

    public void setOccupantName(String occupantName) {
        this.occupantName = occupantName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getInstitutionalDisplayName() {
        return institutionalDisplayName;
    }

    public void setInstitutionalDisplayName(String institutionalDisplayName) {
        this.institutionalDisplayName = institutionalDisplayName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertySize() {
        return propertySize;
    }

    public void setPropertySize(String propertySize) {
        this.propertySize = propertySize;
    }

    public String getPropertyNo() {
        return propertyNo;
    }

    public void setPropertyNo(String propertyNo) {
        this.propertyNo = propertyNo;
    }

    public String getPropertyUsage() {
        return propertyUsage;
    }

    public void setPropertyUsage(String propertyUsage) {
        this.propertyUsage = propertyUsage;
    }

    public String getPropertyCategory() {
        return propertyCategory;
    }

    public void setPropertyCategory(String propertyCategory) {
        this.propertyCategory = propertyCategory;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDurationLeft() {
        return durationLeft;
    }

    public void setDurationLeft(String durationLeft) {
        this.durationLeft = durationLeft;
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

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
//</editor-fold>

}
