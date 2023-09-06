package com.sabonay.advantageservices.restmodels.estatebilling;

import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;

/**
 * @author Daud Ainoo
* @Company Sabonay
* @Contact 0245 293945
* @Website https://sabonay.com
* @date Thu 17 Aug, 2023 03:06 am
*/
public class GroundRentBillListRequest {

    private HeaderRequest headerRequest;
    private String blockId;
    private Integer chargeYear;

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public Integer getChargeYear() {
        return chargeYear;
    }

    public void setChargeYear(Integer chargeYear) {
        this.chargeYear = chargeYear;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }
}
