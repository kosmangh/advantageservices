//package com.sabonay.advantageservices.services.utils;
//
//import com.ctrloption.constants.BillStatus;
//import com.ctrloption.constants.DebitCredit;
//import com.ctrloption.jpa2.CrudController;
//import com.ctrloption.jpa2.Enviroment;
//import com.ctrloption.utils.MsgFormatter;
//import com.sabonay.advantageservices.entities.EntityFields;
//import com.sabonay.advantageservices.entities.estatebilling.Bills;
//import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
//import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
//import com.sabonay.advantageservices.services.BasicServices;
//import com.sabonay.advantageservices.services.IdGeneratorServices;
//import com.sabonay.advantageservices.utils.AdConstants;
//import com.sabonay.advantageservices.utils.enums.PaymentType;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//import javax.annotation.PostConstruct;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import org.apache.log4j.Logger;
//
///**
// * @author Daud Ainoo
// * @Company Sabonay
// * @Contact 0245 293945
// * @Website https://sabonay.com
// * @date Wed 20 Dec, 2023 04:52 am
// */
//@Stateless
//public class BillingUtilsServices extends CrudController implements Serializable {
//
//    private static final Logger log = Logger.getLogger(BillingUtilsServices.class.getName());
//    @Inject
//    private BasicServices basicServices;
//    @Inject
//    private UtitlityServices utitlityServices;
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @PostConstruct
//    private void init() {
//        setEm(em);
//        setEnviroment(Enviroment.JAVA_EE);
//    }
//
//    public static HeaderResponse processLedgerAndHouseRentBills(PropertyLedger ledger, String monthName, Integer billYear) {
//        PropertyLedger propertyLedger = new PropertyLedger();
//        propertyLedger.setEstateProperty(eachOne.getEstateProperty());
//        propertyLedger.setLedgerYear(ledgerYear);
//        propertyLedger.setamountPaid(request.getRentAmount());
//        propertyLedger.setDateOfRecordEntry(new Date());
//        propertyLedger.setDateOfRecordTransaction(eachMonthBill.getFirstDay());
//        propertyLedger.setDebitCreditEntry(DebitCredit.DEBIT.getLabel());
//        propertyLedger.setPaymentType(PaymentType.HOUSE_RENT.getLabel());
//        propertyLedger.setOccupant(eachOne.getOccupant());
//        propertyLedger.setPayeeName(AdConstants.SHC);
//        propertyLedger.setMediumOfPayment(AdConstants.NONE);
//        propertyLedger.setMediumOfPaymentNumber(AdConstants.NONE);
//        propertyLedger.setPaymentFor("Property rent ," + MsgFormatter.sentenceCase(monthName) + " " + ledgerYear);
//        String id = IdGeneratorServices.generateEstateLedgerId(propertyLedger, monthName);
//        propertyLedger.setRecordId(id);
//        log.info(propertyLedger + "\t");
//        List<PropertyLedger> propertyLedgers = basicServices.searchRecordsStrict(PropertyLedger.class, EntityFields.recordId, propertyLedger.getRecordId());
//        if (null == propertyLedgers) {
//            log.error("Error finding property ledger " + eachOne.getRecordId() + " skip");
//            continue;
//        }
//        if (propertyLedgers.isEmpty()) {
//            log.info("First time rent bill for " + monthName + "," + ledgerYear);
//            propertyLedger.setCreatedBy(request.getCreatedBy());
//            propertyLedger.setCreatedDate(new Date());
//        } else {
//            PropertyLedger ledgerAlreadyExist = propertyLedgers.get(0);
//            log.info("Updated existing rent bill for " + monthName + "," + ledgerYear);
//            propertyLedger.setCreatedBy(ledgerAlreadyExist.getCreatedBy());
//            propertyLedger.setCreatedDate(ledgerAlreadyExist.getCreatedDate());
//            propertyLedger.setLastModifiedDate(new Date());
//            propertyLedger.setLastModifiedBy(request.getCreatedBy());
//        }
//
//        String recordId = propertyLedger.getRecordId();
//        Bills bills = new Bills();
//        bills = basicServices.find(Bills.class, recordId);
//        log.info(" record id " + recordId);
//
//        if (Objects.isNull(bills)) {
//            log.info("Bill not found;first time billing");
//            log.info(" record id " + recordId);
//            bills = new Bills();
//            bills.setRecordId(recordId);
//            bills.setCreatedBy(request.getCreatedBy());
//            bills.setCreatedDate(new Date());
//
//            bills.setBillStatus(BillStatus.UNPAID.getLabel());
//            bills.setEntryType(DebitCredit.DEBIT.getLabel());
//            bills.setBillAmountPaid(0.0);
//            bills.setBillAmount(request.getRentAmount());
//
//        } else {
//            log.info("Bill found;existing billing");
//            bills.setBillAmount(request.getRentAmount());
//            Double arrears = bills.getBillAmount() - bills.getBillAmountPaid();
//            if (arrears > 0 && bills.getBillAmountPaid() > 0) {
//                log.info("some payment has been made before but "
//                        + "there is still arrears, set bill status=PART PAYMENT & entry type=DEBIT BAL");
//                bills.setBillStatus(BillStatus.PART_PAYMENT.getLabel());
//                bills.setEntryType(DebitCredit.DEBIT_BAL.getLabel());
//            } else if (arrears < 0) {
//                log.info("there is more payment than arrears "
//                        + "set bill status=PAID & entry type=CREDIT BAL");
//                bills.setBillStatus(BillStatus.PAID.getLabel());
//                bills.setEntryType(DebitCredit.CREDIT_BAL.getLabel());
//            } else if (arrears == 0) {
//                log.info("bill amount is equal to bill amount paid, no arrears "
//                        + "set bill status=PAID & entry type=NONE");
//                bills.setBillStatus(BillStatus.PAID.getLabel());
//                bills.setEntryType(DebitCredit.NONE.getLabel());
//            }
//            bills.setLastModifiedBy(request.getCreatedBy());
//            bills.setLastModifiedDate(new Date());
//        }
//        bills.setBillMonth(eachMonthBill.getMonthName());
//        bills.setDeleted(false);
//        bills.setLastDrDate(new Date());
//        bills.setLastDrRecordedBy(request.getCreatedBy());
//        bills.setBillYear(ledgerYear);
//        bills.setBillType(PaymentType.HOUSE_RENT.getLabel());
//        bills.setBillPenaltyAmount(0.0);
//        bills.setOccupant(eachOne.getOccupant());
//        bills.setEstateProperty(eachOne.getEstateProperty());
//        basicServices.update(propertyLedger);
//        basicServices.update(bills);
//        return null;
//    }
//}
