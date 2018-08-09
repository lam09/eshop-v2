package com.lam.eshopv2.nosql.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * Created by a.lam.tuan on 2. 8. 2018.
 */
@Document(collection = "products")
public class ProductCollection {

    @Id
    private Integer id;

    @Indexed(unique = true)
    @Field(value = "Code")
    private String code;

    @Field(value = "Name")
    private String name;

    @Field(value = "Price")
    private double price;

    @Field(value = "Quantity")
    private Integer quantity;

    @Field(value = "Create_Date")
    private Date createDate;

    @Field(value = "Description")
    private String description;

    @Field(value = "images")
    private List<ProductImage> productImages;

    @Field(value="properties")
    private List<Property> properties;

    @Field(value = "variants")
    private List<Variant> variants;

    public ProductCollection() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }
}
