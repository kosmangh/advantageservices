package com.sabonay.advantageservices.services;

import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.ctrloption.models.DateRange;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.useraccounts.Staff;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author Daud
 */
@Stateless
public class BasicServices extends CrudController implements Serializable {

    @PersistenceContext
    private EntityManager em;
    private static final Logger log = Logger.getLogger(BasicServices.class.getName());

    @PostConstruct
    private void init() {
        setEm(em);
        setEnviroment(Enviroment.JAVA_EE);
    }

    public int runQry(String qry) {
        int affectedResult = em.createQuery(qry).executeUpdate();
        String msg = affectedResult + " Updates on runing : " + qry;
        log.info(msg);
        return affectedResult;
    }

    public Staff validateUser(String username, String password) {
        try {
//            AppLogger.info(log, "username:: " + username + "password:: " + password);
            QryBuilder builder = new QryBuilder(em, Staff.class);
            builder.addStringQryParam(EntityFields.username, username, QryBuilder.ComparismCriteria.EQUAL);
            builder.addStringQryParam(EntityFields.password, password, QryBuilder.ComparismCriteria.EQUAL);
            builder.addObjectParam(EntityFields.deleted, false);
            AppLogger.info(log, "validateUser qry :: " + builder.getQry());
            List<Staff> staffList = builder.buildQry().getResultList();
            if (staffList.isEmpty()) {
                return null;
            }
            return staffList.get(0);
        } catch (Exception e) {
            AppLogger.error(log, e, "validateUser exception");
            return null;
        }
    }

    public List searchRecords(Class clazz, String searchAttribute, String searchValue, String orderField) {
        try {
            log.info("Search Parameter " + searchAttribute);
            log.info("Search Value " + searchValue);
            log.info("orderField " + orderField);
            QryBuilder builder = new QryBuilder(em, clazz);
            builder.addStringQryParam(searchAttribute, searchValue, QryBuilder.ComparismCriteria.LIKE);
            builder.addObjectParam(EntityFields.deleted, false);
            builder.orderByAsc(orderField);
            builder.orderByDesc(EntityFields.createdDate);
            AppLogger.info(log, " searchRecords " + clazz.getSimpleName() + " query:" + builder.getQryInfo());
            return builder.buildQry().getResultList();
        } catch (Exception e) {
            AppLogger.error(log, e, "searchRecords exception");
            return null;
        }
    }

    public List searchRecordsStrict(Class clazz, String searchAttribute, String searchValue, String orderField1) {
        try {
            QryBuilder builder = new QryBuilder(em, clazz);
            builder.addStringQryParam(searchAttribute, searchValue, QryBuilder.ComparismCriteria.EQUAL);
            builder.addObjectParam(EntityFields.deleted, false);
            builder.orderByAsc(orderField1);
            AppLogger.info(log, " searchRecordsStrict  " + clazz.getSimpleName() + " qry :: " + builder.getQryInfo());
            if (builder.buildQry().getResultList().isEmpty()) {
                return Collections.EMPTY_LIST;
            }
            return builder.buildQry().getResultList();
        } catch (Exception e) {
            AppLogger.error(log, e, "searchRecordsStrict exception");
            return null;
        }
    }

    public List searchRecordsStrict(Class clazz, String searchAttribute, String searchValue) {
        try {
            QryBuilder builder = new QryBuilder(em, clazz);
            builder.addStringQryParam(searchAttribute, searchValue, QryBuilder.ComparismCriteria.EQUAL);
            builder.addObjectParam(EntityFields.deleted, false);
            AppLogger.info(log, " searchRecordsStrict  " + clazz.getSimpleName() + " qry :: " + builder.getQryInfo());
            if (builder.buildQry().getResultList().isEmpty()) {
                return Collections.EMPTY_LIST;
            }
            return builder.buildQry().getResultList();
        } catch (Exception e) {
            AppLogger.error(log, e, "searchRecordsStrict exception");
            return null;
        }
    }

