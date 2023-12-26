package com.sabonay.advantageservices.models.billpayment;

import java.util.Date;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Thu 24 Aug, 2023 05:10 am
 */
public class BillPaymentSuper {

    protected String recordId;
    protected String occupantId;
    protected String receiptNumber;
    protected Date datePaid;
    protected Date dateOfTransaction;
    protected String modeOfPayment;
    protected String modeOfPaymentNo;
    protected Double amountPaid;
    protected Integer billYear;
    protected boolean spreadAmtForOccupantProperties;

    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getOccupantId() {
        return occupantId;
    }

    public Integer getBillYear() {
        return billYear;
    }

    public void setBillYear(Integer billYear) {
        this.billYear = billYear;
    }

    public void setOccupantId(String occupantId) {
        this.occupantId = occupantId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public boolean isSpreadAmtForOccupantProperties() {
        return spreadAmtForOccupantProperties;
    }

    public void setSpreadAmtForOccupantProperties(boolean spreadAmtForOccupantProperties) {
        this.spreadAmtForOccupantProperties = spreadAmtForOccupantProperties;
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
