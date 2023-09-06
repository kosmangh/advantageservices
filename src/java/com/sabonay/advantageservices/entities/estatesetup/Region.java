package com.sabonay.advantageservices.entities.estatesetup;

import com.ctrloption.entities.super_classes.EntityCrud;
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website ctrlOpton.com
 * @date Sat 01 Jul, 2023 12.27.29 pm
 */
@Entity
@Cacheable(false)
@Table(name = "region")
//@Setter
public class Region extends EntityCrud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "region_name")
    private String regionName;

    @Column(name = "remarks")
    private String remarks;

    @JoinColumn(name = "zone")
    @ManyToOne
    private EstateZone zone;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        return !((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId)));
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

    public EstateZone getZone() {
        return zone;
    }

    public void setZone(EstateZone zone) {
        this.zone = zone;
    }

}
