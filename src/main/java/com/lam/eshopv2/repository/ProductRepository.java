package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.pagination.PaginationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;



import java.util.List;

/**
 * Created by a.lam.tuan on 11. 6. 2018.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>,ProductRepositoryCustom,PagingAndSortingRepository<Product, Integer>{

    Product findProductById(Integer id);

    @Query(value="select count(*) FROM products ",nativeQuery = true)
    Integer countProducts();

    @Query(value="select * FROM products " +
            "limit :LIMIT " +
            "offset :OFFSET",nativeQuery = true)
    List<Product> paginationProducṭ̣(@Param("LIMIT") Integer limit,@Param("OFFSET") Integer offset);

}
