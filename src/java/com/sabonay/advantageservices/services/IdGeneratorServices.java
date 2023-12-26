package com.sabonay.advantageservices.services;

import com.ctrloption.constants.DebitCredit;
import com.ctrloption.utils.GenUUID;
import com.sabonay.advantageservices.entities.estatebilling.PropertyCharge;
import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
import com.sabonay.advantageservices.utils.enums.PaymentType;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

/**
 *
 * @author dainoo
 */
@Stateless
public class IdGeneratorServices extends GenUUID implements Serializable {

    private static final Logger log = Logger.getLogger(IdGeneratorServices.class.getName());

    public static String APPLICATION_NAME = "supermart";
    public static String ZERO_STRING = "0";
    public static String ZERO_STRING_SIX_PATTERN = "000000";
    public static String ZERO_STRING_9_PATTERN = "000000000";
    public static String ZERO_STRING_3_PATTERN = "000";
    public static String ZERO_STRING_THREE_PATTERN = "000";
    public static String ZERO_STRING_EIGHT_PATTERN = "00000000";
    private static String id = "";

    @Inject
    private BasicServices crudServices;

    public String generateEntityId(String entityName, String companyInitials) {
        String id;
        id = crudServices.genIdGetNextIdString(companyInitials + entityName,
                ZERO_STRING, APPLICATION_NAME,
                ZERO_STRING_SIX_PATTERN);
        return companyInitials + "-" + id;
    }

    public static String getPropertyChargeId(PropertyCharge pc) {
        return (String.valueOf(pc.getChargeYear()) + "#" + pc.getPropertyUsage() + "#" + pc.getRegion().getRecordId()).toUpperCase();
    }

    public static void setEstateLedgerId(PropertyLedger estatePropertyLedger, String month) {
        if (estatePropertyLedger.getDebitCreditEntry() == null) {
            return;
        }
        if (estatePropertyLedger.getEstateProperty() == null) {
            return;
        }

        if (estatePropertyLedger.getLedgerYear() == null) {
            return;
        }

        if (estatePropertyLedger.getLedgerYear() < 1979) {
            return;
        }

        System.out.println("in idsetter := " + estatePropertyLedger.getEstateProperty() + "  "
                + " po: " + estatePropertyLedger.getRecordId() + "  "
                + estatePropertyLedger.getPaymentType());

        id = estatePropertyLedger.getEstateProperty().getRecordId() + "/"
                + estatePropertyLedger.getPaymentType() + "-"
                + estatePropertyLedger.getDebitCreditEntry() + "/"
                + estatePropertyLedger.getLedgerYear();

        if (estatePropertyLedger.getPaymentType().equals(PaymentType.GROUND_RENT.getLabel())) {
            if (estatePropertyLedger.getDebitCreditEntry().equals(DebitCredit.CREDIT.getLabel())) {
                id += "/" + "{" + estatePropertyLedger.getReceiptNumberIssued() + "}";
            } else if (estatePropertyLedger.getReceiptNumberIssued() != null && estatePropertyLedger.getDebitCreditEntry().equals(DebitCredit.DEBIT.getLabel())) {
                id += "/" + "{" + estatePropertyLedger.getReceiptNumberIssued() + "}";
            }
        } else if (estatePropertyLedger.getPaymentType().equals(PaymentType.HOUSE_RENT.getLabel())) {
            if (estatePropertyLedger.getDebitCreditEntry().equals(DebitCredit.DEBIT.getLabel())) {
                if (month == null) {
                    throw new RuntimeException("Month cannot be null");
                }
                id = id + "#" + month;
            } else if (estatePropertyLedger.getDebitCreditEntry().equals(DebitCredit.CREDIT.getLabel())) {
                id += "/" + "{" + estatePropertyLedger.getReceiptNumberIssued() + "}";
            }
        }
        estatePropertyLedger.setRecordId(id.toUpperCase());
    }

    public static String generateEstateLedgerId(PropertyLedger estatePropertyLedger, String month) {
        if (estatePropertyLedger.getDebitCreditEntry() == null) {
            return "";
        }
        if (estatePropertyLedger.getEstateProperty() == null) {
            return "";
        }

        if (estatePropertyLedger.getLedgerYear() == null) {
            return "";
        }

        if (estatePropertyLedger.getLedgerYear() < 1979) {
            return "";
        }

        System.out.println("in idsetter := " + estatePropertyLedger.getEstateProperty() + "  "
                + " po: " + estatePropertyLedger.getRecordId() + "  "
                + estatePropertyLedger.getPaymentType());

        id = estatePropertyLedger.getEstateProperty().getRecordId() + "/"
                + estatePropertyLedger.getPaymentType() + "-"
                + estatePropertyLedger.getDebitCreditEntry() + "/"
                + estatePropertyLedger.getLedgerYear();

        if (estatePropertyLedger.getPaymentType().equals(PaymentType.GROUND_RENT.getLabel())) {
            if (estatePropertyLedger.getDebitCreditEntry().equals(DebitCredit.CREDIT.getLabel())) {
                id += "/" + "{" + estatePropertyLedger.getReceiptNumberIssued() + "}";
            } else if (estatePropertyLedger.getReceiptNumberIssued() != null && estatePropertyLedger.getDebitCreditEntry().equals(DebitCredit.DEBIT.getLabel())) {
                id += "/" + "{" + estatePropertyLedger.getReceiptNumberIssued() + "}";
            }
        } else if (estatePropertyLedger.getPaymentType().equals(PaymentType.HOUSE_RENT.getLabel())) {
            if (estatePropertyLedger.getDebitCreditEntry().equals(DebitCredit.DEBIT.getLabel())) {
                if (month == null) {
                    throw new RuntimeException("Month cannot be null");
                }
                id = id + "#" + month;
            } else if (estatePropertyLedger.getDebitCreditEntry().equals(DebitCredit.CREDIT.getLabel())) {
                id += "/" + "{" + estatePropertyLedger.getReceiptNumberIssued() + "}";
            }
        }

        estatePropertyLedger.setRecordId(id);
        return id.toUpperCase();
    }

    public static String generateGroundRentBillId(PropertyLedger ledger) {
        String ids;
        log.info("property id " + ledger.getEstateProperty().getRecordId());
        log.info("occupant id " + ledger.getOccupant().getRecordId());
        log.info("payment type " + ledger.getPaymentType());
        log.info("ledger year " + ledger.getLedgerYear());
        ids = ledger.getEstateProperty().getRecordId() + "/" + ledger.getOccupant().getRecordId()
                + "/" + ledger.getPaymentType() + "/" + ledger.getLedgerYear();
        return ids;
    }

    public static String generateHouseRentBillId(PropertyLedger ledger, String month) {
        String ids;
        ids = ledger.getEstateProperty().getRecordId() + "/" + ledger.getOccupant().getRecordId()
                + "/" + ledger.getPaymentType() + "/" + month;
        return ids;
    }

}
