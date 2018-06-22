package com.lam.eshopv2.controller;

import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.entity.ProductImage;
import com.lam.eshopv2.repository.CategoryRepository;
import com.lam.eshopv2.repository.ProductImageRepository;
import com.lam.eshopv2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by a.lam.tuan on 13. 6. 2018.
 */
@Controller
@RequestMapping("/image")
public class ImageController {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductImageRepository productImageRepository;


    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam("id") Integer id) throws IOException {
        Product product = null;
        ProductImage productImage=null;
        if (id != null) {
            productImage=productImageRepository.findProductImageById(id);
        }
        if (productImage != null && productImage.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(productImage.getImage());
        }
        response.getOutputStream().close();
    }



}
