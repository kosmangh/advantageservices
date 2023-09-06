package com.sabonay.advantageservices.restmodels.commons;

import com.sabonay.advantageservices.models.DropdownInfo;
import java.util.List;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Tue 29 Aug, 2023 09:09 am
 */
public class DropdownResponse {
    private HeaderResponse headerResponse;
    private List<DropdownInfo> dropdownList;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<DropdownInfo> getDropdownList() {
        return dropdownList;
    }

    public void setDropdownList(List<DropdownInfo> dropdownList) {
        this.dropdownList = dropdownList;
    }

}
