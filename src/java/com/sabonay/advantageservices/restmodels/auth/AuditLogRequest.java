package com.sabonay.advantageservices.restmodels.auth;

import com.sabonay.advantageservices.entities.useraccounts.AuditLog;
import com.sabonay.advantageservices.models.AuditLogInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Mon 24 Jul, 2023 07:57 am
 */
public class AuditLogRequest extends AuditLogInfo {

    private HeaderRequest headerRequest;

    public AuditLogRequest() {
    }

    public AuditLogRequest(AuditLog data) {
        super(data);
    }

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

}
