package com.sabonay.advantageservices.entities.estatebilling;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2023-11-18T20:01:00")
@StaticMetamodel(BillPayment.class)
public class BillPayment_ { 

    public static volatile SingularAttribute<BillPayment, Double> amountInvolved;
    public static volatile SingularAttribute<BillPayment, Date> datePaid;
    public static volatile SingularAttribute<BillPayment, Date> dateOfTransaction;
    public static volatile SingularAttribute<BillPayment, String> modeOfPayment;
    public static volatile SingularAttribute<BillPayment, String> modeOfPaymentNo;
    public static volatile SingularAttribute<BillPayment, String> occupant;
    public static volatile SingularAttribute<BillPayment, String> receiptNumber;

}