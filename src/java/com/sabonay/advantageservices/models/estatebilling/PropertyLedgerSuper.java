package com.sabonay.advantageservices.models.estatebilling;

import java.util.Date;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Mon 14 Aug, 2023 08:02 am
 */
public class PropertyLedgerSuper {

    protected Date dateOfRecordEntry;
    protected Date dateOfRecordTransaction;
    protected String debitCreditEntry;
    protected Double amountPaid;
    protected String receiptNumberIssued;
    protected String paymentFor;
    protected String payeeName;
    protected String payeeContact;
    protected String mediumOfPayment;
    protected String mediumOfPaymentNumber;
    protected Integer ledgerYear;
    protected String recordedBy;
    protected String regionId;
    protected Integer yearPaidFor;
    protected boolean paymentReversal;
    protected boolean reversed;
    protected String description;
    protected String propertyId;
    protected String paymentType;
    protected String occupantId;
    protected String recordId;
    protected String bill;
    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">

    public Date getDateOfRecordEntry() {
        return dateOfRecordEntry;
    }

    public void setDateOfRecordEntry(Date dateOfRecordEntry) {
        this.dateOfRecordEntry = dateOfRecordEntry;
    }

    public Date getDateOfRecordTransaction() {
        return dateOfRecordTransaction;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
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

    public Double getamountPaid() {
        return amountPaid;
    }

    public void setamountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
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

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
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

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
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

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getOccupantId() {
        return occupantId;
    }

    public void setOccupantId(String occupantId) {
        this.occupantId = occupantId;
    }
//</editor-fold>

}
