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
    private Integer chargeYear;

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

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    public Integer getChargeYear() {
        return chargeYear;
    }

    public void setChargeYear(Integer chargeYear) {
        this.chargeYear = chargeYear;
    }

}
