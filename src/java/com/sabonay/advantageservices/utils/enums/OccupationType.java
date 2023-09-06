/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabonay.advantageservices.utils.enums;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author edwin / Ritchid /Daud
 */
public enum OccupationType {

    LEASEHOLD("LEASEHOLD", "L"),
    RENTAL("RENTAL", "R"),
    NONE("NONE", "N");

    private OccupationType(String label, String initials) {
        this.label = label;
        this.initials = initials;
    }

    public static List<OccupationType> list() {
        List<OccupationType> occupationTypes = new LinkedList<>();
        occupationTypes.add(LEASEHOLD);
        occupationTypes.add(RENTAL);
        return occupationTypes;
    }

    private final String label;
    private final String initials;

    public String getFullString() {
        return getClass().getCanonicalName() + "." + name();
    }

    public String getLabel() {
        return label;
    }

    public String getInitials() {
        return initials;
    }

    @Override
    public String toString() {
        return label;
    }

}
