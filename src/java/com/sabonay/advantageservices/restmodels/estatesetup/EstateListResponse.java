package com.sabonay.advantageservices.restmodels.estatesetup;

import com.sabonay.advantageservices.models.estatesetup.EstateInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 30 Jul, 2023 14:40 pm
 */
public class EstateListResponse {

    private HeaderResponse headerResponse;
    private List<EstateInfo> estates;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<EstateInfo> getEstates() {
        return estates;
    }

    public void setEstates(List<EstateInfo> estates) {
        this.estates = estates;
    }

}
