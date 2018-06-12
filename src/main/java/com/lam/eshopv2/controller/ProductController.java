package com.lam.eshopv2.controller;

/**
 * Created by a.lam.tuan on 12. 6. 2018.
 */

import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ProductController {


    @Autowired
    ProductRepository productRepository;

    @RequestMapping("/product/{id}")
    public String product(@PathVariable Integer id, Model model){
        model.addAttribute("product", productRepository.findById(id));
        return "product";
    }

    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public String productsList(Model model){
        System.out.print("Hello all product");
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
    @ResponseBody public String saveProduct(@RequestBody Product product) {
        System.out.print("Hello save product");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        product.setCreateDate(date);
        productRepository.save(product);
        return product.getId().toString();
  //   return "success";
    }

}