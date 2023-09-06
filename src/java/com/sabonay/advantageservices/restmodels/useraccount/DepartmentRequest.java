package com.sabonay.advantageservices.restmodels.useraccount;

import com.sabonay.advantageservices.models.useraccount.DepartmentInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 15 Jul, 2023 02:50 am
 */
public class DepartmentRequest extends DepartmentInfo {

    private HeaderRequest headerRequest;

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }
}
