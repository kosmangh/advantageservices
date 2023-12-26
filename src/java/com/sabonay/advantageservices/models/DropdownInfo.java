package com.sabonay.advantageservices.models;

import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Tue 29 Aug, 2023 06:10 am
 */
public class DropdownInfo {

    private static final Logger log = Logger.getLogger(DropdownInfo.class.getName());

    private String recordId;
    private String recordName;
    private String entityName;

    public DropdownInfo() {
    }

    public DropdownInfo(String recordId, String recordName, String entityName) {
        try {
            this.recordId = recordId;
            this.recordName = recordName;
            this.entityName = entityName;
        } catch (Exception e) {
            AppLogger.error(log, e, "error setting " + entityName + " DropdownInfo constructor ");
        }
    }

//<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
//</editor-fold>

}
