package com.sabonay.advantageservices.restmodels.estatesetup;

import com.sabonay.advantageservices.models.estatesetup.EstateBlockInfo;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
* @Company Sabonay
* @Contact 0245 293945
* @Website https://sabonay.com
* @date Tue 01 Aug, 2023 08:29 am
*/
public class EstateBlockListResponse {
    private HeaderResponse headerResponse;
    private List<EstateBlockInfo> estateBlocks;

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public List<EstateBlockInfo> getEstateBlocks() {
        return estateBlocks;
    }

    public void setEstateBlocks(List<EstateBlockInfo> estateBlocks) {
        this.estateBlocks = estateBlocks;
    }
}
