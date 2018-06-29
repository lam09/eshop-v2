package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Category;
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

    @Query(value = "select * from products where id= :id",nativeQuery = true)
    Product findProductById(@Param("id")Integer id);

    Product findProductByCode(String code);

    @Query(value="select count(*) FROM products ",nativeQuery = true)
    Integer countProducts();

    @Query(value="select * FROM products " +
            "limit :LIMIT " +
            "offset :OFFSET",nativeQuery = true)
    List<Product> paginationProduct(@Param("LIMIT") Integer limit,@Param("OFFSET") Integer offset);

    @Query(value="select count(*) FROM products p JOIN categories c on c.product_id=p.id WHERE c.name LIKE :CATEGORYNAME ",nativeQuery = true)
    Integer countProductsByCategory(@Param("CATEGORYNAME")String CATEGORYNAME);

    @Query(value="select p.* FROM products p JOIN categories c on c.product_id=p.id WHERE c.name LIKE :CATEGORYNAME ",nativeQuery = true)
    List<Product> findProductsByCategory(@Param("CATEGORYNAME")String CATEGORYNAME);

    @Query(value="select p.* FROM products p JOIN categories c on c.product_id=p.id WHERE c.name LIKE :CATEGORYNAME " +
            "limit :LIMIT " +
            "offset :OFFSET",nativeQuery = true)
    List<Product> paginationProductByCategory(@Param("CATEGORYNAME")String CATEGORYNAME,@Param("LIMIT") Integer limit,@Param("OFFSET") Integer offset);


}
