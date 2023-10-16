package com.sabonay.advantageservices.entities.useraccounts;

import com.sabonay.advantageservices.entities.estatesetup.EstateZone;
import com.sabonay.advantageservices.entities.estatesetup.Region;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-15T13:46:09")
@StaticMetamodel(AuditLog.class)
public class AuditLog_ { 

    public static volatile SingularAttribute<AuditLog, EstateZone> estateZone;
    public static volatile SingularAttribute<AuditLog, String> ui;
    public static volatile SingularAttribute<AuditLog, String> ipAddress;
    public static volatile SingularAttribute<AuditLog, String> fullName;
    public static volatile SingularAttribute<AuditLog, Region> region;
    public static volatile SingularAttribute<AuditLog, String> userActivity;
    public static volatile SingularAttribute<AuditLog, String> userRole;
    public static volatile SingularAttribute<AuditLog, String> username;

}