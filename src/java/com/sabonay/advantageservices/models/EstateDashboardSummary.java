package com.sabonay.advantageservices.models;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 15 Oct, 2023 05:50 am
 */
public class EstateDashboardSummary {

    private String recordId;
    private String estateName;
    private int blocks = 0;
    private int properties = 0;
    private int inactiveProperties = 0;
    private int allocatedProperties = 0;
    private int availableProperties = 0;

//<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getEstateName() {
        return estateName;
    }

    public void setEstateName(String estateName) {
        this.estateName = estateName;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getProperties() {
        return properties;
    }

    public void setProperties(int properties) {
        this.properties = properties;
    }

    public int getInactiveProperties() {
        return inactiveProperties;
    }

    public void setInactiveProperties(int inactiveProperties) {
        this.inactiveProperties = inactiveProperties;
    }

    public int getAllocatedProperties() {
        return allocatedProperties;
    }

    public void setAllocatedProperties(int allocatedProperties) {
        this.allocatedProperties = allocatedProperties;
    }

    public int getAvailableProperties() {
        return availableProperties;
    }

    public void setAvailableProperties(int availableProperties) {
        this.availableProperties = availableProperties;
    }
//</editor-fold>

}
