package com.sabonay.advantageservices.entities.estatebilling;

import com.sabonay.advantageservices.entities.estatesetup.Region;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-15T13:46:09")
@StaticMetamodel(PropertyCharge.class)
public class PropertyCharge_ { 

    public static volatile SingularAttribute<PropertyCharge, Double> thirdClassCharge;
    public static volatile SingularAttribute<PropertyCharge, Integer> chargeYear;
    public static volatile SingularAttribute<PropertyCharge, Region> region;
    public static volatile SingularAttribute<PropertyCharge, String> propertyUsage;
    public static volatile SingularAttribute<PropertyCharge, Double> firstClassCharge;
    public static volatile SingularAttribute<PropertyCharge, Double> secondClassCharge;

}