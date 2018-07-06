package com.lam.eshopv2.form;

import com.lam.eshopv2.entity.PaymentMethod;
import com.lam.eshopv2.entity.ShippingMethod;
import com.lam.eshopv2.model.ShippingAddressInfo;

import java.util.List;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */
public class ShippingAddressForm {

    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String phone;
    private String city;
    private String postalCode;
    private boolean valid;

    private String shippingMethod;
    private String paymentMethod;

    public ShippingAddressForm() {

    }

    public ShippingAddressForm(ShippingAddressInfo shippingAddressInfoInfo) {
        if (shippingAddressInfoInfo != null) {
            this.firstname = shippingAddressInfoInfo.getFirstname();
            this.lastname= shippingAddressInfoInfo.getLastname();
            this.address = shippingAddressInfoInfo.getAddress();
            this.email = shippingAddressInfoInfo.getEmail();
            this.phone = shippingAddressInfoInfo.getPhone();
            this.city=shippingAddressInfoInfo.getCity();
            this.postalCode=shippingAddressInfoInfo.getPostalCode();
        }
    }


    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


}