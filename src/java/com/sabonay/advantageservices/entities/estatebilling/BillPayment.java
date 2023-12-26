package com.sabonay.advantageservices.entities.estatebilling;

import com.ctrloption.entities.super_classes.EntityCrud;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Mon 14 Aug, 2023 00:32 am
 */
@Entity
@Table(name = "bill_payment")
@Cacheable(false)
//@Data
public class BillPayment extends EntityCrud implements Serializable {

    @Column(name = "occupant")
    private String occupant;

    @Basic(optional = false)
    @NotNull
    @Column(name = "receipt_number")
    private String receiptNumber;

    @Column(name = "date_paid")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaid;

    @Column(name = "date_of_transaction")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfTransaction;

    @Column(name = "mode_of_payment")
    private String modeOfPayment;

    @Column(name = "mode_of_payment_no")
    private String modeOfPaymentNo;

    @Column(name = "amount_involved")
    private Double amountPaid;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropertyLedger)) {
            return false;
        }
        BillPayment other = (BillPayment) object;
        return !((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId)));
    }
//<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">

    public String getOccupant() {
        return occupant;
    }

    public void setOccupant(String occupant) {
        this.occupant = occupant;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getModeOfPaymentNo() {
        return modeOfPaymentNo;
    }

    public void setModeOfPaymentNo(String modeOfPaymentNo) {
        this.modeOfPaymentNo = modeOfPaymentNo;
    }

    public Double getamountPaid() {
        return amountPaid;
    }

    public void setamountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
//</editor-fold>
}
