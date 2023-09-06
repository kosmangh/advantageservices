package com.sabonay.advantageservices.services;

import com.ctrloption.formating.ObjectValue;
import com.ctrloption.jpa2.CrudController;
import com.ctrloption.jpa2.Enviroment;
import com.ctrloption.jpa2.QryBuilder;
import com.ctrloption.utils.MsgFormatter;
import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.entities.estatesetup.Estate;
import com.sabonay.advantageservices.entities.estatesetup.EstateBlock;
import com.sabonay.advantageservices.models.DropdownInfo;
import com.sabonay.advantageservices.restmodels.commons.DropdownResponse;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.utils.AppConstants;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Tue 29 Aug, 2023 05:53 am
 */
@Stateless
public class DropdownServices extends CrudController implements Serializable {

    private static final Logger log = Logger.getLogger(DropdownServices.class.getName());
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    private void init() {
        setEm(em);
        setEnviroment(Enviroment.JAVA_EE);
    }

    public DropdownResponse getEstatesDropdown(String zoneId) {
        DropdownResponse response = new DropdownResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            QryBuilder builder = new QryBuilder(em, Estate.class);
            builder.addReturnField("e." + EntityFields.recordId);
            builder.addReturnField("e." + EntityFields.estateName);
            builder.addStringQryParam(EntityFields._region_zone, zoneId, QryBuilder.ComparismCriteria.EQUAL);
//        builder.addReturnField("e." + EntityFields._branchCode);
            builder.addObjectParam(EntityFields.deleted, false);
            builder.orderByAsc(EntityFields.estateName);

            log.info("getEstateDropdown qry " + builder.getQryInfo());
            List<Object[]> resultList = builder.buildQry().getResultList();
            log.info("total estateDropdown retrieved " + resultList.size());
            List<DropdownInfo> listOfEstates = new ArrayList<>();
            for (Object[] eachObject : resultList) {
                DropdownInfo dropdownInfo = DropdownInfo.builder()
                        .estateId(ObjectValue.getStringValue(eachObject[0]))
                        .estateName(ObjectValue.getStringValue(eachObject[1]))
                        .build();
                listOfEstates.add(dropdownInfo);
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setHeaderResponse(headerResponse);
            response.setDropdownList(listOfEstates);
            return response;
        } catch (Exception e) {
            AppLogger.error(log, e, "getEstateDropdown IOException");
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(AppConstants.ERROR_PROCESSING_REQUEST));
            return response;
        }

    }

    public DropdownResponse getBlocksDropdown(String estateId) {
        DropdownResponse response = new DropdownResponse();
        HeaderResponse headerResponse = new HeaderResponse();
        try {
            QryBuilder builder = new QryBuilder(em, EstateBlock.class);
            builder.addReturnField("e." + EntityFields.recordId);
            builder.addReturnField("e." + EntityFields.blockName);
            builder.addStringQryParam(EntityFields._estate, estateId, QryBuilder.ComparismCriteria.EQUAL);
            builder.addObjectParam(EntityFields.deleted, false);
            builder.orderByAsc(EntityFields.blockName);

            log.info("getBlocksDropdown qry " + builder.getQryInfo());
            List<Object[]> resultList = builder.buildQry().getResultList();
            log.info("total estateDropdown retrieved " + resultList.size());
            List<DropdownInfo> listOfBlocks = new ArrayList<>();
            for (Object[] eachObject : resultList) {
                DropdownInfo dropdownInfo = DropdownInfo.builder()
                        .blockId(ObjectValue.getStringValue(eachObject[0]))
                        .blockName(ObjectValue.getStringValue(eachObject[1]))
                        .build();
                listOfBlocks.add(dropdownInfo);
            }
            headerResponse.setResponseCode(ResponseCodes.SUCCESS);
            headerResponse.setResponseMessage(ResponseCodes.getAppMsg(ResponseCodes.SUCCESS));
            response.setDropdownList(listOfBlocks);
            return response;
        } catch (Exception e) {
            AppLogger.error(log, e, "getBlocksDropdown IOException");
            headerResponse.setResponseCode(ResponseCodes.FAILED);
            headerResponse.setResponseMessage(MsgFormatter.sentenceCase(AppConstants.ERROR_PROCESSING_REQUEST));
            return response;
        }

    }

}
