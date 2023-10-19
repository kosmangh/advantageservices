package com.sabonay.advantageservices.entities.estatesetup;

import com.sabonay.advantageservices.entities.estatesetup.Region;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-19T07:08:02")
@StaticMetamodel(Estate.class)
public class Estate_ { 

    public static volatile SingularAttribute<Estate, String> addDesc;
    public static volatile SingularAttribute<Estate, Double> landSize;
    public static volatile SingularAttribute<Estate, String> estateName;
    public static volatile SingularAttribute<Estate, String> estateLocation;
    public static volatile SingularAttribute<Estate, String> estateClass;
    public static volatile SingularAttribute<Estate, Date> expirationdate;
    public static volatile SingularAttribute<Estate, Region> region;
    public static volatile SingularAttribute<Estate, Date> dateInitialized;

}