package com.lam.eshopv2.socials.facebook;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by a.lam.tuan on 27. 7. 2018.
 */
@Entity
@Table(name = "FacebookUser")
public class FacebookUser {
    @Id
    String uddi;

    @Column(name = "id")
    String id;

    @Column(name = "userInfo")
    String userInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUddi() {
        return uddi;
    }

    public void setUddi(String uddi) {
        this.uddi = uddi;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
