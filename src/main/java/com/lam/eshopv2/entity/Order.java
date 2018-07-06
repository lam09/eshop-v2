package com.lam.eshopv2.entity;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "ORDER_DATE", nullable = false)
    private Date orderDate;

    @Column(name = "ORDER_NUM", nullable = false)
    private String orderNum;

    @Column(name = "AMOUNT", nullable = false)
    private double amount;

    @Column(name="NOTE",nullable=true)
    private String note;

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Column(name="STATE",nullable=true)

    private String state;

    @Column(name = "CUSTOMER_FIRSTNAME", length = 255, nullable = false)
    private String customerFirstName;

    @Column(name = "CUSTOMER_LASTNAME", length = 255, nullable = false)
    private String customerLastName;

    @Column(name = "CUSTOMER_ADDRESS", length = 255, nullable = false)
    private String customerAddress;

    @Column(name = "CUSTOMER_CITY", length = 255, nullable = false)
    private String customerCity;

    @Column(name = "CUSTOMER_POSTALCODE", length = 255, nullable = false)
    private String customerPostalCode;

    @Column(name = "CUSTOMER_EMAIL", length = 128, nullable = false)
    private String customerEmail;

    @Column(name = "CUSTOMER_PHONE", length = 128, nullable = false)
    private String customerPhone;

    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = true) //
    private Account account;

    @ManyToOne
    @JoinColumn(name = "SHIPPING_METHOD_ID", nullable = true) //
    private ShippingMethod shippingMethod;

    @ManyToOne
    @JoinColumn(name = "PAYMENT_METHOD_ID", nullable = true) //
    private PaymentMethod paymentMethod;

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonBackReference
    private List<OrderDetail> orderDetails;

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Column(name = "PAID", length = 128, nullable = false)
    private boolean paid=false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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