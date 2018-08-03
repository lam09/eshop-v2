package com.lam.eshopv2.nosql.entity;

import java.util.List;

/**
 * Created by a.lam.tuan on 2. 8. 2018.
 */
public class Variant {
    String variantTypeName;
    List<String> value;

    public String getVariantTypeName() {
        return variantTypeName;
    }

    public void setVariantTypeName(String variantTypeName) {
        this.variantTypeName = variantTypeName;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
