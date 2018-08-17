package com.lam.eshopv2.nosql.repo;

import com.lam.eshopv2.nosql.entity.CategoryCollection;
import com.lam.eshopv2.nosql.entity.ProductCollection;

import java.util.List;

/**
 * Created by a.lam.tuan on 17. 8. 2018.
 */
public interface CategoryCollectionRepository {
    public CategoryCollection findCategoryCollectionByName(String name);
    public List<CategoryCollection> findCategoryCollectionsByProductCollection(ProductCollection productCollection);
    public List<CategoryCollection> findAllCategoryCollections();
    public String addNewCategoryCollection(String name);
    public CategoryCollection addProductToCategory(ProductCollection productCollection,CategoryCollection categoryCollection);
    public CategoryCollection removeProductFromCategory(ProductCollection productCollection,CategoryCollection categoryCollection);
    public List<ProductCollection> findProductCollectionsByCategory(CategoryCollection categoryCollection);
}
