package com.lam.eshopv2.repository;

import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.entity.Property;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by a.lam.tuan on 12. 6. 2018.
 */
@Repository
@Transactional
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;
    public List<Product> searchProductByNameKey(String key){
        Query query = entityManager.createNativeQuery("SELECT * FROM products as pr " +
                "WHERE pr.name LIKE ?", Product.class);
        query.setParameter(1, key + "%");
        return query.getResultList();
    }

}
