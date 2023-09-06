package com.sabonay.advantageservices.restmodels.occupancy;

import com.sabonay.advantageservices.models.occupancy.OccupantPropertySuper;
import com.sabonay.advantageservices.restmodels.commons.HeaderRequest;
import java.util.Date;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 06 Aug, 2023 16:32 pm
 */
public class OccupantPropertyRequest extends OccupantPropertySuper {

    private HeaderRequest headerRequest;
    private String recordId;
    private String createdBy;
    private String lastModifiedBy;

    public OccupantPropertyRequest() {
    }

    public HeaderRequest getHeaderRequest() {
        return headerRequest;
    }

    public void setHeaderRequest(HeaderRequest headerRequest) {
        this.headerRequest = headerRequest;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getOccupantId() {
        return occupantId;
    }

    public void setOccupantId(String occupantId) {
        this.occupantId = occupantId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public Date getFirstDateOfOccupancy() {
        return firstDateOfOccupancy;
    }

    public void setFirstDateOfOccupancy(Date firstDateOfOccupancy) {
        this.firstDateOfOccupancy = firstDateOfOccupancy;
    }

    public Date getLastDateOfOccupancy() {
        return lastDateOfOccupancy;
    }

    public void setLastDateOfOccupancy(Date lastDateOfOccupancy) {
        this.lastDateOfOccupancy = lastDateOfOccupancy;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

}
