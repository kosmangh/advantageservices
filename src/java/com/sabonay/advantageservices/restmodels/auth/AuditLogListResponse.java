package com.sabonay.advantageservices.restmodels.auth;

import com.sabonay.advantageservices.models.AuditLogInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Wed 26 Jul, 2023 06:17 am
 */
public class AuditLogListResponse {

    private HeaderResponse headerResponse;
    private List<AuditLogInfo> auditLogs;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<AuditLogInfo> getAuditLogs() {
        return auditLogs;
    }

    public void setAuditLogs(List<AuditLogInfo> auditLogs) {
        this.auditLogs = auditLogs;
    }

}
