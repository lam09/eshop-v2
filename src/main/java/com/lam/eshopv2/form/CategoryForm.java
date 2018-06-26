package com.lam.eshopv2.form;

import com.lam.eshopv2.entity.Product;

/**
 * Created by a.lam.tuan on 25. 6. 2018.
 */
public class CategoryForm {
    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    Product product;
    String[] names;

}
