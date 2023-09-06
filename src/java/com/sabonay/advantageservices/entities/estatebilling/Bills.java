package com.sabonay.advantageservices.entities.estatebilling;

import com.ctrloption.entities.super_classes.EntityCrud;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

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
//@Data
public class Bills extends EntityCrud implements Serializable {

    @Column(name = "billnumber")
    private String billnumber;

    @Column(name = "estate_property")
    private String estateProperty;

    @Column(name = "property_occupant")
    private String propertyOccupant;

    @Column(name = "date_of_record_entry")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfRecordEntry;

    @Size(max = 255)
    @Column(name = "bill_detail")
    private String billDetail;

    @Column(name = "bill_amount")
    private Double billAmount;

    @Column(name = "default_amount")
    private Double defaultAmount;

    @Column(name = "bill_vat")
    private Double billVat;

    @Column(name = "bill_discount")
    private Double billDiscount;

    @Column(name = "bill_amount_paid")
    private Double billAmountPaid;

    @Column(name = "bill_type")
    private String billType;

    @Column(name = "bill_year")
    private Integer billYear;

    @Column(name = "recorded_by")
    private String recordedBy;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "bill_status")
    private String billStatus;

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

    public String getBillnumber() {
        return billnumber;
    }

    public void setBillnumber(String billnumber) {
        this.billnumber = billnumber;
    }

    public String getEstateProperty() {
        return estateProperty;
    }

    public void setEstateProperty(String estateProperty) {
        this.estateProperty = estateProperty;
    }

    public String getPropertyOccupant() {
        return propertyOccupant;
    }

    public void setPropertyOccupant(String propertyOccupant) {
        this.propertyOccupant = propertyOccupant;
    }

    public Date getDateOfRecordEntry() {
        return dateOfRecordEntry;
    }

    public void setDateOfRecordEntry(Date dateOfRecordEntry) {
        this.dateOfRecordEntry = dateOfRecordEntry;
    }

    public String getBillDetail() {
        return billDetail;
    }

    public void setBillDetail(String billDetail) {
        this.billDetail = billDetail;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public Double getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(Double defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    public Double getBillVat() {
        return billVat;
    }

    public void setBillVat(Double billVat) {
        this.billVat = billVat;
    }

    public Double getBillDiscount() {
        return billDiscount;
    }

    public void setBillDiscount(Double billDiscount) {
        this.billDiscount = billDiscount;
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

    public Integer getBillYear() {
        return billYear;
    }

    public void setBillYear(Integer billYear) {
        this.billYear = billYear;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

}
