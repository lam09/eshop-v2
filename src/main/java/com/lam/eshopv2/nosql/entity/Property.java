package com.lam.eshopv2.nosql.entity;

/**
 * Created by a.lam.tuan on 2. 8. 2018.
 */
public class Property {
    String propertyName;
    String value;


    public Property() {
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
