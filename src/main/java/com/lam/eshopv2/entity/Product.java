package com.lam.eshopv2.entity;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PRODUCTS",
        uniqueConstraints = {@UniqueConstraint(columnNames = "CODE")})
public class Product implements Serializable {

    private static final long serialVersionUID = -1000119078147252957L;


    @Id
    @Column(name = "ID", length = 20, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "CODE", length = 20, nullable = true)
    private String code;

    @Column(name = "NAME", length = 255, nullable = true)
    private String name;

    @Column(name = "PRICE", nullable = true)
    private double price;

    @Column(name = "QUANTITY", nullable = true)
    private Integer quantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = true)
    private Date createDate;

    @Column(name = "DESCRIPTION", length = Integer.MAX_VALUE, nullable = true)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductImage> productImages;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Property> properties;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Variant> variants;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductImage getProfil() {
        return profil;
    }

    public void setProfil(ProductImage profil) {
        this.profil = profil;
    }

    private ProductImage profil;


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


    public Product() {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Set<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ProductImage> productImages) {
        this.productImages = productImages;
    }

}