package com.sabonay.advantageservices.models.estatesetup;

import com.ctrloption.formating.DateTimeUtils;
import com.sabonay.advantageservices.entities.estatesetup.Estate;
import com.sabonay.advantageservices.entities.estatesetup.EstateBlock;
import com.sabonay.advantageservices.utils.AppLogger;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 30 Jul, 2023 14:04 pm
 */
public class EstateInfo extends EstateSuper {

    private static final Logger log = Logger.getLogger(EstateInfo.class.getName());

    private String regionName;
    private double landsizeleft;
    private String durationLeft, leased;
    private int totalBlocks, totalProps_alloc;
    private int totalProperty, inactiveProps;
    private String testDate;
    private String createdDate;
    private String lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;

    public EstateInfo() {
        super();
    }

    public EstateInfo(Estate data, List<EstateBlock> estateBlockList) {
        try {
            durationLeft = data.getDurationLeft();
            totalBlocks = estateBlockList.size();
            totalProperty = 0;
            totalProps_alloc = 0;
            inactiveProps = 1;
//            if (!estateBlockList.isEmpty()) {
//                double blkSize = 0.0D;
//                for (EstateBlock blk : estateBlockList) {
//                    blkSize += blk.getBlkSizeLeft();
//                }
//                landsizeleft = landSize - blkSize;
//            }
            landsizeleft = 1;
            leased = "";
            recordId = data.getRecordId();
            regionId = data.getRegion().getRecordId();
            regionName = data.getRegion().getRegionName();
            additionalInfo = data.getAddDesc();
            estateClass = data.getEstateClass();
            estateLocation = data.getEstateLocation();
            estateName = data.getEstateName();
            landSize = data.getLandSize();
            leaseStartDate = data.getDateInitialized();
            leaseEndDate = data.getExpirationdate();
            createdBy = data.getCreatedBy();
            lastModifiedBy = data.getLastModifiedBy();
            createdDate = DateTimeUtils.formatDate(data.getCreatedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
            lastModifiedDate = DateTimeUtils.formatDate(data.getLastModifiedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
        } catch (Exception e) {
            AppLogger.error(log, e, "Estate info constructor error");
        }
    }

    public EstateInfo(Estate data) {
        try {
            durationLeft = data.getDurationLeft();

            inactiveProps = 1;
            landsizeleft = 1;
            leased = "";
            recordId = data.getRecordId();
            regionId = data.getRegion().getRecordId();
            regionName = data.getRegion().getRegionName();
            totalBlocks = 0;
            totalProperty = 0;
            totalProps_alloc = 0;
            additionalInfo = data.getAddDesc();
            estateClass = data.getEstateClass();
            estateLocation = data.getEstateLocation();
            estateName = data.getEstateName();
            landSize = data.getLandSize();
            if (data.getExpirationdate() != null) {
                testDate = DateTimeUtils.formatDate(data.getExpirationdate(), DateTimeUtils.SIMPLE_PATTERN);
            } else {
                testDate = "";
            }
//            leaseEndDate = data.getExpirationdate();
//            leaseStartDate = data.getDateInitialized();
            leaseStartDate = DateTimeUtils.formatDateTwo(data.getDateInitialized(), DateTimeUtils.SIMPLE_PATTERN);
            leaseEndDate = DateTimeUtils.formatDateTwo(data.getExpirationdate(), DateTimeUtils.SIMPLE_PATTERN);
            createdBy = data.getCreatedBy();
            lastModifiedBy = data.getLastModifiedBy();
            createdDate = DateTimeUtils.formatDate(data.getCreatedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
            lastModifiedDate = DateTimeUtils.formatDate(data.getLastModifiedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
        } catch (Exception e) {
            AppLogger.error(log, e, "Estate info constructor error");
        }
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public double getLandsizeleft() {
        return landsizeleft;
    }

    public void setLandsizeleft(double landsizeleft) {
        this.landsizeleft = landsizeleft;
    }

    public String getDurationLeft() {
        return durationLeft;
    }

    public void setDurationLeft(String durationLeft) {
        this.durationLeft = durationLeft;
    }

    public String getLeased() {
        return leased;
    }

    public void setLeased(String leased) {
        this.leased = leased;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public int getTotalBlocks() {
        return totalBlocks;
    }

    public void setTotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }

    public int getTotalProps_alloc() {
        return totalProps_alloc;
    }

    public void setTotalProps_alloc(int totalProps_alloc) {
        this.totalProps_alloc = totalProps_alloc;
    }

    public int getTotalProperty() {
        return totalProperty;
    }

    public void setTotalProperty(int totalProperty) {
        this.totalProperty = totalProperty;
    }

    public int getInactiveProps() {
        return inactiveProps;
    }

    public void setInactiveProps(int inactiveProps) {
        this.inactiveProps = inactiveProps;
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

}
