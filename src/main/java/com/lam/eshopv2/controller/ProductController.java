package com.lam.eshopv2.controller;

/**
 * Created by a.lam.tuan on 12. 6. 2018.
 */

import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class ProductController {


    @Autowired
    ProductRepository productRepository;


    @RequestMapping(value = {"/product/{id}"},method = RequestMethod.GET)
    public String product(@PathVariable Integer id, Model model){
        System.out.print("get product id "+ id);
        //Product product = productRepository.findProductById(id);
        model.addAttribute("product", productRepository.findProductById(id));
        return "product";
    }

    @RequestMapping(value = "/admin/createProduct",method = RequestMethod.GET)
    public String createProduct(Model model){
        return "admin/addproduct";
    }

    @RequestMapping("/admin/editProduct/{id}")
    public String eidtProduct( @PathVariable Integer id,Model model){
        model.addAttribute("product", productRepository.findProductById(id));
        return "admin/editproduct";
    }
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public String productsList(Model model, //
                               @RequestParam(value = "page", defaultValue = "1") int page){

        /*pagination style 1 */



        System.out.print("Hello all product");
        final int product_count=productRepository.countProducts();
        final int max_cout_of_page = 5;
        List<Product> products = productRepository.paginationProducṭ̣(max_cout_of_page,max_cout_of_page*(page-1));
        model.addAttribute("maxPage", (int)Math.round(product_count/max_cout_of_page));
        model.addAttribute("currentPage", page);
        model.addAttribute("products", products);
        System.out.print("Product count " + products.size() + " max" + product_count);
        return "products";
    }

    @RequestMapping(value = "/saveedittedproduct", method = RequestMethod.POST)
    public @ResponseBody
    String saveEdittedProduct(@RequestBody Product product) {
            if(!productRepository.existsById(product.getId())) {
                return "Product not exits ";
            }
            else{
                Product p=productRepository.findProductById(product.getId());
                p.setCode(product.getCode());
                p.setDescription(product.getDescription());
                p.setPrice(product.getPrice());
                p.setQuantity(product.getQuantity());
                p.setName(product.getName());
                productRepository.save(p);
                return "Product is successfully saved";
            }
    }

    @RequestMapping(value = "/saveproduct", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
     String saveProduct(@RequestBody Product product) {
        if(product.getId()!=null){
            if(productRepository.existsById(product.getId())) {
                productRepository.save(product);
                return product.getId().toString();
            }
        }
        System.out.print("Hello save product");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        product.setCreateDate(date);
        try {
            productRepository.save(product);
        }
        catch (Exception e){
            return e.toString();
        }
        return product.getId().toString();
    }

}