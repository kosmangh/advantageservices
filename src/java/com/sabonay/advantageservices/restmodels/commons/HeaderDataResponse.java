package com.sabonay.advantageservices.restmodels.commons;

public class HeaderDataResponse {

    private String requestId;
    private Integer recordCount = 0;
    private String responseMessage;
    private String responseCode;
    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
//</editor-fold>
}
