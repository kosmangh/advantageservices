package com.sabonay.advantageservices.entities.estatesetup;

import com.sabonay.advantageservices.entities.estatesetup.EstateBlock;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-06T11:11:16")
@StaticMetamodel(EstateProperty.class)
public class EstateProperty_ { 

    public static volatile SingularAttribute<EstateProperty, Boolean> blocked;
    public static volatile SingularAttribute<EstateProperty, String> propertyName;
    public static volatile SingularAttribute<EstateProperty, Double> propertyLandSize;
    public static volatile SingularAttribute<EstateProperty, EstateBlock> estateBlock;
    public static volatile SingularAttribute<EstateProperty, String> propertyUsage;
    public static volatile SingularAttribute<EstateProperty, String> propertyCategory;
    public static volatile SingularAttribute<EstateProperty, String> propertyNumber;

}