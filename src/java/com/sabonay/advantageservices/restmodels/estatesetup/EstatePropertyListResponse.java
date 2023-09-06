package com.sabonay.advantageservices.restmodels.estatesetup;

import com.sabonay.advantageservices.models.estatesetup.EstatePropertyInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
* @Company Sabonay
* @Contact 0245 293945
* @Website https://sabonay.com
* @date Thu 03 Aug, 2023 22:13 pm
*/
public class EstatePropertyListResponse {
    private HeaderResponse headerResponse;
    private List<EstatePropertyInfo> properties;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<EstatePropertyInfo> getProperties() {
        return properties;
    }

    public void setProperties(List<EstatePropertyInfo> properties) {
        this.properties = properties;
    }
}
