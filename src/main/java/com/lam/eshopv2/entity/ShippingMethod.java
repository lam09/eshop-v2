package com.lam.eshopv2.entity;

import javax.persistence.*;

/**
 * Created by a.lam.tuan on 29. 6. 2018.
 */
@Entity
@Table(name = "shipping_method")
public class ShippingMethod {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "name",nullable = true)
    String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Column(name = "fee")
    double fee;
    public ShippingMethod(){};
    public ShippingMethod(String name, double fee) {
        this.name = name;
        this.fee = fee;
    }
}
