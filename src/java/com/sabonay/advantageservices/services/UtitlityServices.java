package com.sabonay.advantageservices.services;

import com.ctrloption.constants.DebitCredit;
import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.ctrloption.models.DateRange;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatebilling.PropertyCharge;
import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.entities.occupancy.OccupantProperty;
import com.sabonay.advantageservices.models.DataFetchType;
import com.sabonay.advantageservices.models.estatebilling.PropertyLedgerInfo;
import com.sabonay.advantageservices.models.occupancy.OccupantPropertyInfo;
import com.sabonay.advantageservices.restmodels.billpayment.PropertyLedgerEntriesRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupantPropertyListResponse;
import com.sabonay.advantageservices.restmodels.occupancy.OccupiedPropertyRequest;
import com.sabonay.advantageservices.utils.AppConstants;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import com.sabonay.advantageservices.utils.enums.PropertyUsage;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
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
 * @date Wed 16 Aug, 2023 20:02 pm
 */
@Stateless
public class UtitlityServices extends CrudController implements Serializable {

    private static final Logger log = Logger.getLogger(UtitlityServices.class.getName());
    private final double UNIT_SIZE = 0.25;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        setEm(em);
        setEnviroment(Enviroment.JAVA_EE);
    }

    public Double getGroundRentBill(EstateProperty estateProperty, Integer chargeYear) {
        try {
            QryBuilder builder = new QryBuilder(em, PropertyCharge.class);
            builder.addNumberParam(EntityFields.chargeYear, chargeYear, QryBuilder.ComparismCriteria.EQUAL);
            builder.addStringQryParam(EntityFields.propertyUsage, estateProperty.getPropertyUsage(), QryBuilder.ComparismCriteria.LIKE);
            builder.addObjectParam(EntityFields.deleted, false);
            PropertyCharge propertyCharge = (PropertyCharge) builder.buildQry().getSingleResult();
            Double classAmount = 0.0;
            if (estateProperty.getEstateBlock().getEstate().getEstateClass().equalsIgnoreCase("FIRST_CLASS")) {
                classAmount = propertyCharge.getFirstClassCharge();
            } else if (estateProperty.getEstateBlock().getEstate().getEstateClass().equalsIgnoreCase("SECOND_CLASS")) {
                classAmount = propertyCharge.getSecondClassCharge();
            } else if (estateProperty.getEstateBlock().getEstate().getEstateClass().equalsIgnoreCase("THIRD_CLASS")) {
                classAmount = propertyCharge.getThirdClassCharge();
            }
            log.info(" classAmount --- " + classAmount);
            if (estateProperty.getPropertyUsage().equalsIgnoreCase(PropertyUsage.ALL.getLabel())) {
                log.info(" classAmount PropertyUsage.ALL " + classAmount);
                return classAmount;
            } else {
                //For other usages
                if (chargeYear < 2008) {
                    log.info(" classAmount chargeYear < 2008 " + classAmount);
                    return classAmount;
                }
                double landSize = estateProperty.getPropertyLandSize();
                if (landSize > UNIT_SIZE) {
                    classAmount = (landSize / UNIT_SIZE) * classAmount;
                    log.info(" classAmount UNIT_SIZE" + classAmount);
                    return classAmount;
                }
            }
            return classAmount;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public List<OccupantProperty> getOccupantProperties4Billing(DataFetchType fetchType) {
        Date checkDate = new Date();
        List<OccupantProperty> listOfOccupantProperties = new ArrayList<>();
        try {
            String qry = "SELECT e FROM OccupantProperty e "
                    + "WHERE e." + fetchType.getBillTypeQuery() + " =:searchValue "
                    + "AND e.occupationType =:occType "
                    + "AND e.firstDateOfOccupancy <=:checkDate "
                    + "AND (e.lastDateOfOccupancy IS NULL "
                    + "OR e.lastDateOfOccupancy >=:checkDate) "
                    + "ORDER BY e.firstDateOfOccupancy ASC";
            log.info("getOccupantProperties4Billing parameters billingType: " + fetchType.getBillingType() + " "
                    + fetchType.getSearchValue() + " occupationType: "
                    + fetchType.getOccupationType() + " checkDate: " + checkDate);
            AppLogger.info(log, " qry fetch getOccupantProperties4Billing " + qry);
            Query query = em.createQuery(qry)
                    .setParameter("searchValue", fetchType.getSearchValue())
                    .setParameter("occType", fetchType.getOccupationType())
                    .setParameter("checkDate", checkDate, TemporalType.DATE);
            listOfOccupantProperties = query.getResultList();
            return listOfOccupantProperties;
        } catch (Exception e) {
            AppLogger.error(log, e, "Error processing getOccupantProperties4Billing request");
            return null;
        }
    }

    public List<OccupantProperty> getAllOccupantProperties4Billing(String occupationType) {
        Date checkDate = new Date();
        List<OccupantProperty> listOfOccupantProperties = new ArrayList<>();
        try {
            String occuType = "AND e.occupationType =:occType ";
            String orderBy = "ORDER BY e.firstDateOfOccupancy ASC ";
            String qry = "SELECT e FROM OccupantProperty e "
                    + "WHERE e.firstDateOfOccupancy <=:checkDate "
                    + "AND (e.lastDateOfOccupancy IS NULL "
                    + "OR e.lastDateOfOccupancy >=:checkDate) ";
            log.info("getAllOccupantProperties4Billing parameter  occupationType: " + occupationType + " checkDate: " + checkDate);
            Query query = null;
            if (occupationType.equalsIgnoreCase(AppConstants.ALL)) {
                qry += occuType;
                query = em.createQuery(qry)
                        .setParameter("checkDate", checkDate, TemporalType.DATE);
            } else {
                query = em.createQuery(qry)
                        .setParameter("occType", occupationType)
                        .setParameter("checkDate", checkDate, TemporalType.DATE);
            }
            qry += orderBy;
            AppLogger.info(log, " qry fetch getOccupantProperties4Billing " + qry);
            listOfOccupantProperties = query.getResultList();
            return listOfOccupantProperties;
        } catch (Exception e) {
            AppLogger.error(log, e, "Error processing getAllOccupantProperties4Billing request");
            return null;
        }
    }

    public OccupantPropertyListResponse fetchQuickSearchOccupiedProperties(OccupiedPropertyRequest request) {
        Date checkDate = new Date();
        List<OccupantProperty> listOfOccupantProperties = new ArrayList<>();
        OccupantPropertyListResponse response = new OccupantPropertyListResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        List<OccupantPropertyInfo> occupants = new ArrayList<>();

        try {
            if (null == request.getSearchText() || request.getSearchText().isEmpty()) {
                headerResponse.setResponseCode(ResponseCodes.SUCCESS);
                headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
                response.setHeaderResponse(headerResponse);
                response.setOccupantProperties(occupants);
            }

            String occuType = "AND e.occupationType =:occType ";
            String orderBy = "ORDER BY e.occupant.occupantName ASC ";

            String qry = "SELECT e FROM OccupantProperty e WHERE "
                    + "(e.occupant.occupantName LIKE '%" + request.getSearchText() + "%' "
                    + "OR e.occupant.mobileNo LIKE '%" + request.getSearchText() + "%' "
                    + "OR e.estateProperty.recordId LIKE '%" + request.getSearchText() + "%' "
                    + "OR e.estateProperty.propertyName LIKE '%" + request.getSearchText() + "%' "
                    + "OR e.estateProperty.propertyNumber LIKE '%" + request.getSearchText() + "%') "
                    + "AND e.firstDateOfOccupancy <=:checkDate "
                    + "AND (e.lastDateOfOccupancy IS NULL "
                    + "OR e.lastDateOfOccupancy >=:checkDate) ";
//            +"OR c." + EntityFields.otherNames + " LIKE '%" + searchValue + "%' "

            String qry1 = "SELECT e FROM OccupantProperty e WHERE "
                    + "e.occupant.occupantName=:searchText  "
                    + "AND e.firstDateOfOccupancy <=:checkDate "
                    + "AND (e.lastDateOfOccupancy IS NULL "
                    + "OR e.lastDateOfOccupancy >=:checkDate) ";

            AppLogger.info(log, " qry fetch fetchQuickSearchOccupiedProperties " + qry);
            log.info("fetchQuickSearchOccupiedProperties parameter  occupationType: " + request.getOccupationType() + " checkDate: " + checkDate);
            Query query = null;
            if (request.getOccupationType().equalsIgnoreCase(AppConstants.ALL)) {
                qry += orderBy;
                query = em.createQuery(qry)
                        //                                                .setParameter("searchText", request.getSearchText())
                        .setParameter("checkDate", checkDate, TemporalType.DATE);
            } else {
                qry += occuType;
                qry += orderBy;
                query = em.createQuery(qry)
                        //                        .setParameter("searchText", request.getSearchText())
                        .setParameter("occType", request.getOccupationType())
                        .setParameter("checkDate", checkDate, TemporalType.DATE);
            }
//            AppLogger.info(log, " qry fetch getOccupantProperties4Billing " + qry);
            listOfOccupantProperties = query.getResultList();
            if (null == listOfOccupantProperties) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }
            log.info("total occupied properties retrieved " + listOfOccupantProperties.size());
            if (!listOfOccupantProperties.isEmpty()) {
                for (OccupantProperty eachOne : listOfOccupantProperties) {
                    occupants.add(new OccupantPropertyInfo(eachOne));
                }
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setOccupantProperties(occupants);
            return response;
        } catch (Exception e) {
            AppLogger.error(log, e, "Error processing getAllOccupantProperties4Billing request");
            return null;
        }
    }

    public List<EstateProperty> getPropertiesOccupiedByBlock(String blockId, String occupationType) {
        Date checkDate = new Date();
        List<EstateProperty> listOfOccupantProperties = new ArrayList<>();
        String billTypeQuery = "";
        try {

            billTypeQuery = "estateProperty.estateBlock.recordId";
            String qry = "SELECT e.estateProperty FROM OccupantProperty e "
                    + "WHERE e." + billTypeQuery + " =:searchValue "
                    + "AND e.occupationType =:occType "
                    + "AND e.firstDateOfOccupancy <=:checkDate "
                    + "AND (e.lastDateOfOccupancy IS NULL "
                    + "OR e.lastDateOfOccupancy >=:checkDate) "
                    + "ORDER BY e.firstDateOfOccupancy ASC";
            log.info("getPropertiesOccupiedByBlock parameters billingType: BLK "
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

    public PropertyLedger lastDebitLedgerEntryForProperty(String estatePropertyId) {
        PropertyLedger epl = null;
        String qry = "SELECT pl FROM PropertyLedger pl "
                + "WHERE pl.debitCreditEntry = '" + DebitCredit.DEBIT.getLabel() + "' "
                + "AND pl.estateProperty.recordId = '" + estatePropertyId + "' "
                + "ORDER BY pl.dateOfRecordEntry DESC";
        try {
            epl = (PropertyLedger) em.createQuery(qry).setMaxResults(1).setFirstResult(0).getSingleResult();
            return epl;
        } catch (Exception e) {
            AppLogger.error(log, e, "Error getting lastDebitLedgerEntryForProperty");
        }
        return null;
    }

    public List<PropertyLedgerInfo> getOccupantPropertyLedgerEntries(PropertyLedgerEntriesRequest request) throws IOException {
        try {
            String msg = "";
            AppLogger.printPayload(log, "getOccupantPropertyLedgerEntries request ", request);
            List<PropertyLedger> listOfPropertyLedgers = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, PropertyLedger.class);
            if (request.getSearchBy().equalsIgnoreCase("DR")) {
                DateRange dateRange = new DateRange(request.getStartDate(), request.getEndDate());
                builder.addDateRange(dateRange, EntityFields.dateOfRecordEntry);
            } else {
                builder.addStringQryParam(request.getSearchBy(), request.getSearchValue(), QryBuilder.ComparismCriteria.LIKE);
            }
            builder.addStringQryParam(EntityFields._occupant, request.getOccupantId(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addStringQryParam(EntityFields._property, request.getPropertyId(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addObjectParam(EntityFields.deleted, false);
            builder.orderByDesc(EntityFields.dateOfRecordEntry);
            log.info(" getOccupantPropertyLedgerEntries " + builder.getQryInfo());
            listOfPropertyLedgers = builder.buildQry().getResultList();
            if (null == listOfPropertyLedgers) {
                return null;
            }
            log.info("total ledgers entries retrieved " + listOfPropertyLedgers.size());
            List<PropertyLedgerInfo> ledgers = new ArrayList<>();
            if (!listOfPropertyLedgers.isEmpty()) {
                for (PropertyLedger eachOne : listOfPropertyLedgers) {
                    ledgers.add(new PropertyLedgerInfo(eachOne));
                }
            }
            return ledgers;
        } catch (Exception e) {
            AppLogger.error(log, e, "getOccupantPropertyLedgerEntries IOException");
            return null;
        }
    }

    public List<PropertyLedgerInfo> getBillPayments(PropertyLedgerEntriesRequest request) throws IOException {
        try {
            String msg = "";
            AppLogger.printPayload(log, "bill payment search parameters request ", request);
            List<PropertyLedger> listOfPropertyLedgers = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, PropertyLedger.class);
            if (request.getSearchBy().equalsIgnoreCase("BAB")) {
                DateRange dateRange = new DateRange(request.getStartDate(), request.getEndDate());
                builder.addStringQryParam(EntityFields._block_property, request.getSearchValue(), QryBuilder.ComparismCriteria.EQUAL);
                builder.addDateRange(dateRange, EntityFields.dateOfRecordEntry);
            } else {
                builder.addStringQryParam(request.getSearchBy(), request.getSearchValue(), QryBuilder.ComparismCriteria.LIKE);
            }
            builder.addObjectParam(EntityFields.deleted, false);
            builder.orderByDesc(EntityFields.dateOfRecordEntry);
            log.info(" getBillPayments " + builder.getQryInfo());
            listOfPropertyLedgers = builder.buildQry().getResultList();
            if (null == listOfPropertyLedgers) {
                return null;
            }
            log.info("total ledgers entries retrieved " + listOfPropertyLedgers.size());
            List<PropertyLedgerInfo> ledgers = new ArrayList<>();
            if (!listOfPropertyLedgers.isEmpty()) {
                for (PropertyLedger eachOne : listOfPropertyLedgers) {
                    ledgers.add(new PropertyLedgerInfo(eachOne));
                }
            }
            return ledgers;
        } catch (Exception e) {
            AppLogger.error(log, e, "getOccupantPropertyLedgerEntries IOException");
            return null;
        }
    }

    public List<PropertyLedgerInfo> getOccupantPropertyLedgerEntriesSinceInception(String occupantId, String propertyId, String entryType) throws IOException {
        try {
            String msg = "";
            AppLogger.printPayload(log, "getOccupantPropertyLedgerEntriesSinceInception request ", propertyId);
            List<PropertyLedger> listOfPropertyLedgers = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, PropertyLedger.class);
            builder.addStringQryParam(EntityFields._occupant, occupantId, QryBuilder.ComparismCriteria.EQUAL);
            builder.addStringQryParam(EntityFields._property, propertyId, QryBuilder.ComparismCriteria.EQUAL);
            if (!entryType.equalsIgnoreCase(AppConstants.ALL)) {
                builder.addStringQryParam(EntityFields.entryType, entryType, QryBuilder.ComparismCriteria.EQUAL);
            }
            builder.addObjectParam(EntityFields.deleted, false);
            builder.orderByDesc(EntityFields.dateOfRecordEntry);
            log.info(" getOccupantPropertyLedgerEntries " + builder.getQryInfo());
            listOfPropertyLedgers = builder.buildQry().getResultList();
            if (null == listOfPropertyLedgers) {
                return null;
            }
            log.info("total ledgers entries retrieved " + listOfPropertyLedgers.size());
            List<PropertyLedgerInfo> ledgers = new ArrayList<>();
            if (!listOfPropertyLedgers.isEmpty()) {
                for (PropertyLedger eachOne : listOfPropertyLedgers) {
                    ledgers.add(new PropertyLedgerInfo(eachOne));
                }
            }
            return ledgers;
        } catch (Exception e) {
            AppLogger.error(log, e, "getOccupantPropertyLedgerEntries IOException");
            return null;
        }
    }

    public Double occupantCurrentBal(String occupantId, String propertyId) {
        try {

            List<PropertyLedger> listOfPropertyLedgers = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, PropertyLedger.class);
            builder.addStringQryParam(EntityFields._occupant, occupantId, QryBuilder.ComparismCriteria.EQUAL);
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
                    totalCredit += eachOne.getAmountInvolved();
                } else if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.DEBIT.getLabel())) {
                    totalDebit += eachOne.getAmountInvolved();
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

    public Double[] occupantCurrentBalWithinDateRange(String occupantId, String propertyId, Date startDate, Date endDate) {
        try {

            List<PropertyLedger> listOfPropertyLedgers = new ArrayList<>();
            DateRange dateRange = new DateRange(startDate, endDate);
            QryBuilder builder = new QryBuilder(em, PropertyLedger.class);
            builder.addStringQryParam(EntityFields._occupant, occupantId, QryBuilder.ComparismCriteria.EQUAL);
            builder.addStringQryParam(EntityFields._property, propertyId, QryBuilder.ComparismCriteria.EQUAL);
            builder.addDateRange(dateRange, EntityFields.dateOfRecordEntry);
            builder.addObjectParam(EntityFields.deleted, false);
            listOfPropertyLedgers = builder.buildQry().getResultList();
            log.info("total occupantCurrentBalWithinDateRange " + listOfPropertyLedgers.size());

            Double[] totals = new Double[3];
            if (listOfPropertyLedgers.isEmpty()) {
                totals[0] = 0.0;
                totals[1] = 0.0;
                totals[2] = 0.0;
                return totals;
            }
            Double totalDebit = 0.0, totalCredit = 0.0, balance = 0.0;
            for (PropertyLedger eachOne : listOfPropertyLedgers) {
                if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.CREDIT.getLabel())) {
                    totalCredit += eachOne.getAmountInvolved();
                } else if (eachOne.getDebitCreditEntry().equalsIgnoreCase(DebitCredit.DEBIT.getLabel())) {
                    totalDebit += eachOne.getAmountInvolved();
                }
            }
            log.info(" totalDebit: " + totalDebit + " totalDebit: " + totalDebit);
            balance = totalDebit - totalCredit;
            log.info(" current balance: " + balance);
            totals[0] = totalDebit;
            totals[1] = totalCredit;
            totals[2] = balance;
            return totals;
        } catch (Exception e) {
            AppLogger.error(log, e, "Error processing occupantCurrentBal request");
            return null;
        }
    }

    public Double[] occupantCurrentBalWithinDateRange1(String occupantId, String propertyId, Date startDate, Date endDate) {
        try {
            String qry = "SELECT SUM(CASE WHEN l.type = 'CREDIT' THEN l.amountInvolved ELSE 0 END) AS credit_total, "
                    + "SUM(CASE WHEN l.type = 'DEBIT' THEN l.amountInvolved ELSE 0 END) AS debit_total, "
                    + "SUM(CASE WHEN l.type = 'DEBIT' THEN l.amountInvolved ELSE - l.amountInvolved END) AS balance "
                    + "FROM PropertyLedger l WHERE "
                    + "e.occupant.recordId =:occupantId "
                    + "e.estateProperty.recordId =:propertyId "
                    + "l.dateOfRecordEntry >= :startDate AND l.dateOfRecordEntry <= :endDate";
            Query query = em.createQuery(qry);
            query.setParameter("occupantId", occupantId);
            query.setParameter("propertyId", propertyId);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            Object[] result = (Object[]) query.getSingleResult();
            double creditTotal = result[0] != null ? (double) result[0] : 0.0;
            double debitTotal = result[1] != null ? (double) result[1] : 0.0;
            double balance = result[2] != null ? (double) result[2] : 0.0;
            log.info("debitTotal: " + debitTotal + " creditTotal: " + creditTotal + " balance: " + balance);
            return new Double[]{creditTotal, debitTotal, balance};
        } catch (Exception e) {
            AppLogger.error(log, e, "Error processing occupantCurrentBal request");
            return null;
        }
    }

    public DateRange getDateRangeFromMonthName(String rentMonth) {
        try {
            // Specify the month name
            String monthName = rentMonth;

            // Get the current year
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);

            // Create a SimpleDateFormat object to format the date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Set the month and year in the Calendar object
            calendar.set(Calendar.MONTH, getMonthNumber(monthName));
            calendar.set(Calendar.YEAR, year);

            // Get the first and last date of the specified month
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date firstDate = calendar.getTime();

            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date lastDate = calendar.getTime();

            DateRange dateRange = new DateRange();
            dateRange.setToDate(lastDate);
            dateRange.setFromDate(firstDate);

            // Format and print the first and last date
            log.info("Start date of " + monthName + ": " + dateFormat.format(firstDate));
            log.info("Last date of " + monthName + ": " + dateFormat.format(lastDate));
            return dateRange;

        } catch (Exception e) {
            return null;
        }

    }

    private static int getMonthNumber(String monthName) {
        switch (monthName.toLowerCase()) {
            case "january":
                return Calendar.JANUARY;
            case "february":
                return Calendar.FEBRUARY;
            case "march":
                return Calendar.MARCH;
            case "april":
                return Calendar.APRIL;
            case "may":
                return Calendar.MAY;
            case "june":
                return Calendar.JUNE;
            case "july":
                return Calendar.JULY;
            case "august":
                return Calendar.AUGUST;
            case "september":
                return Calendar.SEPTEMBER;
            case "october":
                return Calendar.OCTOBER;
            case "november":
                return Calendar.NOVEMBER;
            case "december":
                return Calendar.DECEMBER;
            default:
                throw new IllegalArgumentException("Invalid month name: " + monthName);
        }
    }

}
