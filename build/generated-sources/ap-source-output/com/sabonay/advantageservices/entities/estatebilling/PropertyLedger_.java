package com.sabonay.advantageservices.entities.estatebilling;

import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.entities.estatesetup.Region;
import com.sabonay.advantageservices.entities.occupancy.Occupant;
import com.sabonay.advantageservices.entities.useraccounts.Staff;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-09-06T11:11:16")
@StaticMetamodel(PropertyLedger.class)
public class PropertyLedger_ { 

    public static volatile SingularAttribute<PropertyLedger, Double> amountInvolved;
    public static volatile SingularAttribute<PropertyLedger, String> paymentFor;
    public static volatile SingularAttribute<PropertyLedger, Date> dateOfRecordTransaction;
    public static volatile SingularAttribute<PropertyLedger, String> mediumOfPaymentNumber;
    public static volatile SingularAttribute<PropertyLedger, String> receiptNumberIssued;
    public static volatile SingularAttribute<PropertyLedger, String> payeeContact;
    public static volatile SingularAttribute<PropertyLedger, String> description;
    public static volatile SingularAttribute<PropertyLedger, Boolean> paymentReversal;
    public static volatile SingularAttribute<PropertyLedger, String> paymentType;
    public static volatile SingularAttribute<PropertyLedger, String> payeeName;
    public static volatile SingularAttribute<PropertyLedger, Staff> recordedBy;
    public static volatile SingularAttribute<PropertyLedger, EstateProperty> estateProperty;
    public static volatile SingularAttribute<PropertyLedger, Integer> yearPaidFor;
    public static volatile SingularAttribute<PropertyLedger, String> mediumOfPayment;
    public static volatile SingularAttribute<PropertyLedger, String> debitCreditEntry;
    public static volatile SingularAttribute<PropertyLedger, Integer> ledgerYear;
    public static volatile SingularAttribute<PropertyLedger, Region> region;
    public static volatile SingularAttribute<PropertyLedger, Occupant> occupant;
    public static volatile SingularAttribute<PropertyLedger, Date> dateOfRecordEntry;
    public static volatile SingularAttribute<PropertyLedger, Boolean> reversed;

}