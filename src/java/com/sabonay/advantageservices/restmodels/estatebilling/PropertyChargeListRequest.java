package com.sabonay.advantageservices.restmodels.estatebilling;

import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;

/**
 * @author Daud Ainoo
* @Company Sabonay
* @Contact 0245 293945
* @Website https://sabonay.com
* @date Sat 12 Aug, 2023 17:16 pm
*/
public class PropertyChargeListRequest {
    private HeaderRequest headerRequest;
    private String regionId;
    private Integer chargeYear;

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Integer getChargeYear() {
        return chargeYear;
    }

    public void setChargeYear(Integer chargeYear) {
        this.chargeYear = chargeYear;
    }

}
