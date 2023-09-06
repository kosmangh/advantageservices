package com.sabonay.advantageservices.entities.estatesetup;

import com.ctrloption.entities.super_classes.EntityCrud;
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Daud Ainoo
 * @Company CtrlOption
 * @Contact 0245 293945
 * @Website ctrlOpton.com
 * @date Sat 01 Jul, 2023 07:34 am
 */
@Entity
@Cacheable(false)
@Table(name = "estate_zone")
public class EstateZone extends EntityCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "zone_name")
    private String zoneName;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "address")
    private String address;

    @Column(name = "contactNo")
    private String contactNo;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstateZone)) {
            return false;
        }
        EstateZone other = (EstateZone) object;
        return !((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId)));
    }

    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
//</editor-fold>

}
