package com.sabonay.advantageservices.entities.occupancy;

import com.ctrloption.entities.super_classes.EntityCrud;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 05 Aug, 2023 12:41 pm
 */
@Entity
@Table(name = "occupant")
@Cacheable(false)
public class Occupant extends EntityCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "institutional")
    private boolean institutional;

    @Column(name = "occupant_name")
    private String occupantName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "next_of_kin")
    private String nextOfKin;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "nok_email")
    private String nokEmail;

    @Column(name = "nok_phone")
    private String nokPhone;

    @Column(name = "nok_address")
    private String nokAddress;

    @Column(name = "supervisor_name")
    private String supervisorName;

    @Column(name = "supervisor_tel")
    private String supervisorMobileNo;

    @Column(name = "supervisor_email")
    private String supervisorEmail;

    @Column(name = "supervisor_address")
    private String supervisorAddress;

    @Column(name = "local_address")
    private String localAddress;

    @Column(name = "nationality")
    private String nationality = "GHANAIAN";

    @Column(name = "home_town")
    private String homeTown;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "id_no")
    private String idNo;

    @Column(name = "id_type")
    private String idType;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "occupant_type")
    private String occupantType; //LESSEE,TENANT

    @Transient
    private String assigned;

    public String getInstitutionalDisplayName() {
        if (institutional) {
            return "Institution";
        } else {
            return "Individual";
        }
    }

    public String getOccupantNameDisplay() {
        return occupantName + " ( " + getInstitutionalDisplayName() + " " + occupantType.toLowerCase() + " )";
    }

    public Occupant() {
    }

    public Occupant(String recordId) {
        this.recordId = recordId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Occupant other = (Occupant) obj;
        return !((this.recordId == null) ? (other.recordId != null) : !this.recordId.equals(other.recordId));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.recordId != null ? this.recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return occupantName;
    }

    public boolean isInstitutional() {
        return institutional;
    }

    public void setInstitutional(boolean institutional) {
        this.institutional = institutional;
    }

    public String getOccupantName() {
        return occupantName;
    }

    public void setOccupantName(String occupantName) {
        this.occupantName = occupantName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getNokEmail() {
        return nokEmail;
    }

    public void setNokEmail(String nokEmail) {
        this.nokEmail = nokEmail;
    }

    public String getNokPhone() {
        return nokPhone;
    }

    public void setNokPhone(String nokPhone) {
        this.nokPhone = nokPhone;
    }

    public String getNokAddress() {
        return nokAddress;
    }

    public void setNokAddress(String nokAddress) {
        this.nokAddress = nokAddress;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getSupervisorMobileNo() {
        return supervisorMobileNo;
    }

    public void setSupervisorMobileNo(String supervisorMobileNo) {
        this.supervisorMobileNo = supervisorMobileNo;
    }

    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }

    public String getSupervisorAddress() {
        return supervisorAddress;
    }

    public void setSupervisorAddress(String supervisorAddress) {
        this.supervisorAddress = supervisorAddress;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOccupantType() {
        return occupantType;
    }

    public void setOccupantType(String occupantType) {
        this.occupantType = occupantType;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }
}
