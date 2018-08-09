package com.lam.eshopv2.nosql.repo;

/**
 * Created by a.lam.tuan on 1. 8. 2018.
 */

import org.springframework.data.mongodb.repository.MongoRepository;
import com.lam.eshopv2.nosql.entity.*;

// This is an Interface.
// No need Annotation here
public interface ProductRepo extends MongoRepository<ProductCollection, Integer> {
    ProductCollection findProductCollectionById(Integer id);
}