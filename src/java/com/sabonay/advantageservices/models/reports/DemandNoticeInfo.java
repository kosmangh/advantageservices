package com.sabonay.advantageservices.models.reports;

import java.util.Date;

/**
 *
 * @author dainoo
 */
public class DemandNoticeInfo {
    private String lessee;
    private String streetName;
    private String fileNo;
    private String propertyNo;
    private String propertyClass;
    private String location;
    private Double currentCharge;
    private Double arrears;
    private Double totalAmountDue;
    private Date  lesseExpiryDate;
    
    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public String getLessee() {
        return lessee;
    }

    public void setLessee(String lessee) {
        this.lessee = lessee;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getFileNo() {
        return fileNo;
    }

    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    public String getPropertyNo() {
        return propertyNo;
    }

    public void setPropertyNo(String propertyNo) {
        this.propertyNo = propertyNo;
    }

    public String getPropertyClass() {
        return propertyClass;
    }

    public void setPropertyClass(String propertyClass) {
        this.propertyClass = propertyClass;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getCurrentCharge() {
        return currentCharge;
    }

    public void setCurrentCharge(Double currentCharge) {
        this.currentCharge = currentCharge;
    }

    public Double getArrears() {
        return arrears;
    }

    public void setArrears(Double arrears) {
        this.arrears = arrears;
    }

    public Double getTotalAmountDue() {
        return totalAmountDue;
    }

    public void setTotalAmountDue(Double totalAmountDue) {
        this.totalAmountDue = totalAmountDue;
    }

    public Date getLesseExpiryDate() {
        return lesseExpiryDate;
    }

    public void setLesseExpiryDate(Date lesseExpiryDate) {
        this.lesseExpiryDate = lesseExpiryDate;
    }
//</editor-fold>
    
    
}
