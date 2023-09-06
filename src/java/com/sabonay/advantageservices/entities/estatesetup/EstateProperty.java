package com.sabonay.advantageservices.entities.estatesetup;

import com.ctrloption.entities.super_classes.EntityCrud;
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 29 Jul, 2023 22:10 pm
 */
@Entity
@Table(name = "estate_property")
@Cacheable(false)
public class EstateProperty extends EntityCrud implements Serializable {

    @JoinColumn(name = "estate_block")
    @ManyToOne
    private EstateBlock estateBlock;

    @Column(name = "property_usage")
    private String propertyUsage;

    @Column(name = "property_name")
    private String propertyName;

    @Column(name = "property_category")
    private String propertyCategory;

    @Column(name = "property_number")
    private String propertyNumber;

    @Column(name = "property_land_size")
    private Double propertyLandSize;

    @Column(name = "blocked")
    private boolean blocked;

    @Transient
    private String currentOccupantName, occupied;   // Property Name is for page display purpose

    @Transient
    private boolean allocated;

    @Transient
    private double currentOccupancyAccruals, currentOccupancyPayments;

    @Override
    public String toString() {
        return propertyName + '-' + recordId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Estate)) {
            return false;
        }
        EstateProperty other = (EstateProperty) object;
        return !((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId)));
    }

    public EstateBlock getEstateBlock() {
        return estateBlock;
    }

    public void setEstateBlock(EstateBlock estateBlock) {
        this.estateBlock = estateBlock;
    }

    public String getPropertyUsage() {
        return propertyUsage;
    }

    public void setPropertyUsage(String propertyUsage) {
        this.propertyUsage = propertyUsage;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyCategory() {
        return propertyCategory;
    }

    public void setPropertyCategory(String propertyCategory) {
        this.propertyCategory = propertyCategory;
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public Double getPropertyLandSize() {
        return propertyLandSize;
    }

    public void setPropertyLandSize(Double propertyLandSize) {
        this.propertyLandSize = propertyLandSize;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getCurrentOccupantName() {
        return currentOccupantName;
    }

    public void setCurrentOccupantName(String currentOccupantName) {
        this.currentOccupantName = currentOccupantName;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }

    public double getCurrentOccupancyAccruals() {
        return currentOccupancyAccruals;
    }

    public void setCurrentOccupancyAccruals(double currentOccupancyAccruals) {
        this.currentOccupancyAccruals = currentOccupancyAccruals;
    }

    public double getCurrentOccupancyPayments() {
        return currentOccupancyPayments;
    }

    public void setCurrentOccupancyPayments(double currentOccupancyPayments) {
        this.currentOccupancyPayments = currentOccupancyPayments;
    }
}
