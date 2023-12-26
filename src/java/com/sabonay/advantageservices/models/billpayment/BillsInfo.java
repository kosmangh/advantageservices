package com.sabonay.advantageservices.models.billpayment;

import com.ctrloption.formating.DateTimeUtils;
import com.sabonay.advantageservices.entities.estatebilling.Bills;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Mon 18 Dec, 2023 05:01 am
 */
public class BillsInfo extends BillSuper {

    private static final Logger log = Logger.getLogger(BillsInfo.class.getName());

    private String createdDate;
    private String createdBy;
    private String narration;
    private String lastModifiedDate;
    private String lastModifiedBy;
    private Double arrears = 0.0;

    public BillsInfo() {
        super();
    }

    public BillsInfo(Bills data) {
        try {
            recordId = data.getRecordId();
            occupant = data.getOccupant().getRecordId();
            property = data.getEstateProperty().getRecordId();
            billAmount = data.getBillAmount();
            billAmountPaid = data.getBillAmountPaid();
            billPenaltyAmount = data.getBillPenaltyAmount();
            arrears = (billAmount + billPenaltyAmount) - billAmountPaid;
            billStatus = data.getBillStatus();
            billType = data.getBillType();
            billYear = data.getBillYear();
            narration = AppUtils.generateBillNarration(billType, recordId, billYear);
            billMonth = data.getBillMonth();
            createdBy = data.getCreatedBy();
            createdDate = DateTimeUtils.formatDate(data.getCreatedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
            entryType = data.getEntryType();
            lastCrDate = data.getLastCrDate();
            lastCrRecordedBy = data.getLastCrRecordedBy();
            lastDrDate = data.getLastDrDate();
            lastDrRecordedBy = data.getLastDrRecordedBy();
            lastModifiedBy = data.getLastModifiedBy();
            lastModifiedDate = DateTimeUtils.formatDate(data.getLastModifiedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
        } catch (Exception e) {
            AppLogger.error(log, e, "PropertyLedgerInfo exception error");
        }
    }

    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Double getArrears() {
        return arrears;
    }

    public void setArrears(Double arrears) {
        this.arrears = arrears;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
//</editor-fold>

}
