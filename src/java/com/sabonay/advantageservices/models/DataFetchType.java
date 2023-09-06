package com.sabonay.advantageservices.models;

import com.sabonay.advantageservices.entities.EntityFields;
import com.sabonay.advantageservices.restmodels.estatebilling.GroundRentBillRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.RentalBackBillRequest;
import com.sabonay.advantageservices.restmodels.estatebilling.RentalBillRequest;
import com.sabonay.advantageservices.utils.AppConstants;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 20 Aug, 2023 17:16 pm
 */
public class DataFetchType {

    private static final Logger log = Logger.getLogger(DataFetchType.class.getName());

    private String billingType;
    private String estateId;
    private String blockId;
    private String propertyId;
    private String searchValue;
    private String occupationType;
    private String billTypeQuery;

    public DataFetchType() {
    }

    public DataFetchType(GroundRentBillRequest data) {
        try {
            if (data.getBillingType().equals(AppConstants.ESTATE)) {
                searchValue = data.getEstateId();
                billTypeQuery = EntityFields._estate_block_property;
            }
            if (data.getBillingType().equals(AppConstants.BLOCK)) {
                searchValue = data.getBlockId();
                billTypeQuery = EntityFields._block_property;
            }
            if (data.getBillingType().equals(AppConstants.PROPERTY)) {
                searchValue = data.getPropertyId();
                billTypeQuery = EntityFields._property;
            }
            this.billingType = data.getBillingType();
            this.estateId = data.getEstateId();
            this.blockId = data.getBlockId();
            this.propertyId = data.getPropertyId();
            this.occupationType = "";

        } catch (Exception e) {
            AppLogger.error(log, e, "error getting values for GroundRentBillRequest");
        }
    }

    public DataFetchType(RentalBillRequest data) {
        try {
            if (data.getBillingType().equals(AppConstants.ESTATE)) {
                searchValue = data.getEstateId();
                billTypeQuery = EntityFields._estate_block_property;
            }
            if (data.getBillingType().equals(AppConstants.BLOCK)) {
                searchValue = data.getBlockId();
                billTypeQuery = EntityFields._block_property;
            }
            if (data.getBillingType().equals(AppConstants.PROPERTY)) {
                searchValue = data.getPropertyId();
                billTypeQuery = EntityFields._property;
            }
            this.billingType = data.getBillingType();
            this.estateId = data.getEstateId();
            this.blockId = data.getBlockId();
            this.propertyId = data.getPropertyId();
            this.occupationType = "";

        } catch (Exception e) {
            AppLogger.error(log, e, "error getting values for RentalBillRequest");
        }
    }

    public DataFetchType(RentalBackBillRequest data) {
        try {
            if (data.getBillingType().equals(AppConstants.ESTATE)) {
                searchValue = data.getEstateId();
                billTypeQuery = EntityFields._estate_block_property;
            }
            if (data.getBillingType().equals(AppConstants.BLOCK)) {
                searchValue = data.getBlockId();
                billTypeQuery = EntityFields._block_property;
            }
            if (data.getBillingType().equals(AppConstants.PROPERTY)) {
                searchValue = data.getPropertyId();
                billTypeQuery = EntityFields._property;
            }
            this.billingType = data.getBillingType();
            this.estateId = data.getEstateId();
            this.blockId = data.getBlockId();
            this.propertyId = data.getPropertyId();
            this.occupationType = "";
        } catch (Exception e) {
            AppLogger.error(log, e, "error getting values for RentalBackBillRequest");
        }
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public String getBillTypeQuery() {
        return billTypeQuery;
    }

    public void setBillTypeQuery(String billTypeQuery) {
        this.billTypeQuery = billTypeQuery;
    }

}
