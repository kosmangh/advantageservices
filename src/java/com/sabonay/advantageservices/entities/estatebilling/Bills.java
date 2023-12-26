package com.sabonay.advantageservices.entities.estatebilling;

import com.ctrloption.entities.super_classes.EntityCrud;
import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.entities.occupancy.Occupant;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Mon 14 Aug, 2023 00:32 am
 */
@Entity
@Table(name = "bills")
@Cacheable(false)
public class Bills extends EntityCrud implements Serializable {

    @JoinColumn(name = "property")
    @ManyToOne
    private EstateProperty estateProperty;

    @JoinColumn(name = "occupant")
    @ManyToOne
    private Occupant occupant;

    @Column(name = "bill_amount")
    private Double billAmount;

    @Column(name = "bill_penalty_amount")
    private Double billPenaltyAmount;

    @Column(name = "bill_amount_paid")
    private Double billAmountPaid;

    @Column(name = "bill_type")
    private String billType;

    @Column(name = "entry_type")
    private String entryType;

    @Column(name = "bill_status")
    private String billStatus;

    @Column(name = "bill_month")
    private String billMonth;

    @Column(name = "bill_year")
    private Integer billYear;

    @Column(name = "last_cr_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastCrDate;

    @Column(name = "last_cr_recorded_by")
    private String lastCrRecordedBy;

    @Column(name = "last_dr_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastDrDate;

    @Column(name = "last_dr_recorded_by")
    private String lastDrRecordedBy;


    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropertyCharge)) {
            return false;
        }
        Bills other = (Bills) object;
        return !((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId)));
    }

    public EstateProperty getEstateProperty() {
        return estateProperty;
    }

    public void setEstateProperty(EstateProperty estateProperty) {
        this.estateProperty = estateProperty;
    }

    public Occupant getOccupant() {
        return occupant;
    }

    public void setOccupant(Occupant occupant) {
        this.occupant = occupant;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
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
