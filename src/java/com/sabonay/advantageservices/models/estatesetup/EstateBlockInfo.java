package com.sabonay.advantageservices.models.estatesetup;

import com.ctrloption.formating.DateTimeUtils;
import com.sabonay.advantageservices.entities.estatesetup.EstateBlock;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Tue 01 Aug, 2023 08:01 am
 */
public class EstateBlockInfo extends EstateBlockSuper {

    private static final Logger log = Logger.getLogger(EstateBlockInfo.class.getName());

    private int totalProperties, totalPropsAllocated;
    private double blockSizeLeft;
    private String estateName;
    private String createdDate;
    private String lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;

    public EstateBlockInfo() {
        super();
    }

    public EstateBlockInfo(EstateBlock data) {
        try {
            block = data.getBlockName();
            blockSize = data.getBlockSize();
            blockSizeLeft = 0;

            estateId = data.getEstate().getRecordId();
            estateName = data.getEstate().getEstateName();
            recordId = data.getRecordId();
            remarks = data.getRemarks();
            totalProperties = 0;
            totalPropsAllocated = 0;
            createdBy = data.getCreatedBy();
            createdDate = DateTimeUtils.formatDate(data.getCreatedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
            lastModifiedBy = data.getLastModifiedBy();
            lastModifiedDate = DateTimeUtils.formatDate(data.getLastModifiedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
        } catch (Exception e) {
            AppLogger.error(log, e, "error EstateBlockInfo constructor");
        }

    }

    public int getTotalProperties() {
        return totalProperties;
    }

    public void setTotalProperties(int totalProperties) {
        this.totalProperties = totalProperties;
    }

    public int getTotalPropsAllocated() {
        return totalPropsAllocated;
    }

    public void setTotalPropsAllocated(int totalPropsAllocated) {
        this.totalPropsAllocated = totalPropsAllocated;
    }

    public double getBlockSizeLeft() {
        return blockSizeLeft;
    }

    public void setBlockSizeLeft(double blockSizeLeft) {
        this.blockSizeLeft = blockSizeLeft;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public Double getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(Double blockSize) {
        this.blockSize = blockSize;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }
}
