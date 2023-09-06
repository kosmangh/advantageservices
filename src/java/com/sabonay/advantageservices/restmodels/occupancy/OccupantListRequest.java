package com.sabonay.advantageservices.restmodels.occupancy;

import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;
import java.util.Date;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 05 Aug, 2023 16:08 pm
 */
public class OccupantListRequest {
    private HeaderRequest headerRequest;
    private String occupantType;
    private Date startDate;
    private Date endDate;
    private boolean dateRange;

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

    public String getOccupantType() {
        return occupantType;
    }

    public void setOccupantType(String occupantType) {
        this.occupantType = occupantType;
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

    public boolean isDateRange() {
        return dateRange;
    }

    public void setDateRange(boolean dateRange) {
        this.dateRange = dateRange;
    }
}
