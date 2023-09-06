package com.sabonay.advantageservices.entities.useraccounts;

import com.ctrloption.entities.super_classes.EntityCrud;
import com.sabonay.advantageservices.entities.estatesetup.Region;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Edwin / Ritchid / Daud
 */
@Entity
@Table(name = "staff")
@Cacheable(false)
public class Staff extends EntityCrud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "other_names")
    private String otherNames;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dob")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "region")
    @ManyToOne
    private Region region;

    @JoinColumn(name = "department")
    @ManyToOne
    private Department department;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "office_contact_no")
    private String officeContactNo;

    @Column(name = "status")
    private String status;

    @Column(name = "last_login_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "reset_password")
    private boolean resetPassword;

    @Column(name = "access_level")
    private String accessLevel;

    @Column(name = "account_created_by")
    private String accountCreatedBy;

    @Column(name = "account_created_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date accountCreatedDate;

    public Staff() {
    }

    public Staff(String staffId) {
        this.recordId = staffId;
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
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        return !((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId)));
    }

    public String getFullName() {

        if (firstName == null || firstName.equals("")) {
            return lastName + " " + otherNames;
        }
        if (lastName == null || lastName.equals("")) {
            return firstName + " " + otherNames;
        }
        if (otherNames == null || otherNames.equals("")) {
            return firstName + " " + lastName;
        }
        return firstName + " " + lastName + " " + otherNames;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getOfficeContactNo() {
        return officeContactNo;
    }

    public void setOfficeContactNo(String officeContactNo) {
        this.officeContactNo = officeContactNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getAccountCreatedBy() {
        return accountCreatedBy;
    }

    public void setAccountCreatedBy(String accountCreatedBy) {
        this.accountCreatedBy = accountCreatedBy;
    }

    public Date getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public void setAccountCreatedDate(Date accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }

}
