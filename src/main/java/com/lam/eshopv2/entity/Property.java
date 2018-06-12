package com.lam.eshopv2.entity;

import javax.persistence.*;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
@Entity
@Table(name = "PROPERTY")
public class Property {
    @Id
    @Column(name = "ID",nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name = "value")
    private String value;

    public Property() {
    }

    public Property(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
