package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.entity.Property;

import java.util.List;

/**
 * Created by a.lam.tuan on 12. 6. 2018.
 */
public interface ProductRepositoryCustom {
    List<Product> searchProductByNameKey(String key);
}
