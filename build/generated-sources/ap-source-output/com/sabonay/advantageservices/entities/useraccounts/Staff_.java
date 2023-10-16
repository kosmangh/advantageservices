package com.sabonay.advantageservices.entities.useraccounts;

import com.sabonay.advantageservices.entities.estatesetup.Region;
import com.sabonay.advantageservices.entities.useraccounts.Department;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-15T13:46:09")
@StaticMetamodel(Staff.class)
public class Staff_ { 

    public static volatile SingularAttribute<Staff, String> lastName;
    public static volatile SingularAttribute<Staff, Boolean> resetPassword;
    public static volatile SingularAttribute<Staff, String> address;
    public static volatile SingularAttribute<Staff, String> gender;
    public static volatile SingularAttribute<Staff, String> accessLevel;
    public static volatile SingularAttribute<Staff, String> mobileNo;
    public static volatile SingularAttribute<Staff, String> officeContactNo;
    public static volatile SingularAttribute<Staff, Date> lastLoginDate;
    public static volatile SingularAttribute<Staff, String> firstName;
    public static volatile SingularAttribute<Staff, String> password;
    public static volatile SingularAttribute<Staff, Date> accountCreatedDate;
    public static volatile SingularAttribute<Staff, String> otherNames;
    public static volatile SingularAttribute<Staff, Date> dob;
    public static volatile SingularAttribute<Staff, Region> region;
    public static volatile SingularAttribute<Staff, Department> department;
    public static volatile SingularAttribute<Staff, String> userRole;
    public static volatile SingularAttribute<Staff, String> email;
    public static volatile SingularAttribute<Staff, String> status;
    public static volatile SingularAttribute<Staff, String> username;
    public static volatile SingularAttribute<Staff, String> accountCreatedBy;

}