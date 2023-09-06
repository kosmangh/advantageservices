package com.sabonay.advantageservices.restmodels.occupancy;

import com.sabonay.advantageservices.models.occupancy.OccupantPropertyInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
* @Company Sabonay
* @Contact 0245 293945
* @Website https://sabonay.com
* @date Sun 06 Aug, 2023 17:23 pm
*/
public class OccupantPropertyListResponse {
    private HeaderResponse headerResponse;
    private List<OccupantPropertyInfo> occupantProperties;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<OccupantPropertyInfo> getOccupantProperties() {
        return occupantProperties;
    }

    public void setOccupantProperties(List<OccupantPropertyInfo> occupantProperties) {
        this.occupantProperties = occupantProperties;
    }

}
