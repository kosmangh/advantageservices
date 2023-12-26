package com.sabonay.advantageservices.models.billpayment;

import java.util.Date;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Mon 18 Dec, 2023 04:58 am
 */
public class BillSuper {

    protected String recordId;
    protected String occupant;
    protected String property;
    protected Double billAmount;
    protected Double billPenaltyAmount;
    protected Double billAmountPaid;
    protected String billType;
    protected String billMonth;
    protected String entryType;
    protected String billStatus;
    protected Integer billYear;
    protected Date lastCrDate;
    protected String lastCrRecordedBy;
    protected Date lastDrDate;
    protected String lastDrRecordedBy;

    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public String getOccupant() {
        return occupant;
    }

    public void setOccupant(String occupant) {
        this.occupant = occupant;
    }

    public String getProperty() {
        return property;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public Double getBillPenaltyAmount() {
        return billPenaltyAmount;
    }

    public void setBillPenaltyAmount(Double billPenaltyAmount) {
        this.billPenaltyAmount = billPenaltyAmount;
    }

    public Double getBillAmountPaid() {
        return billAmountPaid;
    }

    public void setBillAmountPaid(Double billAmountPaid) {
        this.billAmountPaid = billAmountPaid;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public Integer getBillYear() {
        return billYear;
    }

    public void setBillYear(Integer billYear) {
        this.billYear = billYear;
    }

    public Date getLastCrDate() {
        return lastCrDate;
    }

    public void setLastCrDate(Date lastCrDate) {
        this.lastCrDate = lastCrDate;
    }

    public String getLastCrRecordedBy() {
        return lastCrRecordedBy;
    }

    public void setLastCrRecordedBy(String lastCrRecordedBy) {
        this.lastCrRecordedBy = lastCrRecordedBy;
    }

    public Date getLastDrDate() {
        return lastDrDate;
    }

    public void setLastDrDate(Date lastDrDate) {
        this.lastDrDate = lastDrDate;
    }

    public String getLastDrRecordedBy() {
        return lastDrRecordedBy;
    }

    public void setLastDrRecordedBy(String lastDrRecordedBy) {
        this.lastDrRecordedBy = lastDrRecordedBy;
    }
//</editor-fold>

}
