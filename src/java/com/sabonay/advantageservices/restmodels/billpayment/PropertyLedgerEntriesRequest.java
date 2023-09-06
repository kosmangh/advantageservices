package com.sabonay.advantageservices.restmodels.billpayment;

import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;
import java.util.Date;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Fri 25 Aug, 2023 00:58 am
 */
public class PropertyLedgerEntriesRequest {

    private HeaderRequest headerRequest;
    private String propertyId;
    private String occupantId;
    private String searchBy;
    private String searchValue;
    private Date startDate;
    private Date endDate;

    //<editor-fold defaultstate="collapsed" desc="SETTERS AND SETTERS">

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

    public String getOccupantId() {
        return occupantId;
    }

    public void setOccupantId(String occupantId) {
        this.occupantId = occupantId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
//</editor-fold>

}
