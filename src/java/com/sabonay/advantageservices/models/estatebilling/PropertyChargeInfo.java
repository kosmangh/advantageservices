package com.sabonay.advantageservices.models.estatebilling;

import com.ctrloption.formating.DateTimeUtils;
import com.sabonay.advantageservices.entities.estatebilling.PropertyCharge;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 12 Aug, 2023 10:34 am
 */
public class PropertyChargeInfo extends PropertyChargeSuper {

    private static final Logger log = Logger.getLogger(PropertyChargeInfo.class.getName());

    private String regionName;
    private String createdDate;
    private String createdBy;
    private String lastModifiedDate;
    private String lastModifiedBy;

    public PropertyChargeInfo() {
        super();
    }

    public PropertyChargeInfo(PropertyCharge data) {
        try {
            chargeYear = data.getChargeYear();
            firstClassCharge = data.getFirstClassCharge();
            propertyUsage = data.getPropertyUsage();
            recordId = data.getRecordId();
            regionId = data.getRegion().getRecordId();
            regionName = data.getRegion().getRegionName();
            secondClassCharge = data.getSecondClassCharge();
            thirdClassCharge = data.getThirdClassCharge();
            createdBy = data.getCreatedBy();
            createdDate = DateTimeUtils.formatDate(data.getCreatedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
            lastModifiedBy = data.getLastModifiedBy();
            lastModifiedDate = DateTimeUtils.formatDate(data.getLastModifiedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
        } catch (Exception e) {
            AppLogger.error(log, e, "error PropertyChargeInfo constructor");
        }
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

}
