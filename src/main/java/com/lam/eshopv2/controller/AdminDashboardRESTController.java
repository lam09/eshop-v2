package com.lam.eshopv2.controller;

import com.lam.eshopv2.entity.Category;
import com.lam.eshopv2.entity.Order;
import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.form.ProductForm;
import com.lam.eshopv2.repository.CategoryRepository;
import com.lam.eshopv2.repository.OrderRepository;
import com.lam.eshopv2.repository.ProductImageRepository;
import com.lam.eshopv2.repository.ProductRepository;
import com.lam.eshopv2.services.ProductService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by a.lam.tuan on 6. 7. 2018.
 */
@RestController
public class AdminDashboardRESTController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @CrossOrigin()
    @RequestMapping(value = "/admin/orders/all", method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Order> allOrders() {
        return orderRepository.findAll();
    }

    @RequestMapping(value = "/admin/products/all", method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/admin/product", method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Product addNewProduct(@RequestBody ProductForm productForm) {
        Product product=productRepository.findProductByCode(productForm.getCode());
        if(product!=null){
            return product;
        }
        else product=productForm.toProduct();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        product.setCreateDate(date);
        try {
          return productRepository.save(product);
        }
        catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/admin/product/detail", method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public ProductForm getProductDetail(@RequestParam("id")Integer id) {
        Product product=productRepository.findProductById(id);

        if(product!=null)
        {
            ProductForm productForm=new ProductForm();
            productForm.setId(product.getId());
            productForm.setCode(product.getCode());
            productForm.setName(product.getName());
            productForm.setDescription(product.getDescription());
            productForm.setQuantity(product.getQuantity());
            productForm.setPrice(product.getPrice());
            List<String> categories = new ArrayList<>();
            categoryRepository.findCategoriesByProduct(product).stream().forEach(category -> categories.add(category.getName()));
            productForm.setCategory(categories);

            return productForm;
        }
        else return null;
    }

    @RequestMapping(value = "/admin/product", method = RequestMethod.DELETE)
    public void deleteProduct(@RequestParam("id") Integer id) {
        Product product=productRepository.findProductById(id);
        if(product!=null){
          productRepository.delete(product);
        }
    }


    @RequestMapping(value = "/admin/product", method = RequestMethod.PUT, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Product editProduct(@RequestBody ProductForm productForm) {
        Product product=productRepository.findProductById(productForm.getId());
        if(product!=null){
           // product.setCode(productForm.getCode());
            product.setDescription(productForm.getDescription());
            product.setQuantity(productForm.getQuantity());
            product.setPrice(productForm.getPrice());
            product.setName(productForm.getName());
        }
        else {
            product = productForm.toProduct();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            product.setCreateDate(date);
        }
        try {
            return productRepository.save(product);
        }
        catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value = "/admin/category/all", method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<String> allCategories() {
        List<String>categories = new ArrayList<>();
        categoryRepository.findAll().stream().forEach(category -> categories.add(category.getName()));
        return categories;
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public List<Category> allCategoriesByProduct(@RequestParam("productId")Integer productId) {
        return categoryRepository.findCategoriesByProduct(productRepository.findProductById(productId));
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.DELETE, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public void deleteCategory(@RequestParam("productId") Integer id,@RequestParam("name") String name) {
        try{
            Category category= categoryRepository.findCategoryByProductAndName(productRepository.findProductById(id),name);
            categoryRepository.delete(category);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/admin/category", method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Category saveCategory(@RequestParam("productId")Integer productId,@RequestParam("categoryName") String categoryName) {
        Category category=new Category();
        category.setProduct(productRepository.findProductById(productId));
        category.setName(categoryName);
        return categoryRepository.save(category);
    }

}
