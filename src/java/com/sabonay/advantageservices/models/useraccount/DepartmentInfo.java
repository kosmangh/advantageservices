package com.sabonay.advantageservices.models.useraccount;

import com.sabonay.advantageservices.entities.useraccounts.Department;
import com.sabonay.advantageservices.models.CommonFields;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 16 Jul, 2023 06:18 am
 */
public class DepartmentInfo extends CommonFields {

    private static final Logger log = Logger.getLogger(DepartmentInfo.class.getName());

    private String departmentName;
    private String remarks;

    public DepartmentInfo() {
    }

    public DepartmentInfo(Department data) {
        try {
            recordId = data.getRecordId();
            createdBy = data.getCreatedBy();
            lastModifiedBy = data.getLastModifiedBy();
            lastModifiedDate = data.getLastModifiedDate();
            createdDate = data.getCreatedDate();
            departmentName = data.getDepartmentName();
            remarks = data.getRemarks();
        } catch (Exception e) {
            AppLogger.error(log, e, "deparment constructor exception");
        }

    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
