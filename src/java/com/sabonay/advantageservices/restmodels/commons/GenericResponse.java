package com.sabonay.advantageservices.restmodels.commons;

import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;

/**
 * @author dainoo
 * @date Wed 15 Feb, 2023
 * @time 14.33.42 PM
 */
public class GenericResponse {

    private HeaderResponse headerResponse = new HeaderResponse();

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

}
