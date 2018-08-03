package com.lam.eshopv2.nosql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.eshopv2.nosql.entity.ProductCollection;
import com.lam.eshopv2.nosql.entity.ProductImage;
import com.lam.eshopv2.nosql.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by a.lam.tuan on 2. 8. 2018.
 */
@org.springframework.stereotype.Controller
public class Controller {


    @Autowired
    ProductRepo productRepo;

    @RequestMapping(value = "/saveProductMongo",method = RequestMethod.POST
            //,produces = {MediaType.APPLICATION_JSON_VALUE}
            )
    public ResponseEntity<?> saveProduct(@ModelAttribute ProductColletionForm form){
        System.out.println(toJson(form));
        ProductCollection productCollection= new ProductCollection();
        productCollection.setId(form.getId());
        productCollection.setCode(form.getCode());
        productCollection.setCreateDate(form.getCreateDate());
        productCollection.setDescription(form.getDescription());
        productCollection.setPrice(form.getPrice());
        List<ProductImage> images = new ArrayList<>();
       if(form.getFiles()!=null) Arrays.stream(form.getFiles()).forEach(multipartFile -> {
            ProductImage image = new ProductImage();
            image.setImageSize("normal");
            try {
                image.setImage(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            images.add(image);
        });
        productCollection.setProductImages(images);

        productRepo.save(productCollection);

        return new ResponseEntity<String>("Saved to server " , HttpStatus.OK);
    }

    public String toJson(Object o){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonString;
    }

}
