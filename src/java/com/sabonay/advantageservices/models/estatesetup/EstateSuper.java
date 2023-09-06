package com.sabonay.advantageservices.models.estatesetup;

import java.util.Date;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 30 Jul, 2023 14:19 pm
 */
public class EstateSuper {

    protected String recordId;
    protected String regionId;
    protected String estateClass;
    protected String estateName;
    protected String estateLocation;
    protected Date leaseStartDate;
    protected Date leaseEndDate;
    protected Date freshDate;
    protected String additionalInfo;
    protected Double landSize;

    public EstateSuper() {
    }

    public String getRecordId() {
        return recordId;
    }

    public Date getFreshDate() {
        return freshDate;
    }

    public void setFreshDate(Date freshDate) {
        this.freshDate = freshDate;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getEstateClass() {
        return estateClass;
    }

    public void setEstateClass(String estateClass) {
        this.estateClass = estateClass;
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

    public Date getLeaseStartDate() {
        return leaseStartDate;
    }

    public void setLeaseStartDate(Date leaseStartDate) {
        this.leaseStartDate = leaseStartDate;
    }

    public Date getLeaseEndDate() {
        return leaseEndDate;
    }

    public void setLeaseEndDate(Date leaseEndDate) {
        this.leaseEndDate = leaseEndDate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Double getLandSize() {
        return landSize;
    }

    public void setLandSize(Double landSize) {
        this.landSize = landSize;
    }

}
