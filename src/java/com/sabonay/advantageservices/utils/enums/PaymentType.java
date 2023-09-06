package com.sabonay.advantageservices.utils.enums;

public enum PaymentType {

    GROUND_RENT("GROUND_RENT", "GR", "year"),
    HOUSE_RENT("HOUSE_RENT", "PR", "month");

    private PaymentType(String label, String initials, String paymentPeriod) {
        this.label = label;
        this.initials = initials;
        this.paymentPeriod = paymentPeriod;
    }

    private final String label;
    private final String initials;
    private final String paymentPeriod;

    public String getFullString() {
        return getClass().getCanonicalName() + "." + name();
    }

    public String getInitials() {
        return initials;
    }

    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

}
