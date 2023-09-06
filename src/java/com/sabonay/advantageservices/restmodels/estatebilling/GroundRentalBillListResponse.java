package com.sabonay.advantageservices.restmodels.estatebilling;

import com.sabonay.advantageservices.models.estatebilling.PropertyLedgerInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Thu 17 Aug, 2023 03:07 am
 */
public class GroundRentalBillListResponse {
    private HeaderResponse headerResponse;
    private List<PropertyLedgerInfo> propertyLedgers;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<PropertyLedgerInfo> getPropertyLedgers() {
        return propertyLedgers;
    }

    public void setPropertyLedgers(List<PropertyLedgerInfo> propertyLedgers) {
        this.propertyLedgers = propertyLedgers;
    }

}
