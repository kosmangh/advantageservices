package com.sabonay.advantageservices.restmodels.occupancy;

import com.sabonay.advantageservices.models.occupancy.OccupantInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;
/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 05 Aug, 2023 16:06 pm
 */
public class OccupantListResponse {

    private HeaderResponse headerResponse;
    private List<OccupantInfo> occupants;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<OccupantInfo> getOccupants() {
        return occupants;
    }

    public void setOccupants(List<OccupantInfo> occupants) {
        this.occupants = occupants;
    }
}
