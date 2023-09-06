package com.sabonay.advantageservices.restmodels.estatebilling;

import com.sabonay.advantageservices.models.estatebilling.PropertyChargeInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 12 Aug, 2023 11:04 am
 */
public class PropertyChargeListRepsonse {

    private HeaderResponse headerResponse;
    private List<PropertyChargeInfo> propertyCharges;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<PropertyChargeInfo> getPropertyCharges() {
        return propertyCharges;
    }

    public void setPropertyCharges(List<PropertyChargeInfo> propertyCharges) {
        this.propertyCharges = propertyCharges;
    }
}
