package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Category;
import com.lam.eshopv2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by a.lam.tuan on 20. 6. 2018.
 */
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findCategoryById(Integer id);

    Category findCategoryByName(String name);

    List<Category> findCategoriesByProduct(Product product);

    List<Category> findAll();
}
