package com.lam.eshopv2.nosql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.eshopv2.nosql.entity.ProductCollection;
import com.lam.eshopv2.nosql.entity.ProductImage;
import com.lam.eshopv2.nosql.repo.ProductRepo;
import com.lam.eshopv2.utils.Utils;
import com.lam.restaurant.Ultil;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by a.lam.tuan on 2. 8. 2018.
 */
@org.springframework.stereotype.Controller
public class Controller {
    private static String UPLOAD_DIR = System.getProperty("user.home") + "/test";

    @Autowired
    ProductRepo productRepo;

 /*   @RequestMapping(value = "/saveProductMongo",method = RequestMethod.POST
            //,produces = {MediaType.APPLICATION_JSON_VALUE}
            )
*/

    @RequestMapping(value = "/getProductMongo/{id}",method = RequestMethod.GET,
    produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ProductCollection getProduct(@PathVariable("id")Integer id, HttpServletRequest request){
        ProductCollection productCollection=productRepo.findProductCollectionById(id);
        Utils.setCurrentProductCollection(productCollection,request);
        return productCollection;
    }

    @DeleteMapping("/deleteImageMongo/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable("id")String id,HttpServletRequest request){
        ProductCollection productCollection=Utils.getProcductCollectionInSession(request);
        List<ProductImage> images=productCollection.getProductImages();
        Optional<ProductImage> image=images.stream().filter(x->(x.getId().compareTo(id)==0)).findFirst();
        image.ifPresent(i->{
            images.remove(i);
            productCollection.setProductImages(images);
            productRepo.save(productCollection);
            Utils.setCurrentProductCollection(productCollection,request);
        });
            return new ResponseEntity<String>("",HttpStatus.OK);
    }

    @PostMapping(value = "/saveProductMongo",
    produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveProduct(@ModelAttribute ProductColletionForm form,HttpServletRequest request){
        ProductCollection productCollection=Utils.getProcductCollectionInSession(request);
        //productCollection.setId(form.getId());
        productCollection.setCode(form.getCode());
        productCollection.setCreateDate(form.getCreateDate());
        productCollection.setDescription(form.getDescription());
        productCollection.setPrice(form.getPrice());
        productCollection.setVariants(form.getVariants());
        productCollection.setProperties(form.getProperties());
        productRepo.save(productCollection);
        return new ResponseEntity<String>("Saved to server " , HttpStatus.OK);
    }

    @PutMapping("saveProductInformation")
    public void saveProductInformation(@ModelAttribute ProductCollection product,HttpServletRequest request){
        ProductCollection productCollection=Utils.getProcductCollectionInSession(request);

    }

    @PostMapping("/uploadImage")
    private ResponseEntity<?> saveUploadedFiles(@ModelAttribute MultipartFile[] files,HttpServletRequest request) throws IOException {
        ProductCollection productCollection=Utils.getProcductCollectionInSession(request);
        List<ProductImage>images=productCollection.getProductImages();
        if(files!=null) Arrays.stream(files).forEach(multipartFile -> {
            ProductImage image = new ProductImage();
            image.setId(UUID.randomUUID().toString());
            image.setImageSize("normal");
            try {
                image.setImage(new Binary(multipartFile.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            images.add(image);
        });
        productCollection.setProductImages(images);
        productRepo.save(productCollection);
        return new ResponseEntity<String>("Saved to server " , HttpStatus.OK);
    }
    public void saveToFile(MultipartFile[]files){

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
