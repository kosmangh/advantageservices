package com.sabonay.advantageservices.restmodels.auth;

import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;
import java.util.Date;

/**
 * @author Daud Ainoo
* @Company Sabonay
* @Contact 0245 293945
* @Website https://sabonay.com
* @date Wed 26 Jul, 2023 06:16 am
*/
public class AuditLogListRequest {
    private HeaderRequest headerRequest;
    private String searchBy;
    private String searchValue;
    private Date startDate;
    private Date endDate;

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
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

}
