/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabonay.advantageservices.utils.enums;

import com.ctrloption.api.EnumCommon;

/**
 *
 * @author Edwin / Ritchid / Daud
 */
public enum PropertyUsage implements EnumCommon {
    COMMERCIAL("COMMERCIAL", "Commercial", "COM"),
    INSTITUTIONAL("INSTITUTIONAL", "Institutional", "INS"),
    MIXED_USE("MIXED_USE", "Mixed Use", "MIX"),
    RESIDENTIAL("RESIDENTIAL", "Residential", "RES"),
    ALL("ALL", "All", "ALL");

    private final String label;
    private final String initials;
    private final String usageName;

    PropertyUsage(String usageCode, String usageInitials, String usageName) {
        this.label = usageCode;
        this.initials = usageInitials;
        this.usageName = usageName;
    }

    public String getInitials() {
        return initials;
    }

    public String getLabel() {
        return label;
    }

    public String getUsageName() {
        return usageName;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public String getFullString() {
        return label;
    }

}
