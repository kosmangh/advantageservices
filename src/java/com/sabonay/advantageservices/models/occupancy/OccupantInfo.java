package com.sabonay.advantageservices.models.occupancy;

import com.ctrloption.formating.DateTimeUtils;
import com.sabonay.advantageservices.entities.occupancy.Occupant;
import com.sabonay.advantageservices.utils.AppLogger;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 05 Aug, 2023 16:02 pm
 */
public class OccupantInfo extends OccupantSuper {

    private static final Logger log = Logger.getLogger(OccupantInfo.class.getName());

    private String createdDate;
    private String lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;

    private String institutionalDisplayName;
    private String occupantNameDisplay;
    public OccupantInfo() {
        super();
    }

    public OccupantInfo(Occupant data) {
        try {
            recordId = data.getRecordId();
            assigned = "";
            dateOfBirth = data.getDateOfBirth();
            emailAddress = data.getEmailAddress();
            gender = data.getGender();
            homeTown = data.getHomeTown();
            idNo = data.getIdNo();
            idType = data.getIdType();
            institutional = data.isInstitutional();
            institutionalDisplayName = data.getInstitutionalDisplayName();
            occupantNameDisplay = data.getOccupantNameDisplay();
            localAddress = data.getLocalAddress();
            maritalStatus = data.getMaritalStatus();
            mobileNo = data.getMobileNo();
            nationality = data.getNationality();
            nextOfKin = data.getNextOfKin();
            nokAddress = data.getNokAddress();
            nokEmail = data.getNokEmail();
            nokPhone = data.getNokPhone();
            occupantName = data.getOccupantName();
            occupantType = data.getOccupantType();
            occupation = data.getOccupation();
            relationship = data.getRelationship();
            remarks = data.getRemarks();
            supervisorAddress = data.getSupervisorAddress();
            supervisorEmail = data.getSupervisorEmail();
            supervisorMobileNo = data.getSupervisorMobileNo();
            supervisorName = data.getSupervisorName();
            telephoneNumber = data.getTelephoneNumber();
            createdBy = data.getCreatedBy();
            createdDate = DateTimeUtils.formatDate(data.getCreatedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
            lastModifiedBy = data.getLastModifiedBy();
            lastModifiedDate = DateTimeUtils.formatDate(data.getLastModifiedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
        } catch (Exception e) {
            AppLogger.error(log, e, "error OccupantInfo constructor");
        }
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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

    public String getInstitutionalDisplayName() {
        return institutionalDisplayName;
    }

    public void setInstitutionalDisplayName(String institutionalDisplayName) {
        this.institutionalDisplayName = institutionalDisplayName;
    }

    public String getOccupantNameDisplay() {
        return occupantNameDisplay;
    }

    public void setOccupantNameDisplay(String occupantNameDisplay) {
        this.occupantNameDisplay = occupantNameDisplay;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Boolean getInstitutional() {
        return institutional;
    }

    public void setInstitutional(Boolean institutional) {
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
