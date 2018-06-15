package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
public interface ProductRepository extends JpaRepository<Product, Integer>,ProductRepositoryCustom {

    Product findProductById(Integer id);
}
