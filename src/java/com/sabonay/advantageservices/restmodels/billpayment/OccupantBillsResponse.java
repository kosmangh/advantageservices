package com.sabonay.advantageservices.restmodels.billpayment;

import com.sabonay.advantageservices.models.billpayment.BillsInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Mon 18 Dec, 2023 12:42 pm
 */
public class OccupantBillsResponse {
    private HeaderResponse headerResponse;
    private List<BillsInfo> listOfBills;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<BillsInfo> getListOfBills() {
        return listOfBills;
    }

    public void setListOfBills(List<BillsInfo> listOfBills) {
        this.listOfBills = listOfBills;
    }

}
