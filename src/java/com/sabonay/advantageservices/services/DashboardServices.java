package com.sabonay.advantageservices.services;

import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatesetup.Estate;
import com.sabonay.advantageservices.entities.estatesetup.EstateBlock;
import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.entities.occupancy.OccupantProperty;
import com.sabonay.advantageservices.models.EstateDashboardSummary;
import com.sabonay.advantageservices.requestvalidators.HeaderValidator;
import com.sabonay.advantageservices.restmodels.DashboardSummaryResponse;
import com.sabonay.advantageservices.restmodels.commons.GenericSearchRequest;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.utils.AppLogger;
import com.sabonay.advantageservices.utils.AppUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
 * @date Sun 15 Oct, 2023 06:04 am
 */
@Stateless
public class DashboardServices extends CrudController implements Serializable {

    private static final Logger log = Logger.getLogger(DashboardServices.class.getName());

    @Inject
    private BasicServices basicServices;

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        setEm(em);
        setEnviroment(Enviroment.JAVA_EE);
    }

    public DashboardSummaryResponse getDashboardSummary(GenericSearchRequest request) throws IOException {

        DashboardSummaryResponse response = new DashboardSummaryResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
//            AppLogger.printPayload(log, "header validation response before", headerResponse);
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                response.setHeaderResponse(headerResponse);
                return response;
            }
            AppLogger.printPayload(log, "header validation response after", headerResponse);
            //Gets estates of a zone
            List<Estate> listOfEstates = new ArrayList<>();
            QryBuilder builder = new QryBuilder(em, Estate.class);
            builder.addStringQryParam(EntityFields._region_zone, request.getSearchValue(), QryBuilder.ComparismCriteria.EQUAL);
            builder.addObjectParam(EntityFields.deleted, false);
            listOfEstates = builder.buildQry().getResultList();
            if (null == listOfEstates) {
                response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
                return response;
            }

            if (listOfEstates.isEmpty()) {
                response.setEstateSummary(new ArrayList<>());
                headerResponse.setResponseCode(ResponseCodes.SUCCESS);
                headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
                response.setHeaderResponse(headerResponse);
                return response;
            }

            List<EstateProperty> listOfproperties = new ArrayList<>();
            List<EstateBlock> listOfBlocks = new ArrayList<>();

            List<EstateDashboardSummary> estateSummaryList = new ArrayList<>();
            double totalEstateSize = 0.0, totalBlkSize = 0.0, totalPropsSize = 0.0;
            int overAllTotalBlks = 0, overAllTotalProps = 0, totalInactiveProp = 0, totalOccupiedProperties = 0, totalProps = 0;
            int overAllTotalEstateOccupied = 0, overAllTotalBlockedOccupied = 0, overAllTotalOccupiedProperties = 0;

            for (Estate eachEstate : listOfEstates) {
                totalEstateSize += eachEstate.getLandSize();

                List<OccupantProperty> occupiedEstates
                        = getOccupiedPropertiesByEstate(EntityFields._estate_block_property, eachEstate.getRecordId());
                if (!occupiedEstates.isEmpty()) {
                    overAllTotalEstateOccupied += 1;
                }

                listOfBlocks = basicServices.
                        searchRecordsStrict(EstateBlock.class, EntityFields._estate, eachEstate.getRecordId());
                overAllTotalBlks += listOfBlocks.size();

                for (EstateBlock eachBlk : listOfBlocks) {
                    totalBlkSize += eachBlk.getBlockSize();
                    //Sums total occupied blocks
                    int totalOccupied = 0;
                    totalOccupied = (int) occupiedEstates.parallelStream()
                            .filter(eachOccupiedEstate -> eachOccupiedEstate.getEstateProperty().getEstateBlock().getRecordId().toUpperCase().equals(eachBlk.getRecordId().toUpperCase()))
                            .count();
                    if (totalOccupied > 0) {
                        overAllTotalBlockedOccupied += 1;
                    }

                    //Gets each blocks's property
                    listOfproperties = basicServices.
                            searchRecordsStrict(EstateProperty.class, EntityFields._estateBlock, eachBlk.getRecordId());
                    overAllTotalProps += listOfproperties.size();
                    totalProps += listOfproperties.size();

                    for (EstateProperty eachProp : listOfproperties) {
                        totalPropsSize += eachProp.getPropertyLandSize();

                        if (eachProp.isBlocked()) {
                            totalInactiveProp += 1;
                        }
                        totalOccupied = 0;
                        totalOccupied = (int) occupiedEstates.parallelStream()
                                .filter(eachOccupiedEstate -> eachOccupiedEstate.getEstateProperty().getRecordId().toUpperCase().trim().equals(eachProp.getRecordId().toUpperCase().trim()))
                                .count();
                        log.info(" total property occupied for " + eachProp.getPropertyName() + " " + totalOccupied);
                        if (totalOccupied > 0) {
                            totalOccupiedProperties += 1;
                            overAllTotalOccupiedProperties += 1;
//                            break;
                        }
                    }
                }
                log.info(" total estate occupied for " + eachEstate.getEstateName() + " " + totalOccupiedProperties);

                EstateDashboardSummary eds = new EstateDashboardSummary();

                eds.setEstateName(eachEstate.getEstateName());
                eds.setRecordId(eachEstate.getRecordId());
                eds.setBlocks(listOfBlocks.size());
                eds.setProperties(totalProps);

                eds.setAllocatedProperties(totalOccupiedProperties);
                eds.setAvailableProperties(eds.getProperties() - totalOccupiedProperties);
                eds.setInactiveProperties(totalInactiveProp);

                estateSummaryList.add(eds);
                totalOccupiedProperties = 0;
                totalInactiveProp = 0;
                totalProps = 0;
                listOfBlocks.clear();
                listOfproperties.clear();

            }

            //Land size
            response.setTotalEstateSize(totalEstateSize);
            response.setTotalBlockSize(totalBlkSize);
            response.setTotalPropertySize(totalPropsSize);

            response.setOccupiedEstates(overAllTotalEstateOccupied);
            response.setAvailableEstates(listOfEstates.size() - overAllTotalEstateOccupied);

            response.setOccupiedBlocks(overAllTotalBlockedOccupied);
            response.setAvailableBlocks(overAllTotalBlks - overAllTotalBlockedOccupied);
