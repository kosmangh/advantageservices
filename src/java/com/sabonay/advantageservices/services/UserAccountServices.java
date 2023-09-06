package com.sabonay.advantageservices.services;

import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.ctrloption.models.DateRange;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.useraccounts.AuditLog;
import com.sabonay.advantageservices.models.AuditLogInfo;
import com.sabonay.advantageservices.restmodels.auth.AuditLogListRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.Serializable;
import java.util.ArrayList;
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
public class UserAccountServices extends CrudController implements Serializable {

    @PersistenceContext
    private EntityManager em;
    private static final Logger log = Logger.getLogger(UserAccountServices.class.getName());

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

    public List<AuditLogInfo> fetchAuditLogs(AuditLogListRequest request) {
        try {
            AppLogger.printPayload(log, "final request", request);
            QryBuilder builder = new QryBuilder(em, AuditLog.class);
            DateRange dateRange = new DateRange();
            dateRange.setToDate(request.getEndDate());
            dateRange.setFromDate(request.getStartDate());
            if (request.getSearchBy().equalsIgnoreCase("username")) {
                builder.addStringQryParam(request.getSearchBy(), request.getSearchValue(), QryBuilder.ComparismCriteria.LIKE);
            }
            builder.addDateRange(dateRange, EntityFields.createdDate);
            builder.orderByDesc(EntityFields.createdDate);
            List<AuditLog> listOfAudits = builder.buildQry().getResultList();
            log.info(" querying fetchAuditLogs " + builder.getQryInfo());
            if (listOfAudits.isEmpty()) {
                log.info("No payment data found");
                return Collections.EMPTY_LIST;
            }
            List<AuditLogInfo> dataList = new ArrayList();
            for (AuditLog eachPayment : listOfAudits) {
                dataList.add(new AuditLogInfo(eachPayment));
            }
            return dataList;
        } catch (Exception e) {
            return null;
        }
    }
}
