package com.lam.eshopv2.controller;

import com.lam.eshopv2.entity.Order;
import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.form.ProductForm;
import com.lam.eshopv2.repository.CategoryRepository;
import com.lam.eshopv2.repository.OrderRepository;
import com.lam.eshopv2.repository.ProductImageRepository;
import com.lam.eshopv2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    @RequestMapping(value = "/admin/product/{code}", method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Product getProduct(@PathVariable("code") String code) {
        return productRepository.findProductByCode(code);
    }
}
