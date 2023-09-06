package com.sabonay.advantageservices.entities.useraccounts;

import com.ctrloption.entities.super_classes.EntityCrud;
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 01 Jul, 2023 15:19 pm
 */
@Entity
@Cacheable(false)
@Table(name = "department")
public class Department extends EntityCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "department_name")
    protected String departmentName;

    @Column(name = "remarks")
    protected String remarks;

    //<editor-fold defaultstate="collapsed" desc="OTHERS">
    public Department() {
    }

    public Department(String recordId) {
        this.recordId = recordId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        return !((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId)));
    }
//</editor-fold>

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