    public List searchStaff(String searchValue) {
        try {
            String qry = "SELECT c FROM " + Staff.class.getSimpleName() + " c "
                    + "WHERE c." + EntityFields.surname + " LIKE '%" + searchValue + "%' "
                    + "OR c." + EntityFields.otherNames + " LIKE '%" + searchValue + "%' "
                    + "OR c." + EntityFields.phoneNumber + " LIKE '%" + searchValue + "%' "
                    + "OR c." + EntityFields.emailAddress + " LIKE '%" + searchValue + "%' "
                    + "ORDER BY c." + EntityFields.surname + " ASC ";
            return em.createQuery(qry).getResultList();
        } catch (Exception e) {
            AppLogger.error(log, e, "validateUser exception");
            return Collections.EMPTY_LIST;
        }
    }

    public List findAll(Class clazz, boolean searchAll, String orderField1) {
        try {
            QryBuilder builder = new QryBuilder(em, clazz);
            if (!searchAll) {
                builder.addObjectParam(EntityFields.deleted, searchAll);
            }
            builder.orderByDesc(orderField1);
            return builder.buildQry().getResultList();
        } catch (Exception e) {
            AppLogger.error(log, e, "validateUser exception");
            return null;
        }

    }

    public List findAll(Class clazz, String company, boolean searchAll, String orderField1, String orderField2) {
        try {
            QryBuilder builder = new QryBuilder(em, clazz);
            if (!searchAll) {
                builder.addObjectParam(EntityFields.deleted, searchAll);
            }
            builder.addObjectParam(EntityFields.COMPANY, company);
            builder.orderByAsc(orderField1);
            builder.orderByAsc(orderField2);
            return builder.buildQry().getResultList();
        } catch (Exception e) {
            AppLogger.error(log, e, "validateUser exception");
            return Collections.EMPTY_LIST;
        }

    }

    public List searchDateRange(Class clazz, String company, DateRange dateRange, String dateColumnName) {
        QryBuilder qryBuilder = new QryBuilder(em, clazz);
        qryBuilder.addDateRange(dateRange, dateColumnName);
        qryBuilder.addObjectParam(EntityFields.COMPANY, company);
        qryBuilder.orderByAsc(dateColumnName);
        return qryBuilder.buildQry().getResultList();
    }

    public List findAllEntitiesWithout(Class t, String company, boolean allRecords, String withoutColumn, String withoutColumnValue) {
        try {
            String qry = "SELECT e FROM " + t.getSimpleName() + " e WHERE e." + withoutColumn + " <> '" + withoutColumnValue + "'";
            qry += " AND e.deleted =" + allRecords;
            qry += " AND e.company.recordId='" + company + "'";
            AppLogger.info(log, "THE VIEW ALL QUARY IS " + qry);
            return em.createQuery(qry).getResultList();
        } catch (Exception e) {
            AppLogger.error(log, e, "validateUser exception");
        }
        return null;
    }

    public List lastRecords(Class clazz, String columnName, int maxResult) {
        try {
            QryBuilder builder = new QryBuilder(em, clazz);
            builder.orderByDesc(columnName);
            return builder.buildQry().setFirstResult(0).setMaxResults(maxResult).getResultList();
        } catch (Exception e) {
            AppLogger.error(log, e, "lastRecords");
        }
        return Collections.EMPTY_LIST;
    }

    public String generateUsername(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be null or empty.");
        }
        // Convert the full name to lowercase
        fullName = fullName.toLowerCase();

        // Remove leading and trailing whitespaces (if any)
        fullName = fullName.trim();

        // Remove all special characters except hyphen ("-")
        fullName = fullName.replaceAll("[^a-zA-Z0-9\\s-]", "");

        // Remove consecutive hyphens, if any
        fullName = fullName.replaceAll("-+", "-");

        // Trim leading and trailing hyphens, if any
        fullName = fullName.replaceAll("^-|-$", "");

        // Extract the first letter of the first name and the last name
        String[] names = fullName.split("\\s+");
        String firstName = names[0].substring(0, 1);
        String lastName = names[names.length - 1];
        String username = firstName + lastName;

        List<Staff> usernameList = searchRecords(Staff.class, EntityFields.username, username, EntityFields.createdDate);
        if (usernameList.isEmpty()) {
            AppLogger.info(log, "first time username " + username);
            return username;
        }
        AppLogger.info(log, usernameList.size() + " contains username like " + username);
        log.info("Add increase the last username by 1");
        username = username + ((usernameList.size() + 1)) + "";
        log.info("final username " + username);
        return username;
    }
}
