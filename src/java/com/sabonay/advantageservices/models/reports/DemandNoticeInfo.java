package com.sabonay.advantageservices.models.reports;

import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
import com.sabonay.advantageservices.utils.AppLogger;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import org.apache.log4j.Logger;

/**
 *
 * @author dainoo
 */
public class DemandNoticeInfo {

    private static final Logger log = Logger.getLogger(DemandNoticeInfo.class.getName());

    private String lessee;
    private String streetName;
    private String fileNo;
    private String propertyNo;
    private String propertyClass;
    private String location;
    private Double currentCharge;
    private Double arrears;
    private Double totalAmountDue;
    private Date lesseExpiryDate;

    public DemandNoticeInfo(PropertyLedger ledger, Double currentAmount, Double previousArrears, Date expiryDate) {
        try {
            if (Objects.isNull(ledger.getOccupant())) {
                lessee = "Name not found";
            } else {
                lessee = ledger.getOccupant().getOccupantName();
            }
            streetName = ledger.getEstateProperty().getEstateBlock().getBlockName();
            fileNo = ledger.getEstateProperty().getPropertyName();
            propertyNo = ledger.getEstateProperty().getPropertyNumber();
            propertyClass = ledger.getEstateProperty().getEstateBlock().getEstate().getEstateClass().replaceAll("_", " ");
            location = ledger.getEstateProperty().getEstateBlock().getEstate().getEstateName();
            currentCharge = currentAmount;
            arrears = previousArrears;
            totalAmountDue = currentCharge + arrears;
            lesseExpiryDate = Optional.ofNullable(expiryDate).orElse(null);
        } catch (Exception e) {
            AppLogger.error(log, e, "error processing " + lessee + "'s " + fileNo + " demand notice");
        }
    }

    public DemandNoticeInfo() {
    }

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
