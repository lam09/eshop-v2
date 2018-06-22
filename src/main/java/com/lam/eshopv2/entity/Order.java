package com.lam.eshopv2.entity;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS", //
        uniqueConstraints = { @UniqueConstraint(columnNames = "ORDER_NUM") })
public class Order implements Serializable {

    private static final long serialVersionUID = -2576670215015463100L;

    @Id
    @Column(name = "ID", length = 50)
    private String id;

    @Column(name = "ORDER_DATE", nullable = false)
    private Date orderDate;

    @Column(name = "ORDER_NUM", nullable = false)
    private int orderNum;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;

    @Column(name="NOTE",nullable=true)
    private String note;

    @Column(name="STATE",nullable=true)
    private String state;

    @Column(name = "CUSTOMER_NAME", length = 255, nullable = false)
    private String customerName;

    @Column(name = "CUSTOMER_ADDRESS", length = 255, nullable = false)
    private String customerAddress;

    @Column(name = "CUSTOMER_EMAIL", length = 128, nullable = false)
    private String customerEmail;

    @Column(name = "CUSTOMER_PHONE", length = 128, nullable = false)
    private String customerPhone;

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}