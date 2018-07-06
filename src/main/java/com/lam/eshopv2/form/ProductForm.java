package com.lam.eshopv2.form;

import com.lam.eshopv2.entity.Product;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by a.lam.tuan on 6. 7. 2018.
 */
public class ProductForm {

    private String code;
    private String name;
    private double price;
    private Integer quantity;
    private String description;

    public ProductForm(){}

    public Product toProduct(){
        Product product=new Product();
        product.setName(this.name);
        product.setCode(this.code);
        product.setPrice(this.price);
        product.setQuantity(this.quantity);
        product.setDescription(this.description);
        return product;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
