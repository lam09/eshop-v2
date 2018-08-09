package com.lam.eshopv2.socials.google;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by a.lam.tuan on 30. 7. 2018.
 */
@Entity
@Table(name = "Google_user")
public class Google_user {
    @Id
    String uddi;


    String email;
    String info;

    public Google_user() {
    }

    public String getUddi() {
        return uddi;
    }

    public void setUddi(String uddi) {
        this.uddi = uddi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
