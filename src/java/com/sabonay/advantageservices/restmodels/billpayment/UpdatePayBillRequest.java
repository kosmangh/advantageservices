package com.sabonay.advantageservices.restmodels.billpayment;

import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 24 Dec, 2023 14:02 pm
 */
public class UpdatePayBillRequest {

    private HeaderRequest headerRequest;
    private String billId;
    private String ledgerId;
    private String modeOfPayment;
    private String modeOfPaymentNo;
    private String receiptNumber;
    private String modifiedBy;
    private Double amountPaid;

    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(String ledgerId) {
        this.ledgerId = ledgerId;
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

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
//</editor-fold>

}
