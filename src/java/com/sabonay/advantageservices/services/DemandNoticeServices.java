package com.sabonay.advantageservices.services;

import com.ctrloption.constants.DebitCredit;
import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
import com.sabonay.advantageservices.entities.occupancy.OccupantProperty;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 26 Nov, 2023 10:37 am
 */
public class DemandNoticeServices extends CrudController implements Serializable {

    private static final Logger log = Logger.getLogger(DemandNoticeServices.class.getName());

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        setEm(em);
        setEnviroment(Enviroment.JAVA_EE);
    }


    public Double getLedgerBalance(String propertyId, Integer ledgerYear, boolean yearInQuestion) {
        try {
            List<PropertyLedger> listOfPropertyLedgers = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, PropertyLedger.class);
            if (yearInQuestion) {
                builder.addNumberParam(EntityFields.ledgerYear, ledgerYear, QryBuilder.ComparismCriteria.EQUAL);
            } else {
                builder.addNumberParam(EntityFields.ledgerYear, ledgerYear, QryBuilder.ComparismCriteria.LESS_THAN);
            }
            builder.addStringQryParam(EntityFields._property, propertyId, QryBuilder.ComparismCriteria.EQUAL);
            builder.addObjectParam(EntityFields.deleted, false);
            log.info(" getLedgerBalance " + builder.getQryInfo());
            listOfPropertyLedgers = builder.buildQry().getResultList();
            log.info("total occupantCurrentBal " + listOfPropertyLedgers.size());
            if (listOfPropertyLedgers.isEmpty()) {
                return 0.0;
            }
            Double totalDebit = 0.0, totalCredit = 0.0, balance = 0.0;
            for (PropertyLedger eachOne : listOfPropertyLedgers) {
                if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.CREDIT.getLabel())) {
                    totalCredit += eachOne.getamountPaid();
                } else if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.DEBIT.getLabel())) {
                    totalDebit += eachOne.getamountPaid();
                }
            }
            log.info(" totalDebit: " + totalDebit + " totalDebit: " + totalDebit);
            balance = totalDebit - totalCredit;
            log.info(" current balance: " + balance);
            return balance;
        } catch (Exception e) {
            AppLogger.error(log, e, "Error processing occupantCurrentBal request");
            return null;
        }
    }

    public Double getPreviousYearsBal(String propertyId, Integer ledgerYear) {
        try {
            List<PropertyLedger> listOfPropertyLedgers = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, PropertyLedger.class);
            builder.addNumberParam(EntityFields.ledgerYear, ledgerYear, QryBuilder.ComparismCriteria.LESS_THAN);
            builder.addStringQryParam(EntityFields._property, propertyId, QryBuilder.ComparismCriteria.EQUAL);
            builder.addObjectParam(EntityFields.deleted, false);
            log.info(" occupantCurrentBal " + builder.getQryInfo());
            listOfPropertyLedgers = builder.buildQry().getResultList();
            log.info("total occupantCurrentBal " + listOfPropertyLedgers.size());
            if (listOfPropertyLedgers.isEmpty()) {
                return 0.0;
            }
            Double totalDebit = 0.0, totalCredit = 0.0, balance = 0.0;
            for (PropertyLedger eachOne : listOfPropertyLedgers) {
                if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.CREDIT.getLabel())) {
                    totalCredit += eachOne.getamountPaid();
                } else if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.DEBIT.getLabel())) {
                    totalDebit += eachOne.getamountPaid();
                }
            }
            log.info(" totalDebit: " + totalDebit + " totalDebit: " + totalDebit);
            balance = totalDebit - totalCredit;
            log.info(" current balance: " + balance);
            return balance;
        } catch (Exception e) {
            AppLogger.error(log, e, "Error processing occupantCurrentBal request");
            return null;
        }
    }

    public Double generateDemandNotice(String estateId, int leadgerYear) {
        PropertyLedger epl = null;
        int yearBefore = leadgerYear - 1;
        String qry = "SELECT pl FROM PropertyLedger pl "
                + "WHERE  pl.estateProperty.estateBlock.estate.recordId = '" + estateId + "' "
                + "AND pl.leadgerYear <=:leadgerYear  "
                + "ORDER BY pl.dateOfRecordEntry DESC";
        List<PropertyLedger> listOfPropertyLedgers = new ArrayList<>();

        try {
            Query query = em.createQuery(qry)
                    .setParameter("estateId", estateId)
                    .setParameter("leadgerYear", leadgerYear);
            listOfPropertyLedgers = query.getResultList();
            log.info("total occupantCurrentBal " + listOfPropertyLedgers.size());
            if (listOfPropertyLedgers.isEmpty()) {
                return 0.0;
            }
            Double totalDebit = 0.0, totalCredit = 0.0, balance = 0.0;
            for (PropertyLedger eachOne : listOfPropertyLedgers) {
                if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.CREDIT.getLabel())) {
                    totalCredit += eachOne.getamountPaid();
                } else if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.DEBIT.getLabel())) {
                    totalDebit += eachOne.getamountPaid();
                }
            }
            log.info(" totalDebit: " + totalDebit + " totalDebit: " + totalDebit);
            balance = totalDebit - totalCredit;
            log.info(" current balance: " + balance);
            return balance;
        } catch (Exception e) {
            AppLogger.error(log, e, "Error getting lastDebitLedgerEntryForProperty");
        }
        return null;
    }

    //generate previous year balances or arrears , thus selected year minus 1 year
    public List<OccupantProperty> getOccupantPropertiesByBlock(String blockId, String occupationType) {
        Date checkDate = new Date();
        List<OccupantProperty> listOfOccupantProperties = new ArrayList<>();
        String billTypeQuery = "";
        try {

            billTypeQuery = "estateProperty.estateBlock.recordId";
            String qry = "SELECT e FROM OccupantProperty e "
                    + "WHERE e." + billTypeQuery + " =:searchValue "
                    + "AND e.occupationType =:occType "
                    + "AND e.firstDateOfOccupancy <=:checkDate "
                    + "AND (e.lastDateOfOccupancy IS NULL "
                    + "OR e.lastDateOfOccupancy >=:checkDate) "
                    + "ORDER BY e.firstDateOfOccupancy ASC";
            log.info("getOccupantProperties4Billing parameters billingType: BLK "
                    + blockId + " occupationType: "
                    + "" + occupationType + " checkDate: " + checkDate);
            AppLogger.info(log, " qry fetch getOccupantProperties4Billing " + qry);
            Query query = em.createQuery(qry)
                    .setParameter("searchValue", blockId)
                    .setParameter("occType", occupationType)
                    .setParameter("checkDate", checkDate, TemporalType.DATE);
            listOfOccupantProperties = query.getResultList();

            return listOfOccupantProperties;
        } catch (Exception e) {
            AppLogger.error(log, e, "Error processing getOccupantProperties4Billing request");
            return null;
        }

    }

}
