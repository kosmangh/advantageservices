package com.sabonay.advantageservices.models.estatesetup;

import com.sabonay.advantageservices.entities.estatesetup.EstateZone;
import com.sabonay.advantageservices.models.CommonFields;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company CtrlOpton
 * @Contact 0245 293945
 * @Website ctrlOpton.com
 * @date Sat 18 Mar, 2023
 * @time 04.54.56 am
 */
public class ZoneInfo extends CommonFields {

    private static final Logger log = Logger.getLogger(ZoneInfo.class.getName());

    private String zoneName;
    private String remarks;
    private String address;
    private String contactNo;

    public ZoneInfo() {
    }

    public ZoneInfo(EstateZone data) {
        try {
            this.recordId = data.getRecordId();
            this.zoneName = data.getZoneName();
            this.remarks = data.getRemarks();
            this.address = data.getAddress();
            this.contactNo = data.getContactNo();
            this.createdBy = data.getCreatedBy();
            this.createdDate = data.getCreatedDate();
            this.lastModifiedBy = data.getLastModifiedBy();
            this.lastModifiedDate = data.getLastModifiedDate();
        } catch (Exception e) {
            AppLogger.error(log, e, "ZoneInfo constructor exeception");
        }
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

}
