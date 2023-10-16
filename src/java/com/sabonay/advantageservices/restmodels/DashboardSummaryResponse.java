package com.sabonay.advantageservices.restmodels;

import com.sabonay.advantageservices.models.EstateDashboardSummary;
import com.sabonay.advantageservices.restmodels.commons.HeaderResponse;
import java.util.List;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 15 Oct, 2023 05:51 am
 */
public class DashboardSummaryResponse {

    private HeaderResponse headerResponse;
    private Double totalEstateSize = 0.0;
    private Double totalBlockSize = 0.0;
    private Double totalPropertySize = 0.0;
    private int occupiedEstates;
    private int availableEstates;
    private int occupiedBlocks;
    private int availableBlocks;
    private int occupiedProperties;
    private int availableProperties;
    private List<EstateDashboardSummary> estateSummary;
    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">

    public HeaderResponse getHeaderResponse() {
        return headerResponse;
    }

    public void setHeaderResponse(HeaderResponse headerResponse) {
        this.headerResponse = headerResponse;
    }

    public Double getTotalEstateSize() {
        return totalEstateSize;
    }

    public void setTotalEstateSize(Double totalEstateSize) {
        this.totalEstateSize = totalEstateSize;
    }

    public int getOccupiedEstates() {
        return occupiedEstates;
    }

    public void setOccupiedEstates(int occupiedEstates) {
        this.occupiedEstates = occupiedEstates;
    }

    public int getAvailableEstates() {
        return availableEstates;
    }

    public void setAvailableEstates(int availableEstates) {
        this.availableEstates = availableEstates;
    }

    public Double getTotalBlockSize() {
        return totalBlockSize;
    }

    public void setTotalBlockSize(Double totalBlockSize) {
        this.totalBlockSize = totalBlockSize;
    }

    public int getOccupiedBlocks() {
        return occupiedBlocks;
    }

    public void setOccupiedBlocks(int occupiedBlocks) {
        this.occupiedBlocks = occupiedBlocks;
    }

    public int getAvailableBlocks() {
        return availableBlocks;
    }

    public void setAvailableBlocks(int availableBlocks) {
        this.availableBlocks = availableBlocks;
    }

    public Double getTotalPropertySize() {
        return totalPropertySize;
    }

    public void setTotalPropertySize(Double totalPropertySize) {
        this.totalPropertySize = totalPropertySize;
    }

    public int getOccupiedProperties() {
        return occupiedProperties;
    }

    public void setOccupiedProperties(int occupiedProperties) {
        this.occupiedProperties = occupiedProperties;
    }

    public int getAvailableProperties() {
        return availableProperties;
    }

    public void setAvailableProperties(int availableProperties) {
        this.availableProperties = availableProperties;
    }

    public List<EstateDashboardSummary> getEstateSummary() {
        return estateSummary;
    }

    public void setEstateSummary(List<EstateDashboardSummary> estateSummary) {
        this.estateSummary = estateSummary;
    }
//</editor-fold>

}
