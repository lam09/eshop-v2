package com.lam.eshopv2.entity;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ORDER_DETAILS")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 7550745928843183535L;

    @Id
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "ORDER_DETAIL_ORD_FK"))
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, //
            foreignKey = @ForeignKey(name = "ORDER_DETAIL_PROD_FK"))
    private Product product;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "Amount", nullable = false)
    private double amount;

    public OrderDetail(){};

    public OrderDetail(Order order, Product product, int quantity, double price, double amount) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public void setQuantity(int quanity) {
        this.quantity = quanity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}