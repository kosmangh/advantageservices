package com.sabonay.advantageservices.models.estatesetup;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Tue 01 Aug, 2023 07:54 am
 */
public class EstateBlockSuper {

    protected String recordId;
    protected String block;
    protected Double blockSize;
    protected String remarks;
    protected String estateId;

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
