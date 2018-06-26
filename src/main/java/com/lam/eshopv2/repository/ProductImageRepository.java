package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {

    ProductImage findProductImageById(Integer id);

    List<ProductImage> findProductImagesByProduct(Product product);

    @Query(value = "select * from product_image where product_id = :productId and type LIKE 'PROFIL'", nativeQuery = true)
    ProductImage findProfilByProduct(@Param("productId") Integer productId);

}
