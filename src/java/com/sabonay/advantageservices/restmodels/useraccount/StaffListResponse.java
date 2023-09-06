package com.sabonay.advantageservices.restmodels.useraccount;

import com.sabonay.advantageservices.models.useraccount.StaffInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
* @Company Sabonay
* @Contact 0245 293945
* @Website https://sabonay.com
* @date Sun 16 Jul, 2023 14:30 pm
*/
public class StaffListResponse {
    private HeaderResponse headerResponse;
    private List<StaffInfo> staffs;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<StaffInfo> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<StaffInfo> staffs) {
        this.staffs = staffs;
    }
}
