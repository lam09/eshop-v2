package com.lam.eshopv2.model;


import com.lam.eshopv2.entity.Account;
import com.lam.eshopv2.form.ShippingAddressForm;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */
public class ShippingAddressInfo {

    private String firstname;
    private String lasttname;
    private String address;
    private String email;
    private String phone;
    private String city;

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

    private String postalCode;


    private boolean valid;

    public ShippingAddressInfo() {

    }

    public ShippingAddressInfo(Account account) {
        this.firstname = account.getFirstname();
        this.lasttname=account.getLastname();
        this.address = account.getAddress();
        this.email = account.getEmail();
        this.phone = account.getPhone();
        this.city=account.getCity();
        this.postalCode=account.getPostalCode();
    }

    public ShippingAddressInfo(ShippingAddressForm shippingAddressForm) {
        this.firstname = shippingAddressForm.getFirstname();
        this.lasttname= shippingAddressForm.getLastname();
        this.address = shippingAddressForm.getAddress();
        this.email = shippingAddressForm.getEmail();
        this.phone = shippingAddressForm.getPhone();
        this.city= shippingAddressForm.getCity();
        this.postalCode= shippingAddressForm.getPostalCode();
        this.valid = shippingAddressForm.isValid();
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

    public String getLasttname() {
        return lasttname;
    }

    public void setLasttname(String lasttname) {
        this.lasttname = lasttname;
    }

}