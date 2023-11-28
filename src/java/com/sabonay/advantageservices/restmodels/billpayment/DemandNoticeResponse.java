package com.sabonay.advantageservices.restmodels.billpayment;

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
    private Double totalArrears = 0.0;
    private Double totalCurrentCharge = 0.0;
    private Double totalAmountDue = 0.0;

    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
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

    public Double getTotalAmountDue() {
        return totalAmountDue;
    }

    public void setTotalAmountDue(Double totalAmountDue) {
        this.totalAmountDue = totalAmountDue;
    }

    public Double getTotalArrears() {
        return totalArrears;
    }

    public void setTotalArrears(Double totalArrears) {
        this.totalArrears = totalArrears;
    }

    public Double getTotalCurrentCharge() {
        return totalCurrentCharge;
    }

    public void setTotalCurrentCharge(Double totalCurrentCharge) {
        this.totalCurrentCharge = totalCurrentCharge;
    }
//</editor-fold>

}
