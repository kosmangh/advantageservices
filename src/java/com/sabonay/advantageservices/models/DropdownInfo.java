package com.sabonay.advantageservices.models;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Tue 29 Aug, 2023 06:10 am
 */
public class DropdownInfo {

    private final String estateId;
    private final String estateName;
    private final String blockId;
    private final String blockName;
    private final String propertyId;
    private final String propertyName;

    private DropdownInfo(Builder builder) {
        this.estateId = builder.estateId;
        this.estateName = builder.estateName;
        this.blockId = builder.blockId;
        this.blockName = builder.blockName;
        this.propertyId = builder.propertyId;
        this.propertyName = builder.propertyName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getEstateId() {
        return estateId;
    }

    public String getEstateName() {
        return estateName;
    }

    public String getBlockId() {
        return blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public static class Builder {

        private String estateId;
        private String estateName;
        private String blockId;
        private String blockName;
        private String propertyId;
        private String propertyName;

        private Builder() {
        }

        public Builder estateId(String estateId) {
            this.estateId = estateId;
            return this;
        }

        public Builder estateName(String estateName) {
            this.estateName = estateName;
            return this;
        }

        public Builder blockId(String blockId) {
            this.blockId = blockId;
            return this;
        }

        public Builder blockName(String blockName) {
            this.blockName = blockName;
            return this;
        }

        public Builder propertyId(String propertyId) {
            this.propertyId = propertyId;
            return this;
        }

        public Builder propertyName(String propertyName) {
            this.propertyName = propertyName;
            return this;
        }

        public DropdownInfo build() {
            return new DropdownInfo(this);
        }
    }
}
