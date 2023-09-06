package com.sabonay.advantageservices.models.estatebilling;

import com.ctrloption.formating.DateTimeUtils;
import com.sabonay.advantageservices.entities.estatebilling.PropertyLedger;
import com.sabonay.advantageservices.utils.AppLogger;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Tue 15 Aug, 2023 03:19 am
 */
public class PropertyLedgerInfo extends PropertyLedgerSuper {

    private static final Logger log = Logger.getLogger(PropertyLedgerInfo.class.getName());

    private String regionName;
    private String occupantName;
    private String propertyName;
    private String propertyNo;
    private String propertyUsage;
    private String createdDate;
    private String createdBy;
    private String lastModifiedDate;
    private String lastModifiedBy;
    private String institutionalDisplayName;
    private String occupationType;
    public PropertyLedgerInfo() {
        super();
    }

    public PropertyLedgerInfo(PropertyLedger data) {
        try {
            amountInvolved = data.getAmountInvolved();

            dateOfRecordEntry = data.getDateOfRecordEntry();
            dateOfRecordTransaction = data.getDateOfRecordTransaction();
            debitCreditEntry = data.getDebitCreditEntry();
            receiptNumberIssued = data.getReceiptNumberIssued();
            description = data.getDescription();
            ledgerYear = data.getLedgerYear();
            mediumOfPayment = data.getMediumOfPayment();
            mediumOfPaymentNumber = data.getMediumOfPaymentNumber();
            occupantId = data.getOccupant().getRecordId();
            occupantName = data.getOccupant().getOccupantName();
            occupationType = data.getOccupant().getOccupantType();
            institutionalDisplayName = data.getOccupant().getInstitutionalDisplayName();
            payeeContact = data.getPayeeContact();
            payeeName = data.getPayeeName();
            paymentFor = data.getPaymentFor();
            paymentType = data.getPaymentType();
            propertyId = data.getEstateProperty().getRecordId();
            propertyName = data.getEstateProperty().getPropertyName();
            propertyNo = data.getEstateProperty().getPropertyNumber();
            propertyUsage = data.getEstateProperty().getPropertyUsage();
            createdBy = data.getCreatedBy();
            createdDate = DateTimeUtils.formatDate(data.getCreatedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
            lastModifiedBy = data.getLastModifiedBy();
            lastModifiedDate = DateTimeUtils.formatDate(data.getLastModifiedDate(), DateTimeUtils.PATTERN_DATE_AND_TIME);
        } catch (Exception e) {
            AppLogger.error(log, e, "PropertyLedgerInfo exception error");
        }
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getOccupantName() {
        return occupantName;
    }

    public void setOccupantName(String occupantName) {
        this.occupantName = occupantName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyNo() {
        return propertyNo;
    }

    public void setPropertyNo(String propertyNo) {
        this.propertyNo = propertyNo;
    }

    public String getPropertyUsage() {
        return propertyUsage;
    }

    public void setPropertyUsage(String propertyUsage) {
        this.propertyUsage = propertyUsage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getInstitutionalDisplayName() {
        return institutionalDisplayName;
    }

    public void setInstitutionalDisplayName(String institutionalDisplayName) {
        this.institutionalDisplayName = institutionalDisplayName;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

}
