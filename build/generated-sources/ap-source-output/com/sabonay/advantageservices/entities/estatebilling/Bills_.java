package com.sabonay.advantageservices.entities.estatebilling;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-10-03T05:27:02")
@StaticMetamodel(Bills.class)
public class Bills_ { 

    public static volatile SingularAttribute<Bills, Double> billDiscount;
    public static volatile SingularAttribute<Bills, String> billDetail;
    public static volatile SingularAttribute<Bills, String> billType;
    public static volatile SingularAttribute<Bills, String> lastModifiedBy;
    public static volatile SingularAttribute<Bills, Integer> billYear;
    public static volatile SingularAttribute<Bills, Double> billVat;
    public static volatile SingularAttribute<Bills, Double> billAmount;
    public static volatile SingularAttribute<Bills, String> recordedBy;
    public static volatile SingularAttribute<Bills, String> estateProperty;
    public static volatile SingularAttribute<Bills, Double> defaultAmount;
    public static volatile SingularAttribute<Bills, String> billnumber;
    public static volatile SingularAttribute<Bills, String> billStatus;
    public static volatile SingularAttribute<Bills, String> propertyOccupant;
    public static volatile SingularAttribute<Bills, Date> dateOfRecordEntry;
    public static volatile SingularAttribute<Bills, Double> billAmountPaid;

}