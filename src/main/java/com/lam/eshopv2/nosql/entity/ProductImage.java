package com.lam.eshopv2.nosql.entity;

import org.bson.types.Binary;

/**
 * Created by a.lam.tuan on 2. 8. 2018.
 */
public class ProductImage {
    String imageSize;
    Binary image;
    Binary image10;
    Binary image30;
    Binary image50;

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

    public Binary getImage10() {
        return image10;
    }

    public void setImage10(Binary image10) {
        this.image10 = image10;
    }

    public Binary getImage30() {
        return image30;
    }

    public void setImage30(Binary image30) {
        this.image30 = image30;
    }

    public Binary getImage50() {
        return image50;
    }

    public void setImage50(Binary image50) {
        this.image50 = image50;
    }
}
