package com.sabonay.advantageservices.restmodels.estatebilling;

import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;
/**
 * @author Daud Ainoo
* @Company Sabonay
* @Contact 0245 293945
* @Website https://sabonay.com
* @date Sat 19 Aug, 2023 04:43 am
*/
public class RentalBillListRequest {
    private HeaderRequest headerRequest;
    private String rentMonth;
    private Integer rentYear;

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

    public String getRentMonth() {
        return rentMonth;
    }

    public void setRentMonth(String rentMonth) {
        this.rentMonth = rentMonth;
    }

    public Integer getRentYear() {
        return rentYear;
    }

    public void setRentYear(Integer rentYear) {
        this.rentYear = rentYear;
    }
}
