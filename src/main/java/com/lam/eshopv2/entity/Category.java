package com.lam.eshopv2.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by a.lam.tuan on 29. 5. 2018.
 */
@Entity
@Table(name = "CATEGORIES" )
public class Category implements Serializable {

    @Id
    @Column(name = "NAME", length = 255, nullable = false)
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
