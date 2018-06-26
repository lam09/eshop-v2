package com.lam.eshopv2.model;

import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.entity.ProductImage;
import com.lam.eshopv2.entity.Property;

import java.util.List;

/**
 * Created by a.lam.tuan on 26. 6. 2018.
 */
public class ProductInfo {
    Product product;

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<ProductImage> productImages;

    public List<Integer> getProductImageIds() {
        return productImageIds;
    }

    public void setProductImageIds(List<Integer> productImageIds) {
        this.productImageIds = productImageIds;
    }

    List<Integer> productImageIds;

    public Integer getProfilImageId() {
        return profilImageId;
    }

    public void setProfilImageId(Integer profilImageId) {
        this.profilImageId = profilImageId;
    }

    Integer profilImageId;
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    List<Property> properties;
}
