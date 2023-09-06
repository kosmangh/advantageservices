package com.sabonay.advantageservices.restmodels.estatesetup;

import com.sabonay.advantageservices.entities.estatesetup.EstateZone;
import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website sabonay.com
 * @date Sun 02 Jul, 2023 07:33 am
 */
public class ZoneRequest extends EstateZone {

    private HeaderRequest headerRequest;

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }
}
