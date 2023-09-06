package com.sabonay.advantageservices.entities.estatesetup;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sat 29 Jul, 2023 22:07 pm
 */
import com.ctrloption.entities.super_classes.EntityCrud;
import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "estate_block")
@Cacheable(false)
public class EstateBlock extends EntityCrud implements Serializable {

    @Column(name = "block_name")
    private String blockName;

    @Column(name = "block_size")
    private double blockSize;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "estate")
    private Estate estate;

    @Transient
    private int totalProperties, totalProps_allocated;

    @Transient
    private double blockSizeLeft;

//    public int getTotalProps_allocated() {
//        totalProps_allocated = 0;
//        if (totalProperties > 0) {
//            getEstatePropertyList().stream().filter((props) -> (props.isAllocated())).forEach((_item) -> {
//                totalProps_allocated++;
//            });
//        }
//        return totalProps_allocated;
//    }

//    public List<EstateProperty> getEstatePropertyList() {
//        if (estatePropertyList != null) {
//            List<EstateProperty> props = new ArrayList<>();
//            estatePropertyList.stream().filter((estProp) -> (!estProp.getDeleted())).forEachOrdered(props::add);
//            StringValueComparator.sort(props);
//            return props;
//        }
//        return estatePropertyList;
//    }

//    public double getBlkSizeLeft() {
//        blockSizeLeft = 0.0D;
//        if (blockSize > 0) {
//            double allPropSize = 0.0D;
//            if (getEstatePropertyList() != null) {
//                allPropSize = getEstatePropertyList().stream().map((prop) -> prop.getPropertyLandSize()).reduce(allPropSize, (a, b) -> a + b);
//            }
//            blockSizeLeft = blockSize - allPropSize;
//        }
//        return blockSizeLeft;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recordId != null ? recordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estate)) {
            return false;
        }
        EstateBlock other = (EstateBlock) object;
        return !((this.recordId == null && other.recordId != null) || (this.recordId != null && !this.recordId.equals(other.recordId)));
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public double getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(double blockSize) {
        this.blockSize = blockSize;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Estate getEstate() {
        return estate;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public int getTotalProperties() {
        return totalProperties;
    }

    public void setTotalProperties(int totalProperties) {
        this.totalProperties = totalProperties;
    }

    public int getTotalProps_allocated() {
        return totalProps_allocated;
    }

    public void setTotalProps_allocated(int totalProps_allocated) {
        this.totalProps_allocated = totalProps_allocated;
    }

    public double getBlockSizeLeft() {
        return blockSizeLeft;
    }

    public void setBlockSizeLeft(double blockSizeLeft) {
        this.blockSizeLeft = blockSizeLeft;
    }
}
