package com.sabonay.advantageservices.requestvalidators;

import com.sabonay.advantageservices.ResponseCodes;
import com.sabonay.advantageservices.entities.estatesetup.EstateBlock;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import com.sabonay.advantageservices.restmodels.estatesetup.EstateBlockRequest;
import com.sabonay.advantageservices.utils.AppLogger;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Tue 01 Aug, 2023 08:08 am
 */
public class EstateBlockValidator {

    private static final Logger log = Logger.getLogger(EstateBlockValidator.class.getName());

    public static EstateBlock validateEstateBlockCommonFields(EstateBlockRequest request) throws IOException {
        HeaderResponse headerResponse = new HeaderResponse();
        String msg = "";
        EstateBlock estateBlock = new EstateBlock();
        try {
            headerResponse = HeaderValidator.validateHeaderRequest(request.getHeaderRequest());
            if (!headerResponse.getResponseCode().equalsIgnoreCase(ResponseCodes.SUCCESS)) {
                msg = headerResponse.getResponseMessage();
                estateBlock.setResponseCode(ResponseCodes.FAILED);
                estateBlock.setResponseMsg(msg);
                return estateBlock;
            }
            if (null == request.getRecordId() || StringUtils.isEmpty(request.getRecordId())) {
                msg = ResponseCodes.ESTATE_BLOCK_ID_REQUIRED;
                estateBlock.setResponseCode(ResponseCodes.FAILED);
                estateBlock.setResponseMsg(msg);
                return estateBlock;
            }
            if (null == request.getBlock() || StringUtils.isEmpty(request.getBlock())) {
                msg = ResponseCodes.BLOCK_NAME_REQUIRED;
                estateBlock.setResponseCode(ResponseCodes.FAILED);
                estateBlock.setResponseMsg(msg);
                return estateBlock;
            }
            if (null == request.getEstateId() || StringUtils.isEmpty(request.getEstateId())) {
                msg = ResponseCodes.ESTATE_NAME_REQUIRED;
                estateBlock.setResponseCode(ResponseCodes.FAILED);
                estateBlock.setResponseMsg(msg);
                return estateBlock;
            }
            if (null == request.getBlockSize() || request.getBlockSize() <= 0.) {
                msg = ResponseCodes.LAND_SIZE_REQUIRED;
                estateBlock.setResponseCode(ResponseCodes.FAILED);
                estateBlock.setResponseMsg(msg);
                return estateBlock;
            }
            log.info("validateEstateBlockCommonFields passed validation;set commons fields to EstateBlock entity");
            estateBlock.setBlockName(request.getBlock().toUpperCase());
            estateBlock.setBlockSize(request.getBlockSize());
            estateBlock.setRemarks(request.getRemarks());
            AppLogger.printPayload(log, "After validation ", estateBlock);
            msg = ResponseCodes.SUCCESS;
            estateBlock.setResponseCode(ResponseCodes.SUCCESS);
            estateBlock.setResponseMsg(msg);
            return estateBlock;
        } catch (IOException e) {
            AppLogger.error(log, e, "validateRolerUsers exception error");
            msg = ResponseCodes.getAppMsg(ResponseCodes.ERROR_SERVICING_REQUEST);
            estateBlock.setResponseCode(ResponseCodes.FAILED);
            estateBlock.setResponseMsg(msg);
            return estateBlock;
        }
    }
}
