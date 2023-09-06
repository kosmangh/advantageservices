package com.sabonay.advantageservices.utils.enums;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Daud
 */
public enum OccupantType {

    LESSEE("LESSEE", "L"),
    TENANT("TENANT", "T");

    private final String label;
    private final String initials;

    private OccupantType(String label, String initials) {
        this.label = label;
        this.initials = initials;
    }

    public static List<OccupantType> list() {
        List<OccupantType> occupantTypes = new LinkedList<>();
        occupantTypes.add(LESSEE);
        occupantTypes.add(TENANT);
        return occupantTypes;
    }

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
