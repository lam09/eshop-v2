package com.lam.eshopv2.nosql.entity;

/**
 * Created by a.lam.tuan on 2. 8. 2018.
 */
public class ProductImage {
    String imageSize;
    byte[] image;

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
