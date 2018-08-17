package com.lam.eshopv2.nosql.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by a.lam.tuan on 13. 8. 2018.
 */
@Document(collection = "CategoryCollection")
public class CategoryCollection {
    @Id
    @Field(value = "name")
    String name;

    @DBRef
    List<ProductCollection> productCollections;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductCollection> getProductCollections() {
        return productCollections;
    }

    public void setProductCollections(List<ProductCollection> productCollections) {
        this.productCollections = productCollections;
    }
}
