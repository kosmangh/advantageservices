package com.sabonay.advantageservices.models.reports;

import com.ctrloption.formating.NumberFormattingUtils;
import com.sabonay.advantageservices.entities.estatebilling.Bills;
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
    private String entryType;
    private String fileNo;
    private String propertyNo;
    private String propertyClass;
    private String location;
    private Double currentCharge;
    private Double arrears;
    private Double totalAmountDue;
    private Date lesseExpiryDate;
    private String currentChargeString;
    private String arrearsString;
    private String totalAmountDueString;
    private String arrearsLabel;
    private String totalAmountDueLabel;


    public DemandNoticeInfo(Bills bill, Double[] calculatedAmounts, Date expiryDate) {
        try {
            if (Objects.isNull(bill.getOccupant())) {
                lessee = "Name not found";
            } else {
                lessee = bill.getOccupant().getOccupantName();
            }
            streetName = bill.getEstateProperty().getEstateBlock().getBlockName();
            fileNo = bill.getEstateProperty().getPropertyName();
            propertyNo = bill.getEstateProperty().getPropertyNumber();
            propertyClass = bill.getEstateProperty().getEstateBlock().getEstate().getEstateClass().replaceAll("_", " ");
            location = bill.getEstateProperty().getEstateBlock().getEstate().getEstateName();
            entryType = bill.getEntryType();
            currentCharge = calculatedAmounts[0];
            arrears = calculatedAmounts[1];

            if (currentCharge < 0) {
//                currentCharge = 0.0;
                arrears = calculatedAmounts[0] + calculatedAmounts[1];
            }
            totalAmountDue = currentCharge + arrears;
            arrearsLabel = arrears < 0 ? "Credit bal." : "Arrears";
            totalAmountDueLabel = totalAmountDue < 0 ? "Total Balance Due" : "Total Amount Due";
            currentChargeString = NumberFormattingUtils.formatDouble(currentCharge, 2);
            arrearsString = NumberFormattingUtils.formatDouble(arrears, 2);
            totalAmountDueString = NumberFormattingUtils.formatDouble(totalAmountDue, 2);
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

    public String getEntryType() {
        return entryType;
    }

    public String getArrearsLabel() {
        return arrearsLabel;
    }

    public void setArrearsLabel(String arrearsLabel) {
        this.arrearsLabel = arrearsLabel;
    }

    public String getCurrentChargeString() {
        return currentChargeString;
    }

    public void setCurrentChargeString(String currentChargeString) {
        this.currentChargeString = currentChargeString;
    }

    public String getArrearsString() {
        return arrearsString;
    }

    public void setArrearsString(String arrearsString) {
        this.arrearsString = arrearsString;
    }

    public String getTotalAmountDueString() {
        return totalAmountDueString;
    }

    public void setTotalAmountDueString(String totalAmountDueString) {
        this.totalAmountDueString = totalAmountDueString;
    }

    public String getTotalAmountDueLabel() {
        return totalAmountDueLabel;
    }

    public void setTotalAmountDueLabel(String totalAmountDueLabel) {
        this.totalAmountDueLabel = totalAmountDueLabel;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
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
