///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sabonay.advantageservices.requestvalidators;
//
//import com.ctrloption.constants.DebitCredit;
//import com.ctrloption.formating.DateTimeUtils;
//import com.ctrloption.formating.NumberFormattingUtils;
//import com.ctrloption.jpa2.CrudController;
//import com.ctrloption.jpa2.Enviroment;
//import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
//import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
//import com.sabonay.advantageservices.entities.occupancy.Occupant;
//import com.sabonay.advantageservices.entities.occupancy.OccupantProperty;
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
//import javax.annotation.PostConstruct;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import org.apache.log4j.Logger;
//
///**
// *
// * @author edwin / Ritchid
// */
//@Stateless
//public class GroundRentBillerOld extends CrudController implements Serializable {
//
//    private static final Logger log = Logger.getLogger(GroundRentBillerOld.class.getName());
//
//    public static final double UNIT_SIZE = .25;
//    private EstateProperty estateProperty;
//    private OccupantProperty occupant;
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
//    @Inject
//    private BasicServices basicServices;
//    @Inject
//    private CustomDataSource customDataSource;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @PostConstruct
//    private void init() {
//        setEm(em);
//        setEnviroment(Enviroment.JAVA_EE);
//    }
//    int year_current = Calendar.getInstance().get(Calendar.YEAR);
//
//    public boolean billPropertyGroud() {
//        backTrackYear = selectedYear;
//        paymentType = estateProperty.PaymentType();
//        if (paymentType == null) {
//            processingMsg = "Property is currently unoccupied or "
//                    + "Property occupation type cannot be determind";
//        }
//        Occupant occupancy = estateProperty.ge;
//        if (occupancy == null) {
//            processingMsg = "Aborting attempt to bill ground rent on non-occupied property";
//            System.out.println(processingMsg);
//            log.error(processingMsg);
//            billingSuccessfull = false;
//            return false;
//        }
//
//        if (!occupancy.getOccupationType().equals(OccupationType.LEASEHOLD.getLabel())) {
//            processingMsg = "Aborting attempt to bill ground rent on non-leasehold property";
//            log.error(processingMsg);
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
//
//        PropertyLedger propertyLedger = new PropertyLedger();
//        propertyLedger.setEstateProperty(estateProperty);
//        propertyLedger.setLedgerYear(selectedYear);
//        propertyLedger.setamountPaid(amtToBeCharge);
//        propertyLedger.setDateOfRecordEntry(new Date());
//        propertyLedger.setDateOfRecordTransaction(calendar.getTime());
//        propertyLedger.setDebitCreditEntry(DebitCredit.DEBIT.getLabel());
//        propertyLedger.setPaymentType(PaymentType.GROUND_RENT.getLabel());
//        propertyLedger.setOccupantProperty(estateProperty.getCurrentOccupantProperty());
//        propertyLedger.setPayeeName(AdConstants.SHC);
//        propertyLedger.setMediumOfPayment(AdConstants.NONE);
//        propertyLedger.setMediumOfPaymentNumber(AdConstants.NONE);
//        propertyLedger.setPaymentFor("Ground Rent Bill, " + selectedYear);
//
//        IdGeneratorServices.setEstateLedgerId(propertyLedger, null);
//        System.out.print(propertyLedger + "\t");
//        ds.getCommonQry().estatePropertyLedgerUpdate(propertyLedger);
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
//    public boolean chargeDefaultPenalty() {
//        backTrackYear = selectedYear;
//        paymentType = estateProperty.supposePaymentType();
//        if (paymentType == null) {
//            processingMsg = "Property is currently unoccupied or "
//                    + "Property occupation type cannot be determind";
//        }
//        OccupantProperty occupantProperty = estateProperty.currentOccupantProperty();
//        if (occupantProperty == null) {
//            processingMsg = "Aborting attempt to bill ground rent on non-occupied property";
//            System.out.println(processingMsg);
//            Logger.getLogger(GroundRentBillerOld.class.getName()).log(Level.INFO, processingMsg);
//
//            billingSuccessfull = false;
//            return false;
//        }
//
//        if (occupantProperty.getOccupationType() != PropOccupationType.Leasehold) {
//            processingMsg = "Aborting attempt to bill ground rent on non-leasehold property";
//            System.out.println(processingMsg);
//            Logger.getLogger(GroundRentBillerOld.class.getName()).log(Level.INFO, processingMsg);
//
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
//
//        //load ledger entry for selected, if empty create new ledger entry for property
//        listOfEstateLedger = customDataSource.loadLedgerEntriesForSelectedYear(estateProperty, selectedYear);
//        if (listOfEstateLedger.isEmpty()) {
//            PropertyLedger propertyLedger = new PropertyLedger();
//            propertyLedger.setEstateProperty(estateProperty);
//            propertyLedger.setLedgerYear(selectedYear);
//            propertyLedger.setamountPaid(amtToBeCharge);
//            propertyLedger.setDateOfRecordEntry(new Date());
//            propertyLedger.setDateOfRecordTransaction(calendar.getTime());
//            propertyLedger.setDebitCreditEntry(DebitCredit.DEBIT.getLabel());
//            propertyLedger.setPaymentType(PaymentType.GROUND_RENT.getLabel());
//            propertyLedger.setOccupantProperty(estateProperty.getCurrentOccupantProperty());
//            propertyLedger.setPayeeName(AdConstants.SHC);
//            propertyLedger.setMediumOfPayment(AdConstants.NONE);
//            propertyLedger.setMediumOfPaymentNumber(AdConstants.NONE);
//            propertyLedger.setPaymentFor("Ground Rent Bill, " + selectedYear);
//
//            IDCreator.setEstateLedgerId(propertyLedger, null);
//
//            System.out.print(propertyLedger + "\t");
//
//            ds.getCommonQry().estatePropertyLedgerUpdate(propertyLedger);
//
//        }
//        /**
//         * We get previous groundrent charges of for the lessee, we check if the
//         * previous is less than the current amount being charged and the lessee
//         * hasn't made any payment we update the previous with the current
//         * charge
//         */
//        //we loop through all the ledger payments for an occupant and do updates where necessary
//        double backTrackSum = 0.0;
//        Integer firstYear = customDataSource.getFirstEstateProperyLedgeryear(estateProperty);
//        double totalCredit = customDataSource.sumLedgerEntryForProperty(estateProperty, DebitCredit.CREDIT);
//        for (int i = firstYear; i <= backTrackYear; i++) {
//            listOfDebitLedger = customDataSource.loadLedgerEntriesOnEntryType(estateProperty, i, DebitCredit.DEBIT);
//            for (PropertyLedger epl : listOfDebitLedger) {
//                backTrackSum += epl.getamountPaid();
//                if (backTrackSum > totalCredit) {
//                    epl.setamountPaid(amtToBeCharge);
//                    ds.getCommonQry().estatePropertyLedgerUpdate(epl);
//                }
//            }
//
//        }
//        billingSuccessfull = true;
//        return true;
//    }
//
//    public boolean chargeHouseRentArrears() {
//        try {
//            listOfEstateLedger.clear();
//            listOfEstateLedger = customDataSource.loadEstatePropertyLegders(estateProperty);
//            //get first ledger year and transaction date
//            int firstLedgerYr = listOfEstateLedger.get(0).getLedgerYear();
//            Date firstTransDate = listOfEstateLedger.get(0).getDateOfRecordTransaction();
//            //convert first transaction date to month
//            int firstEntryMonth = DateTimeUtils.getMonth(firstTransDate);
//            //convert first ledger entry into calender from
//            Calendar firstEntry = Calendar.getInstance();
//            firstEntry.set(Calendar.YEAR, firstLedgerYr);
//            firstEntry.set(Calendar.MONTH, firstEntryMonth);
//
//            Calendar currentCal = Calendar.getInstance();
//            currentCal.setTime(new Date());
//
//            //charge months left in arrears
//            while (firstEntry.getTime().getYear() >= currentCal.getTime().getYear()) {
//                for (PropertyLedger ep : listOfEstateLedger) {
//
//                    //
//                }
//            }
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean chargeGroundRentArreas() {
//        try {
//            backTrackYear = selectedYear;
//
//            OccupantProperty occupantProperty = estateProperty.currentOccupantProperty();
//            if (occupantProperty == null) {
//                processingMsg = "Aborting attempt to bill ground rent on non-occupied property";
//                System.out.println(processingMsg);
//                Logger.getLogger(GroundRentBillerOld.class.getName()).log(Level.INFO, processingMsg);
//
//                billingSuccessfull = false;
//                return false;
//            }
//
//            if (occupantProperty.getOccupationType() != PropOccupationType.Leasehold) {
//                processingMsg = "Aborting attempt to bill ground rent on non-leasehold property";
//                System.out.println(processingMsg);
//                Logger.getLogger(GroundRentBillerOld.class.getName()).log(Level.INFO, processingMsg);
//
//                billingSuccessfull = false;
//                return false;
//            }
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(new Date());
//            calendar.set(Calendar.MONTH, Calendar.JANUARY);
//            calendar.set(Calendar.YEAR, selectedYear);
//
//            amtToBeCharge = getCurrentGroundRentBill();
//
//            //load ledger entry for selected, if empty create new ledger entry for property
//            listOfEstateLedger = customDataSource.loadLedgerEntriesForSelectedYear(estateProperty, selectedYear);
////            if (listOfEstateLedger.isEmpty()) {
//            while (backTrackYear <= currentYear) {
//                PropertyLedger propertyLedger = new PropertyLedger();
//                propertyLedger.setEstateProperty(estateProperty);
//                propertyLedger.setLedgerYear(backTrackYear);
//                propertyLedger.setamountPaid(amtToBeCharge);
//                propertyLedger.setDateOfRecordEntry(new Date());
//                propertyLedger.setDateOfRecordTransaction(calendar.getTime());
//                propertyLedger.setDebitCreditEntry(DebitCredit.DEBIT.getLabel());
//                propertyLedger.setPaymentType(PaymentType.GROUND_RENT.toString());
//                propertyLedger.setOccupantProperty(estateProperty.getCurrentOccupantProperty());
//                propertyLedger.setPayeeName(AdConstants.SHC);
//                propertyLedger.setMediumOfPayment(AdConstants.NONE);
//                propertyLedger.setMediumOfPaymentNumber(AdConstants.NONE);
//                propertyLedger.setPaymentFor("Ground Rent Bill, " + backTrackYear);
//
//                IDCreator.setEstateLedgerId(propertyLedger, null);
//                System.out.print(propertyLedger + "\t");
//                ds.getCommonQry().estatePropertyLedgerUpdate(propertyLedger);
//                backTrackYear = backTrackYear + 1;
//            }
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public boolean removeGroundRent() {
//        try {
//            PropertyLedger propertyLedger = new PropertyLedger();
//            propertyLedger.setEstateProperty(estateProperty);
//            propertyLedger.setLedgerYear(selectedYear);
//
//            propertyLedger.setDebitCreditEntry(DebitCredit.DEBIT.getLabel());
//            propertyLedger.setPaymentType(PaymentType.GROUND_RENT.getLabel());
//            propertyLedger.setOccupantProperty(estateProperty.getCurrentOccupantProperty());
//            propertyLedger.setPayeeName(AdConstants.SHC);
//            propertyLedger.setMediumOfPayment(AdConstants.NONE);
//            propertyLedger.setMediumOfPaymentNumber(AdConstants.NONE);
//
//            IDCreator.setEstateLedgerId(propertyLedger, null);
//
//            System.out.print(propertyLedger + "\t");
//
//            // the delete problem may be coming form here
//            boolean delete = ds.getCommonQry().estatePropertyLedgerDelete(propertyLedger, true);
//
//            if (delete) {
//                processingMsg = "Deleted";
//                billingSuccessfull = true;
//                JsfUtil.addInformationMessage(processingMsg);
//            } else {
//                processingMsg = "Unable to Delete";
//                billingSuccessfull = false;
//                JsfUtil.addErrorMessage(processingMsg);
//            }
//        } catch (Exception e) {
//            JsfUtil.addErrorMessage("Removing ground rent failed");
//        }
//
//        return billingSuccessfull;
//
//    }
//
//    private double getAppropriateGroundRentBilll() {
//        try {
//            PropertyUsage ussage = estateProperty.getPropertyUsage();
//            if (ussage == null) {
//                return 0.0;
//            }
//
////        PaymentType paymentType = estateProperty.supposePaymentType();
////        if(paymentType == null)
//            if (ussage == PropertyUsage.COMMERCIAL || ussage == PropertyUsage.MIXED_USE || ussage == PropertyUsage.INSTITUTIONAL || ussage == PropertyUsage.RESIDENTIAL) {
//                return getCommMixedUsee();
//            } else {
//                return customDataSource.getGroundRentBasicChargee(estateProperty, selectedYear);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return 0.00;
//    }
//
//    private double getCurrentGroundRentBill() {
//        try {
//            PropertyUsage usage = estateProperty.getPropertyUsage();
//            if (usage == null) {
//                return 0.0;
//            }
//            if (usage == PropertyUsage.COMMERCIAL || usage == PropertyUsage.MIXED_USE || usage == PropertyUsage.INSTITUTIONAL || usage == PropertyUsage.RESIDENTIAL) {
//                return getCurrentForComMixedUse();
//            } else {
//                return customDataSource.getGroundRentBasicCharge(estateProperty, currentYear);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0.0;
//        }
//    }
//
//    private double getCommMixedUsee() {
//        double a = customDataSource.getGroundRentBasicChargee(estateProperty, selectedYear);
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
//    private double getCurrentForComMixedUse() {
//        double a = customDataSource.getGroundRentBasicCharge(estateProperty, currentYear);
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
//    private static GroundRentBillerOld rentBiller = new GroundRentBillerOld();
//
//    public static GroundRentBillerOld instance() {
//        return rentBiller;
//    }
//
//    public double getCalculatedd(EstateProperty estateProperty, int year) {
//        this.estateProperty = estateProperty;
//        this.selectedYear = year;
//        return getAppropriateGroundRentBilll();
//    }
//
//    public double getCalculated(EstateProperty estateProperty, int year) {
//        this.estateProperty = estateProperty;
//        this.selectedYear = year;
//        return getAppropriateGroundRentBill();
//    }
//
//    public void setEstateProperty(EstateProperty estateProperty) {
//        this.estateProperty = estateProperty;
//    }
//
//    public void setSelectedYear(int selectedYear) {
//        this.selectedYear = selectedYear;
//    }
//
//    public boolean isBillingSuccessfull() {
//        return billingSuccessfull;
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
//    public String getProcessingMsg() {
//        return processingMsg;
//    }
//
//    public OccupantProperty getOccupant() {
//        return occupant;
//    }
//
//    public void setOccupant(OccupantProperty propertyOccupant) {
//        this.occupant = propertyOccupant;
//    }
//
//}
