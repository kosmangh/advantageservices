package com.sabonay.advantageservices.restmodels.commons;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 16 Jul, 2023 08:35 am
 */
public class GenericSearchRequest {

    private HeaderRequest headerRequest;
    private String searchBy;
    private String searchValue;
    private Boolean occupiedProps;
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

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Boolean getOccupiedProps() {
        return occupiedProps;
    }

    public void setOccupiedProps(Boolean occupiedProps) {
        this.occupiedProps = occupiedProps;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

}
