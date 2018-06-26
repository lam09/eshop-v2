package com.lam.eshopv2.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by a.lam.tuan on 1. 6. 2018.
 */
@Entity
@Table(name = "PRODUCT_IMAGE")
public class ProductImage implements Serializable{

    private static final long serialVersionUID = 7550745928843183535L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Lob
    @Column(name = "IMAGE", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

    @Lob
    @Column(name = "IMAGE_REDUCED", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image_reduced;

    public byte[] getImage_reduced() {
        return image_reduced;
    }

    public void setImage_reduced(byte[] image_reduced) {
        this.image_reduced = image_reduced;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "TYPE", nullable = true)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "PRODUCT_IMAGE_PROD_FK"))
    private Product product;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
