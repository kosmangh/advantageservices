package com.sabonay.advantageservices.entities.estatebilling;

import com.ctrloption.entities.super_classes.EntityCrud;
import com.sabonay.advantageservices.entities.estatesetup.Region;
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 12 Aug, 2023 08:01 am
 */
@Entity
@Table(name = "property_charge")
@Cacheable(false)
//@Data
public class PropertyCharge extends EntityCrud implements Serializable {

    private static final Logger log = Logger.getLogger(PropertyCharge.class.getName());

    @JoinColumn(name = "region")
    @ManyToOne
    private Region region;

    @Column(name = "charge_year")
    private Integer chargeYear;

    @Column(name = "property_usage")
    private String propertyUsage;

    @Column(name = "first_class_charge")
    private Double firstClassCharge;

    @Column(name = "second_class_charge")
    private Double secondClassCharge;

    @Column(name = "third_class_charge")
    private Double thirdClassCharge;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PropertyCharge)) {
            return false;
        }
        PropertyCharge other = (PropertyCharge) object;
        return !((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId)));
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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
