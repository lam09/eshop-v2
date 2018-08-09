package com.lam.eshopv2.nosql.entity;

import org.bson.types.Binary;

/**
 * Created by a.lam.tuan on 2. 8. 2018.
 */
public class ProductImage {
    String imageSize;
    Binary image;
    String id;

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }
}
