package com.sabonay.advantageservices.models.estatesetup;

import com.ctrloption.formating.DateTimeUtils;
import com.sabonay.advantageservices.entities.estatesetup.EstateProperty;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Thu 03 Aug, 2023 22:08 pm
 */
public class EstatePropertyInfo extends EstatePropertySuper {

    private static final Logger log = Logger.getLogger(EstatePropertyInfo.class.getName());

    private String block;
    private String estateName;
    private String createdBy;
    private String createdDate;
    private String lastModifiedBy;
    private String lastModifiedDate;
    private String currentOccupantName;
    private String occupied;
    private double currentOccupancyAccruals, currentOccupancyPayments;
    private boolean allocated;
    private String estateId;

    public EstatePropertyInfo() {
        super();
    }

    public EstatePropertyInfo(EstateProperty data) {
        try {
            allocated = true;
            blocked = true;
            occupied = "";
            estateName = data.getEstateBlock().getEstate().getEstateName();
            estateId = data.getEstateBlock().getEstate().getRecordId();
            createdBy = data.getCreatedBy();
            propertyName = data.getPropertyName();
            blockId = data.getEstateBlock().getRecordId();
            block = data.getEstateBlock().getBlockName();
            category = data.getPropertyCategory();
            propertySize = data.getPropertyLandSize();
            propertyName = data.getPropertyName();
            propertyNo = data.getPropertyNumber();
            usage = data.getPropertyUsage();
            recordId = data.getRecordId();
            createdDate = DateTimeUtils.formatDate(data.getCreatedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
            lastModifiedDate = DateTimeUtils.formatDate(data.getLastModifiedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
        } catch (Exception e) {
            AppLogger.error(log, e, "error EstatePropertyInfo constructor");
        }
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getCurrentOccupantName() {
        return currentOccupantName;
    }

    public void setCurrentOccupantName(String currentOccupantName) {
        this.currentOccupantName = currentOccupantName;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }

    public double getCurrentOccupancyAccruals() {
        return currentOccupancyAccruals;
    }

    public void setCurrentOccupancyAccruals(double currentOccupancyAccruals) {
        this.currentOccupancyAccruals = currentOccupancyAccruals;
    }

    public double getCurrentOccupancyPayments() {
        return currentOccupancyPayments;
    }

    public void setCurrentOccupancyPayments(double currentOccupancyPayments) {
        this.currentOccupancyPayments = currentOccupancyPayments;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

}
