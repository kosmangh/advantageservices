package com.sabonay.advantageservices.models.occupancy;

import java.util.Date;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 06 Aug, 2023 15:42 pm
 */
public class OccupantPropertySuper {

    protected String occupantId;
    protected String propertyId;
    protected Date firstDateOfOccupancy;
    protected Date lastDateOfOccupancy;
    protected String occupationType;

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
