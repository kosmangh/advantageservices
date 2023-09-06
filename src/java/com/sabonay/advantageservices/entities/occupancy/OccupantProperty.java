package com.sabonay.advantageservices.entities.occupancy;

import com.ctrloption.entities.super_classes.EntityCrud;
import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.utils.AppConstants;
import com.sabonay.advantageservices.utils.enums.OccupationType;
import com.sabonay.advantageservices.utils.enums.PaymentType;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 06 Aug, 2023 14:48 pm
 */
@Entity
@Table(name = "occupant_property")
@Cacheable(false)
public class OccupantProperty extends EntityCrud implements Serializable {

    @JoinColumn(name = "occupant")
    @ManyToOne
    private Occupant occupant;

    @JoinColumn(name = "estate_property")
    @OneToOne
    private EstateProperty estateProperty;

    //Presents the occupancy type or status of the contract (occupancy) between  a client and a property (the institution)
    //Available values :
    //Leasehold : for land occupancy Rental
    //Rental : For house occupancy
    //NONE : For none of the above
    @Column(name = "occupation_type")
    private String occupationType; // LEASEHOLD,RENTAL,NONE

    @Column(name = "first_date_of_occupancy")
    @Temporal(TemporalType.DATE)
    private Date firstDateOfOccupancy;

    @Column(name = "last_date_of_occupancy")
    @Temporal(TemporalType.DATE)
    private Date lastDateOfOccupancy;

    public OccupantProperty() {
    }

    public OccupantProperty(String recordId) {
        this.recordId = recordId;
    }

    public String getPaymentType() {
        if (occupationType.equalsIgnoreCase(AppConstants.RENTAL)) {
            return AppConstants.HOUSE_RENT;
        }
        if (occupationType.equalsIgnoreCase(AppConstants.LEASEHOLD)) {
            return AppConstants.GROUND_RENT;
        }
        return "";
    }

    public PaymentType getAppropriatePaymentType() {
        if (getOccupationType().equals(OccupationType.RENTAL.getLabel())) {
            return PaymentType.HOUSE_RENT;
        } else if (getOccupationType().equals(OccupationType.LEASEHOLD.getLabel())) {
            return PaymentType.GROUND_RENT;
        }
        return null;
    }

    public String getTotalDuration() {
        String totalDuration = "";
        if ((firstDateOfOccupancy != null) && (lastDateOfOccupancy != null)) {
            Calendar firstCalendar = Calendar.getInstance();
            Calendar lastCalendar = Calendar.getInstance();
            firstCalendar.setTime(firstDateOfOccupancy);
            lastCalendar.setTime(firstDateOfOccupancy);
            int Fyr = firstCalendar.get(Calendar.YEAR);
            int Fmnth = firstCalendar.get(Calendar.MONTH) + 1;
            int Lyr = lastCalendar.get(Calendar.YEAR);
            int Lmnth = lastCalendar.get(Calendar.MONTH) + 1;
            if (Fyr > Lyr) {
                totalDuration = "N/A";
            } else {
                if (Fmnth <= Lmnth) {
                    totalDuration = (Lyr - Fyr) + " yr(s), " + (Lmnth - Fmnth) + " mnth(s)";
                } else {
                    totalDuration = ((Lyr - Fyr) - 1) + " yr(s), " + ((Fmnth - 12) + Lmnth) + " mnth(s)";
                }
            }
        } else {
            totalDuration = "N/A";
        }
        return totalDuration;
    }

    public String getDurationLeft() {
        if ((lastDateOfOccupancy != null)) {
            Calendar currentCalendar = Calendar.getInstance();
            Calendar lastCalendar = Calendar.getInstance();
            lastCalendar.setTime(lastDateOfOccupancy);

            int Cyr = currentCalendar.get(Calendar.YEAR);
            int Cmnth = currentCalendar.get(Calendar.MONTH) + 1;
            int Lyr = lastCalendar.get(Calendar.YEAR);
            int Lmnth = lastCalendar.get(Calendar.MONTH) + 1;

            if (lastCalendar.before(currentCalendar)) {
                return "Exceeded";
            }
            if ((Lyr - Cyr == 0) && (Lmnth - Cmnth == 0)) {
                //this will force the system to check for days left
                return (lastCalendar.get(Calendar.DAY_OF_MONTH) - currentCalendar.get(Calendar.DAY_OF_MONTH)) + "day(s)";
            }
            if (Cmnth <= Lmnth) {
                return (Lyr - Cyr) + " yr(s), " + (Lmnth - Cmnth) + " mnth(s)";
            } else {
                return ((Lyr - Cyr) - 1) + " yr(s), " + ((Cmnth - 12) + Lmnth) + " mnth(s)";
            }
        }
        return "Unknown";
    }

    public String getValidity() {
        if ((firstDateOfOccupancy != null) && (lastDateOfOccupancy != null)) {
            Calendar lastCalendar = Calendar.getInstance();
            Calendar currentCalendar = Calendar.getInstance();
            lastCalendar.setTime(lastDateOfOccupancy);
            int outcome = lastCalendar.compareTo(currentCalendar);
            if (outcome > 0) {
                return "Occupied";
            } else {
                return "Expired";
            }
        } else {
            return "N/A";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OccupantProperty other = (OccupantProperty) obj;
        return !((this.recordId == null) ? (other.recordId != null) : !this.recordId.equals(other.recordId));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.recordId != null ? this.recordId.hashCode() : 0);
        return hash;
    }

    public Occupant getOccupant() {
        return occupant;
    }

    public void setOccupant(Occupant occupant) {
        this.occupant = occupant;
    }

    public EstateProperty getEstateProperty() {
        return estateProperty;
    }

    public void setEstateProperty(EstateProperty estateProperty) {
        this.estateProperty = estateProperty;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
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

}
