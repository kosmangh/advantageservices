package com.sabonay.advantageservices.entities.estatesetup;

import com.ctrloption.entities.super_classes.EntityCrud;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 29 Jul, 2023 22:01 pm
 */
@Entity
@Table(name = "estate")
@Cacheable(false)
public class Estate extends EntityCrud implements Serializable {

    private static final Logger log = Logger.getLogger(Estate.class.getName());

    @JoinColumn(name = "region")
    @ManyToOne
    private Region region;

    @Column(name = "estate_class")
    private String estateClass;

    @Column(name = "land_size")
    private double landSize;

    @Column(name = "estate_name")
    private String estateName;

    @Column(name = "estate_location")
    private String estateLocation;

    @Column(name = "date_initialized")
    @Temporal(TemporalType.DATE)
    private Date dateInitialized;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationdate;

    @Column(name = "fresh_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date freshDate;

    @Column(name = "additional_desc")
    private String addDesc;

    @Transient
    private double landsizeleft;

    @Transient
    private String durationLeft, leased;

    @Transient
    private int totalBlocks, //Retrieves the total number of Blocks within an Estate
            totalProperty, //Retrieves the total number of Properties within an Estate
            totalProps_alloc, //Retrieves the total number of properties which has been allocated to occupants
            inactiveProps;

    public String getLeased() {
        if (expirationdate != null && dateInitialized != null) {
            int yr = expirationdate.getYear() - new Date().getYear();
            int mnths = expirationdate.getMonth() - new Date().getMonth();
            if (mnths >= 5) {
                yr += 1;
            }
            leased = yr + " yr(s)";
        } else {
            leased = null;
        }
        return leased;
    }

    public String getDurationLeft() {
        if (expirationdate != null && dateInitialized != null) {
            int yr = expirationdate.getYear() - new Date().getYear();
            int mnths = expirationdate.getMonth() - new Date().getMonth();
            if (mnths < 0 && yr >= 1) {
                yr -= 1;
                mnths += 11;
            }
            if (mnths <= 0 && yr <= 0) {
                durationLeft = "Expired";
            } else if (yr == 1) {
                durationLeft = yr + " yr," + mnths + "mnth(s)";
            } else {
                durationLeft = yr + " yrs," + mnths + "mnths";
            }
            return durationLeft;
        } else {
            durationLeft = "";
        }
        return durationLeft;
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
        if (!(object instanceof Estate)) {
            return false;
        }
        Estate other = (Estate) object;
        return !((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId)));
    }
//<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Date getFreshDate() {
        return freshDate;
    }

    public void setFreshDate(Date freshDate) {
        this.freshDate = freshDate;
    }

    public String getEstateClass() {
        return estateClass;
    }

    public void setEstateClass(String estateClass) {
        this.estateClass = estateClass;
    }

    public double getLandSize() {
        return landSize;
    }

    public void setLandSize(double landSize) {
        this.landSize = landSize;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public String getEstateLocation() {
        return estateLocation;
    }

    public void setEstateLocation(String estateLocation) {
        this.estateLocation = estateLocation;
    }

    public Date getDateInitialized() {
        return dateInitialized;
    }

    public void setDateInitialized(Date dateInitialized) {
        this.dateInitialized = dateInitialized;
    }

    public Date getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(Date expirationdate) {
        this.expirationdate = expirationdate;
    }

    public String getAddDesc() {
        return addDesc;
    }

    public void setAddDesc(String addDesc) {
        this.addDesc = addDesc;
    }

    public double getLandsizeleft() {
        return landsizeleft;
    }

    public void setLandsizeleft(double landsizeleft) {
        this.landsizeleft = landsizeleft;
    }

    public int getTotalBlocks() {
        return totalBlocks;
    }

    public void setTotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }

    public int getTotalProperty() {
        return totalProperty;
    }

    public void setTotalProperty(int totalProperty) {
        this.totalProperty = totalProperty;
    }

    public int getTotalProps_alloc() {
        return totalProps_alloc;
    }

    public void setTotalProps_alloc(int totalProps_alloc) {
        this.totalProps_alloc = totalProps_alloc;
    }

    public int getInactiveProps() {
        return inactiveProps;
    }

    public void setInactiveProps(int inactiveProps) {
        this.inactiveProps = inactiveProps;
    }
//</editor-fold>

}
