package com.sabonay.advantageservices.entities.estatesetup;

import com.sabonay.advantageservices.entities.estatesetup.Estate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-06T11:11:16")
@StaticMetamodel(EstateBlock.class)
public class EstateBlock_ { 

    public static volatile SingularAttribute<EstateBlock, String> blockName;
    public static volatile SingularAttribute<EstateBlock, Estate> estate;
    public static volatile SingularAttribute<EstateBlock, Double> blockSize;
    public static volatile SingularAttribute<EstateBlock, String> remarks;

}