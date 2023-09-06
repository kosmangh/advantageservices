package com.sabonay.advantageservices.restmodels.occupancy;

import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Tue 22 Aug, 2023 06:40 am
 */
public class OccupiedPropertyRequest {

    private HeaderRequest headerRequest;
    private String searchText;
    private String occupationType;

    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }
//</editor-fold>

}
