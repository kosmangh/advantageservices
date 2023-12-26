//package com.sabonay.advantageservices.requestvalidators;
//
//import com.ctrloption.constants.DebitCredit;
//import com.ctrloption.formating.DateTimeUtils;
//import com.ctrloption.formating.NumberFormattingUtils;
//import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
//import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
//import com.sabonay.advantageservices.entities.occupancy.Occupant;
//import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
//import com.sabonay.advantageservices.services.BasicServices;
//import com.sabonay.advantageservices.services.CustomDataSource;
//import com.sabonay.advantageservices.services.IdGeneratorServices;
//import com.sabonay.advantageservices.utils.AdConstants;
//import com.sabonay.advantageservices.utils.enums.OccupationType;
//import com.sabonay.advantageservices.utils.enums.PaymentType;
//import com.sabonay.advantageservices.utils.enums.PropertyUsage;
//import java.io.Serializable;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
//
///**
// * @author Daud Ainoo
// * @Company Sabonay
// * @Contact 0245 293945
// * @Website https://sabonay.com
// * @date Wed 16 Aug, 2023 03:31 am
// */
//@Stateless
//public class GroundRentBiller implements Serializable {
//
//    private static final Logger log = Logger.getLogger(GroundRentBillerOld.class.getName());
//
//    public static final double UNIT_SIZE = .25;
//    private EstateProperty estateProperty;
//    private Occupant Occupant;
//    private int selectedYear = 0;
//    private double amtToBeCharge = 0.0;
//    private double prevAmtCharged = 0.0;
//    private int backTrackYear = 0;
//    private double outstandingBalance = 0.0;
//    private boolean billingSuccessfull = false;
//    private String processingMsg = "";
//    private List<PropertyLedger> listOfEstateLedger = null;
//    private List<PropertyLedger> listOfDebitLedger = null;
//    private int currentYear = DateTimeUtils.getCurrentYear();
//    private List<Object[]> estatePropertyObjList = null;
//    private PaymentType paymentType;
//    private double amountPaid = 0.0;
//    private String recordedBy;
//
//    int year_current = Calendar.getInstance().get(Calendar.YEAR);
//
//    @Inject
//    private BasicServices basicServices;
//    @Inject
//    private CustomDataSource customDataSource;
//
//    public HeaderResponse processGroundRentBill() {
//
//    }
//
//    public boolean billPropertyGroud() {
//        backTrackYear = selectedYear;
//        paymentType = estateProperty.supposePaymentType();
//        if (paymentType == null) {
//            processingMsg = "Property is currently unoccupied or "
//                    + "Property occupation type cannot be determind";
//        }
////        EstateProperty property = estateProperty;
//        if (estateProperty == null) {
//            processingMsg = "Aborting attempt to bill ground rent on non-occupied property";
//            System.out.println(processingMsg);
//            Logger.getLogger(GroundRentBiller.class.getName()).log(Level.INFO, processingMsg);
//            billingSuccessfull = false;
//            return false;
//        }
//
//        if (estateProperty.getOccupied() != OccupationType.Leasehold) {
//            processingMsg = "Aborting attempt to bill ground rent on non-leasehold property";
//            System.out.println(processingMsg);
//            Logger.getLogger(GroundRentBiller.class.getName()).log(Level.INFO, processingMsg);
//            billingSuccessfull = false;
//            return false;
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.set(Calendar.MONTH, Calendar.JANUARY);
//        calendar.set(Calendar.YEAR, selectedYear);
//
//        amtToBeCharge = NumberFormattingUtils.formatDecimalNumberTo_2(getAppropriateGroundRentBill());
//        PropertyLedger propertyLedger = new PropertyLedger();
//        propertyLedger.setEstateProperty(estateProperty);
//        propertyLedger.setLedgerYear(selectedYear);
//        propertyLedger.setamountPaid(amtToBeCharge);
//        propertyLedger.setDateOfRecordEntry(new Date());
//        propertyLedger.setDateOfRecordTransaction(calendar.getTime());
//        propertyLedger.setDebitCreditEntry(DebitCredit.DEBIT.getLabel());
//        propertyLedger.setPaymentType(PaymentType.GROUND_RENT.getLabel());
//        propertyLedger.setOccupant(customDataSource.currentPropertyOccupant(estateProperty.getRecordId(), new Date()));
//        propertyLedger.setOccupant(Occupant);
//        propertyLedger.setPayeeName(AdConstants.SHC);
//        propertyLedger.setMediumOfPayment(AdConstants.NONE);
//        propertyLedger.setMediumOfPaymentNumber(AdConstants.NONE);
//        propertyLedger.setPaymentFor("Ground Rent Bill, " + selectedYear);
//
//        IdGeneratorServices.setEstateLedgerId(propertyLedger, null);
//
//        System.out.print(propertyLedger + "\t");
//
//        propertyLedger.setLastModifiedDate(new Date());
//        propertyLedger.setLastModifiedBy(recordedBy);
//        basicServices.update(propertyLedger);
//        billingSuccessfull = true;
//        return true;
//    }
//
//    private double getAppropriateGroundRentBill() {
//        String ussage = estateProperty.getPropertyUsage();
//        if (ussage == null) {
//            return 0.0;
//        }
//        if (ussage.equals(PropertyUsage.COMMERCIAL.getLabel()) || ussage.equals(PropertyUsage.MIXED_USE.getLabel())
//                || ussage.equals(PropertyUsage.INSTITUTIONAL.getLabel()) || ussage.equals(PropertyUsage.RESIDENTIAL.getLabel())) {
//            return getCommMixedUse();
//        } else {
//            return customDataSource.getGroundRentBasicCharge(estateProperty, selectedYear);
//        }
//    }
//
//    private double getCommMixedUse() {
//        double a = customDataSource.getGroundRentBasicCharge(estateProperty, selectedYear);
//        if (selectedYear < 2008) {
//            return a;
//        }
//        double landSize = estateProperty.getPropertyLandSize();
//        if (landSize > UNIT_SIZE) {
//            double newRate = (landSize / UNIT_SIZE) * a;
//            return newRate;
//        }
//        return a;
//    }
//
//    public EstateProperty getEstateProperty() {
//        return estateProperty;
//    }
//
//    public void setEstateProperty(EstateProperty estateProperty) {
//        this.estateProperty = estateProperty;
//    }
//
//    public String getRecordedBy() {
//        return recordedBy;
//    }
//
//    public void setRecordedBy(String recordedBy) {
//        this.recordedBy = recordedBy;
//    }
//
//    public int getSelectedYear() {
//        return selectedYear;
//    }
//
//    public void setSelectedYear(int selectedYear) {
//        this.selectedYear = selectedYear;
//    }
//
//}
