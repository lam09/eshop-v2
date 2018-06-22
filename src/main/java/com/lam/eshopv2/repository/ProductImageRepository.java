package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {

    ProductImage findProductImageById(Integer id);

    List<ProductImage> findProductImagesByProduct(Product product);

}
