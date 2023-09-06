/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sabonay.advantageservices.utils.enums;

import java.io.Serializable;

/**
 *
 * @author crash
 */
public class SMSContact implements Serializable {

    private String estateProperty;
    private String occupantContactNumber;
    private Object[] result;

    public SMSContact() {
    }

    public SMSContact(Object[] result) {
        this.result = result;
        if (result != null) {
            estateProperty = (String) result[0];
            occupantContactNumber = (String) result[1];
        }
    }

    public String getEstateProperty() {
        return estateProperty;
    }

    public void setEstateProperty(String estateProperty) {
        this.estateProperty = estateProperty;
    }

    public String getOccupantContactNumber() {
        return occupantContactNumber;
    }

    public void setOccupantContactNumber(String occupantContactNumber) {
        this.occupantContactNumber = occupantContactNumber;
    }

    @Override
    public String toString() {
        return "SMSContact{" + "estateProperty=" + estateProperty + ", occupantContactNumber=" + occupantContactNumber + '}';
    }

}
