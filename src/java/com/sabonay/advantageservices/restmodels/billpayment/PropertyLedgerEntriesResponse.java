package com.sabonay.advantageservices.restmodels.billpayment;

import com.sabonay.advantageservices.models.estatebilling.PropertyLedgerInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Fri 25 Aug, 2023 07:04 am
 */
public class PropertyLedgerEntriesResponse {

    private HeaderResponse headerResponse;
    private List<PropertyLedgerInfo> propertyLedgers;
    private Double totalCredit;
    private Double totalDebit;
    private Double currentBalance;

    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<PropertyLedgerInfo> getPropertyLedgers() {
        return propertyLedgers;
    }

    public void setPropertyLedgers(List<PropertyLedgerInfo> propertyLedgers) {
        this.propertyLedgers = propertyLedgers;
    }

    public Double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }
//</editor-fold>

}
