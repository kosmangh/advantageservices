package com.sabonay.advantageservices.restmodels.useraccount;

import com.sabonay.advantageservices.models.useraccount.DepartmentInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 16 Jul, 2023 06:42 am
 */
public class DepartmentListResponse {

    private HeaderResponse headerResponse;
    private List<DepartmentInfo> departments;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<DepartmentInfo> getDepartments() {
        return departments;
    }

    public void setDepartments(List<DepartmentInfo> departments) {
        this.departments = departments;
    }
}
