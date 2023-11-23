package com.sabonay.advantageservices.entities.occupancy;

import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.entities.occupancy.Occupant;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-11-18T20:01:00")
@StaticMetamodel(OccupantProperty.class)
public class OccupantProperty_ { 

    public static volatile SingularAttribute<OccupantProperty, String> occupationType;
    public static volatile SingularAttribute<OccupantProperty, EstateProperty> estateProperty;
    public static volatile SingularAttribute<OccupantProperty, Date> lastDateOfOccupancy;
    public static volatile SingularAttribute<OccupantProperty, Date> firstDateOfOccupancy;
    public static volatile SingularAttribute<OccupantProperty, Occupant> occupant;

}