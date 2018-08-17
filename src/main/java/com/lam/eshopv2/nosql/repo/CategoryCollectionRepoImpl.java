package com.lam.eshopv2.nosql.repo;

import com.lam.eshopv2.nosql.entity.CategoryCollection;
import com.lam.eshopv2.nosql.entity.ProductCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by a.lam.tuan on 17. 8. 2018.
 */
@Repository
public class CategoryCollectionRepoImpl implements CategoryCollectionRepository{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public CategoryCollection findCategoryCollectionByName(String name) {
        Query query=new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query,CategoryCollection.class);
    }

    @Override
    public List<CategoryCollection> findCategoryCollectionsByProductCollection(ProductCollection productCollection) {
        return null;
    }

    @Override
    public List<CategoryCollection> findAllCategoryCollections() {
        return null;
    }

    @Override
    public String addNewCategoryCollection(String name) {
        return null;
    }

    @Override
    public CategoryCollection addProductToCategory(ProductCollection productCollection, CategoryCollection categoryCollection) {
        return null;
    }

    @Override
    public CategoryCollection removeProductFromCategory(ProductCollection productCollection, CategoryCollection categoryCollection) {
        return null;
    }

    @Override
    public List<ProductCollection> findProductCollectionsByCategory(CategoryCollection categoryCollection) {
        return null;
    }
}
