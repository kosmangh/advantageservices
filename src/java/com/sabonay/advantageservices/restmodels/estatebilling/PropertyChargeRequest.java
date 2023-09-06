package com.sabonay.advantageservices.restmodels.estatebilling;

import com.sabonay.advantageservices.models.estatebilling.PropertyChargeSuper;
import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;
/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 12 Aug, 2023 10:33 am
 */
public class PropertyChargeRequest extends PropertyChargeSuper {

    private HeaderRequest headerRequest;
    private String createdBy;
    private String lastModifiedBy;

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Integer getChargeYear() {
        return chargeYear;
    }

    public void setChargeYear(Integer chargeYear) {
        this.chargeYear = chargeYear;
    }

    public String getPropertyUsage() {
        return propertyUsage;
    }

    public void setPropertyUsage(String propertyUsage) {
        this.propertyUsage = propertyUsage;
    }

    public Double getFirstClassCharge() {
        return firstClassCharge;
    }

    public void setFirstClassCharge(Double firstClassCharge) {
        this.firstClassCharge = firstClassCharge;
    }

    public Double getSecondClassCharge() {
        return secondClassCharge;
    }

    public void setSecondClassCharge(Double secondClassCharge) {
        this.secondClassCharge = secondClassCharge;
    }

    public Double getThirdClassCharge() {
        return thirdClassCharge;
    }

    public void setThirdClassCharge(Double thirdClassCharge) {
        this.thirdClassCharge = thirdClassCharge;
    }
}
