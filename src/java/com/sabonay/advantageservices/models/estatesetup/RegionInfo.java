package com.sabonay.advantageservices.models.estatesetup;

import com.sabonay.advantageservices.entities.estatesetup.Region;
import com.sabonay.advantageservices.models.CommonFields;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Mon 03 Jul, 2023 21:26:14 pm
 */
public class RegionInfo extends CommonFields {

    private static final Logger log = Logger.getLogger(RegionInfo.class.getName());

    private String regionName;
    private String remarks;
    private String zoneId;
    private String zoneName;

    public RegionInfo() {
    }

    public RegionInfo(Region data) {
        try {
            this.recordId = data.getRecordId();
            this.regionName = data.getRegionName();
            this.remarks = data.getRemarks();
            this.zoneId = data.getZone().getRecordId();
            this.zoneName = data.getZone().getZoneName();
            this.createdBy = data.getCreatedBy();
            this.createdDate = data.getCreatedDate();
            this.lastModifiedBy = data.getLastModifiedBy();
            this.lastModifiedDate = data.getLastModifiedDate();
        } catch (Exception e) {
            AppLogger.error(log, e, "RegionInfo constructor exeception");
        }

    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

}