//
            response.setOccupiedProperties(overAllTotalOccupiedProperties);
            response.setAvailableProperties(overAllTotalProps - overAllTotalOccupiedProperties);

            response.setEstateSummary(estateSummaryList);

            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            return response;
        } catch (IOException e) {
            AppLogger.error(log, e, "getEstates IOException");
            response.setHeaderResponse(AppUtils.getErrorHeaderResponse(request.getHeaderRequest()));
            return response;
        }
    }

    public Integer getGeneralCount(Class clazz, String countField, String columnName, String columnValue) {
        QryBuilder builder = new QryBuilder(em, clazz);
        try {
            builder.addReturnField("COUNT (e." + countField + ")");
            builder.addObjectParam(columnName, columnValue);
            builder.addObjectParam(EntityFields.deleted, false);
            builder.addGroupBy(countField);
            log.info(" quering getGeneralCount " + builder.getQryInfo());
            return (Integer) builder.buildQry().getSingleResult();
        } catch (Exception e) {
            return 0;
        }
    }

    public Double getGeneralSum(Class clazz, String sumField) {
        QryBuilder builder = new QryBuilder(em, clazz);
        try {
            builder.addReturnField("SUM (e." + sumField + ")");
            builder.addObjectParam(EntityFields.deleted, false);
            log.info(" quering getGeneralSum " + builder.getQryInfo());
            return (Double) builder.buildQry().getSingleResult();
        } catch (Exception e) {
            AppLogger.error(log, e, "ERROR getGeneralSum");
            return 1.0;
        }
    }

    public Double calculateTotalLandSize() {
        Query query = em.createQuery("SELECT SUM(e.landSize) FROM EntityName e");
        return (Double) query.getSingleResult();
    }

    public List<OccupantProperty> getOccupiedPropertiesByEstate(String searchType, String recordId) {
        Date checkDate = new Date();
        List<OccupantProperty> listOfOccupantProperties = new ArrayList<>();
        try {
            String qry = "SELECT e FROM OccupantProperty e "
                    + "WHERE e." + searchType + " =:searchValue "
                    + "AND e.firstDateOfOccupancy <=:checkDate "
                    + "AND (e.lastDateOfOccupancy IS NULL OR e.lastDateOfOccupancy >=:checkDate) ";
            AppLogger.info(log, " qry fetch getOccupiedPropertiesByEstate " + qry);
            Query query = em.createQuery(qry)
                    .setParameter("searchValue", recordId)
                    .setParameter("checkDate", checkDate, TemporalType.DATE);
            listOfOccupantProperties = query.getResultList();
            if (listOfOccupantProperties.isEmpty()) {
                return Collections.EMPTY_LIST;
            }
            return listOfOccupantProperties;
        } catch (Exception e) {
            AppLogger.error(log, e, "Error processing getOccupiedPropertiesByEstate request");
            return null;
        }
    }

}
