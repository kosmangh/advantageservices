package com.sabonay.advantageservices.restmodels.estatesetup;

import com.sabonay.advantageservices.entities.estatesetup.Region;
import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;

/**
 * @author Daud Ainoo
 * @Company CtrlOpton
 * @Contact 0245 293945
 * @Website ctrlOpton.com
 * @date Mon 03 Jul, 2023
 * @time 21.24.39 pm
 */
public class RegionRequest extends Region {
    private HeaderRequest headerRequest;
    private String zoneId;

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

}
