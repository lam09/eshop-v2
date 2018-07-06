package com.lam.eshopv2.entity;

/**
 * Created by a.lam.tuan on 23. 5. 2018.
 */

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT",
        uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class Account implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @Column(name = "ID",  nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @Column(name = "FIRSTNAME", length = 255, nullable = true)
    private String firstname;

    @Column(name = "LASTNAME", length = 255, nullable = true)
    private String lastname;
    @Column(name = "ADDRESS", length = 255, nullable = true)
    private String address;
    @Column(name = "CITY", length = 255, nullable = true)
    private String city;
    @Column(name = "EMAIL", length = 255, nullable = true)
    private String email;
    @Column(name = "PHONE", length = 255, nullable = true)
    private String phone;
    @Column(name = "POSTALCODE", length = 255, nullable = true)
    private String postalCode;

    @Column(name = "ENCRYTED_PASSWORD", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "ACTIVE", length = 1, nullable = true)
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ACCOUNT_ROLE",
            joinColumns = @JoinColumn(name = "ACCOUNT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }

    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}