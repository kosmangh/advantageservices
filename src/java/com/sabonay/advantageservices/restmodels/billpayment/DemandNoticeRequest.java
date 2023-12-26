package com.sabonay.advantageservices.restmodels.billpayment;

import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;

/**
 *
 * @author dainoo
 */
public class DemandNoticeRequest {

    private HeaderRequest headerRequest;
    private String searchParameter;
    private String searchValue;
    private Integer billYear;
    private String billMonth;
    private String billType;

    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

    public String getSearchParameter() {
        return searchParameter;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    public Integer getBillYear() {
        return billYear;
    }

    public void setBillYear(Integer billYear) {
        this.billYear = billYear;
    }
//</editor-fold>

}
