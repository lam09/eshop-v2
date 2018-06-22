package com.lam.eshopv2.model;

import com.lam.eshopv2.entity.Product;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */
public class CartLineInfo {

    private Product product;
    private int quantity;

    public CartLineInfo() {
        this.quantity = 0;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return this.product.getPrice() * this.quantity;
    }

}