package com.lam.eshopv2.services;

import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.entity.Property;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by a.lam.tuan on 12. 6. 2018.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findAllProduct() {
        return null;
    }

    @Override
    public List<Product> findAllProductOrderedByPrice() {
        return null;
    }

    @Override
    public List<Product> searchProductByName() {
        return null;
    }

    @Override
    public List<Product> searchProductByProperties(List<Property> properties) {
        return null;
    }
}
