package com.sabonay.advantageservices.restmodels.reports;

import com.sabonay.advantageservices.models.reports.DemandNoticeInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
 * @Company CtrlOpton
 * @Contact 0245 293945
 * @Website ctrlOpton.com
 * @date Sun 05 Nov, 2023
 * @time 11.01.49 AM
 */
public class DemandNoticeResponse {

    private HeaderResponse headerResponse;
    private List<DemandNoticeInfo> demandNotices;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<DemandNoticeInfo> getDemandNotices() {
        return demandNotices;
    }

    public void setDemandNotices(List<DemandNoticeInfo> demandNotices) {
        this.demandNotices = demandNotices;
    }

}
