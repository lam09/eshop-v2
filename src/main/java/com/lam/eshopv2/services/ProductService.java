package com.lam.eshopv2.services;

import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.entity.Property;

import java.util.List;

/**
 * Created by a.lam.tuan on 12. 6. 2018.
 */
public interface ProductService {
    public List<Product> findAllProduct();
    public List<Product> findAllProductOrderedByPrice();
    public List<Product> searchProductByName();
    public List<Product> searchProductByProperties(List<Property>properties);
}
