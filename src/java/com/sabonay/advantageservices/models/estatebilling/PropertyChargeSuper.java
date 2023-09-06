package com.sabonay.advantageservices.models.estatebilling;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 12 Aug, 2023 10:29 am
 */
public class PropertyChargeSuper {

    protected String recordId;
    protected String regionId;
    protected Integer chargeYear;
    protected String propertyUsage;
    protected Double firstClassCharge;
    protected Double secondClassCharge;
    protected Double thirdClassCharge;

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
