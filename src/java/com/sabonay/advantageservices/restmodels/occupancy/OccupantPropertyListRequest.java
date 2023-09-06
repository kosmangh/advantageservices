package com.sabonay.advantageservices.restmodels.occupancy;

import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;
/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 06 Aug, 2023 17:23 pm
 */
public class OccupantPropertyListRequest {

    private HeaderRequest headerRequest;
    private String searchBy;
    private String searchParameter;
    private String searchValue;
    private String block;
    private boolean activeOccupant = false;
    private String occupationType;

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

    public String getSearchParameter() {
        return searchParameter;
    }

    public void setSearchParameter(String searchParameter) {
        this.searchParameter = searchParameter;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public boolean isActiveOccupant() {
        return activeOccupant;
    }

    public void setActiveOccupant(boolean activeOccupant) {
        this.activeOccupant = activeOccupant;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }
}
