package com.sabonay.advantageservices.restmodels.estatesetup;

import com.sabonay.advantageservices.models.estatesetup.ZoneInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 02 Jul, 2023 07:35 am
 */
public class ZoneListReponse {

    private HeaderResponse headerResponse;
    private List<ZoneInfo> zones;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<ZoneInfo> getZones() {
        return zones;
    }

    public void setZones(List<ZoneInfo> zones) {
        this.zones = zones;
    }
}
