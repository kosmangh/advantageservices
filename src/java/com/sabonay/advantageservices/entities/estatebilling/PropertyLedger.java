package com.sabonay.advantageservices.entities.estatebilling;

import com.ctrloption.entities.super_classes.EntityCrud;
import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.entities.estatesetup.Region;
import com.sabonay.advantageservices.entities.occupancy.Occupant;
import com.sabonay.advantageservices.entities.useraccounts.Staff;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 13 Aug, 2023 12:28 pm
 */
@Entity
@Table(name = "property_ledger")
@Cacheable(false)
//@Setter
//@Getter
public class PropertyLedger extends EntityCrud implements Serializable {

    @JoinColumn(name = "estate_property")
    @ManyToOne
    private EstateProperty estateProperty;

    @JoinColumn(name = "occupant")
    @ManyToOne
    private Occupant occupant;

    @Column(name = "date_of_record_entry")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfRecordEntry;

    @Column(name = "date_of_record_transaction")
    @Temporal(TemporalType.DATE)
    private Date dateOfRecordTransaction;

    @Column(name = "type_of_entry")
    private String debitCreditEntry;

    @Column(name = "amount_involved")
    private Double amountInvolved;

    @Column(name = "receipt_number_issued")
    private String receiptNumberIssued;

    @Column(name = "payment_for")
    private String paymentFor;

    @Column(name = "payee_name")
    private String payeeName;

    @Column(name = "payee_contact")
    private String payeeContact;

    @Column(name = "medium_of_payment")
    private String mediumOfPayment;

    @Column(name = "medium_of_payment_number")
    private String mediumOfPaymentNumber;

    @Column(name = "ledger_year")
    private Integer ledgerYear;

    @JoinColumn(name = "recorded_by")
    @ManyToOne
    private Staff recordedBy;

    @Column(name = "year_paid_for")
    private Integer yearPaidFor;

    //this boolean object indicates that the current transaction is
    //to reverse or make amendment to a previous transaction made
    @Column(name = "payment_reversal")
    private boolean paymentReversal;

    //this determines if the bill has been 
    //reversed by another transaction
    @Column(name = "reversed")
    private boolean reversed;

    @Column(name = "descrptn")
    private String description;

    @Column(name = "payment_type")
    private String paymentType;

    @JoinColumn(name = "region")
    @ManyToOne
    private Region region;

    @Override
    public String toString() {
        return debitCreditEntry + "-" + ledgerYear;
    }

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
        PropertyLedger other = (PropertyLedger) object;
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

    public Date getDateOfRecordEntry() {
        return dateOfRecordEntry;
    }

    public void setDateOfRecordEntry(Date dateOfRecordEntry) {
        this.dateOfRecordEntry = dateOfRecordEntry;
    }

    public Date getDateOfRecordTransaction() {
        return dateOfRecordTransaction;
    }

    public void setDateOfRecordTransaction(Date dateOfRecordTransaction) {
        this.dateOfRecordTransaction = dateOfRecordTransaction;
    }

    public String getDebitCreditEntry() {
        return debitCreditEntry;
    }

    public void setDebitCreditEntry(String debitCreditEntry) {
        this.debitCreditEntry = debitCreditEntry;
    }

    public Double getAmountInvolved() {
        return amountInvolved;
    }

    public void setAmountInvolved(Double amountInvolved) {
        this.amountInvolved = amountInvolved;
    }

    public String getReceiptNumberIssued() {
        return receiptNumberIssued;
    }

    public void setReceiptNumberIssued(String receiptNumberIssued) {
        this.receiptNumberIssued = receiptNumberIssued;
    }

    public String getPaymentFor() {
        return paymentFor;
    }

    public void setPaymentFor(String paymentFor) {
        this.paymentFor = paymentFor;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeContact() {
        return payeeContact;
    }

    public void setPayeeContact(String payeeContact) {
        this.payeeContact = payeeContact;
    }

    public String getMediumOfPayment() {
        return mediumOfPayment;
    }

    public void setMediumOfPayment(String mediumOfPayment) {
        this.mediumOfPayment = mediumOfPayment;
    }

    public String getMediumOfPaymentNumber() {
        return mediumOfPaymentNumber;
    }

    public void setMediumOfPaymentNumber(String mediumOfPaymentNumber) {
        this.mediumOfPaymentNumber = mediumOfPaymentNumber;
    }

    public Integer getLedgerYear() {
        return ledgerYear;
    }

    public void setLedgerYear(Integer ledgerYear) {
        this.ledgerYear = ledgerYear;
    }

    public Staff getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(Staff recordedBy) {
        this.recordedBy = recordedBy;
    }

    public Integer getYearPaidFor() {
        return yearPaidFor;
    }

    public void setYearPaidFor(Integer yearPaidFor) {
        this.yearPaidFor = yearPaidFor;
    }

    public boolean isPaymentReversal() {
        return paymentReversal;
    }

    public void setPaymentReversal(boolean paymentReversal) {
        this.paymentReversal = paymentReversal;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
