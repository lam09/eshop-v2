package com.lam.eshopv2.nosql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.eshopv2.nosql.entity.CategoryCollection;
import com.lam.eshopv2.nosql.entity.ProductCollection;
import com.lam.eshopv2.nosql.entity.ProductImage;
import com.lam.eshopv2.nosql.repo.ProductRepo;
import com.lam.eshopv2.utils.Utils;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    /**
     * Get all products
     */
    @GetMapping("/allProduct")
    public @ResponseBody
    List<ProductCollection> getAllProductCollection(@PathVariable("from") Integer fromIndex, @PathVariable("to") Integer toIndex) {
        return productRepo.findAll().subList(fromIndex, toIndex);
    }


    /**
     * Get Product by id
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/getProductMongo/{id}", method = RequestMethod.GET,
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ProductCollection getProduct(@PathVariable("id") Integer id, HttpServletRequest request) {
        ProductCollection productCollection = productRepo.findProductCollectionById(id);
        return productCollection;
    }

    /**
     * Delete image by image id and product id.
     * @param id
     * @param productId
     * @return image id if success else return null.
     */
    @DeleteMapping("/deleteImage")
    public @ResponseBody String deleteImage(@RequestParam("id") String id, @RequestParam("productId") Integer productId) {
        String result[]={null};
        productRepo.findById(productId).ifPresent(productCollection -> {
            List<ProductImage> images = productCollection.getProductImages();
            Optional<ProductImage> image = images.stream().filter(x -> (x.getId().compareTo(id) == 0)).findFirst();
            image.ifPresent(i -> {
                images.remove(i);
                productCollection.setProductImages(images);
                productRepo.save(productCollection);
                result[0]=i.getId();
            });
        });
        return result[0];
    }

    /**
     * Add new product collection
     * @param productCollection
     * @return product id if success, else return null.
     */
    @PostMapping(value = "/addNewProduct",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Integer addNewProduct(@RequestBody ProductCollection productCollection){
        try {
            //productCollection.setId(UUID.randomUUID().toString());
            productCollection=productRepo.save(productCollection);
            return productCollection.getId();
        }catch (Exception e){
            return null;
        }
    }

    @PostMapping(value = "/updateProduct",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateProduct(@RequestBody ProductColletionForm form, HttpServletRequest request) {
//        ProductCollection productCollection = Utils.getProcductCollectionInSession(request);
        ProductCollection productCollection = productRepo.findProductCollectionById(form.getId());
        productCollection.setCode(form.getCode());
        productCollection.setCreateDate(form.getCreateDate());
        productCollection.setDescription(form.getDescription());
        productCollection.setPrice(form.getPrice());
        productCollection.setVariants(form.getVariants());
        productCollection.setProperties(form.getProperties());
        productRepo.save(productCollection);
        return new ResponseEntity<String>("Saved to server ", HttpStatus.OK);
    }

    @PostMapping(value = "/uploadImageToProduct",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody String uploadImagesToProduct(@RequestBody MultipartFile file,@RequestParam("productId")Integer productId){
        final String[] result = {null};
        productRepo.findById(productId).ifPresent(productCollection -> {
            List<ProductImage> images = productCollection.getProductImages();
            if (file != null) {
                ProductImage image = new ProductImage();
                image.setId(UUID.randomUUID().toString());
                image.setImageSize("normal");
                try {
                    image.setImage(new Binary(file.getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                images.add(image);
                productCollection.setProductImages(images);
                productRepo.save(productCollection);
                result[0] =image.getId();
            }
        });
        return result[0];
    }


    public String addNewCategory(@RequestBody CategoryCollection categoryCollection){
        return null;
    }

/*    @PostMapping("/uploadImage")
    private ResponseEntity<?> saveUploadedFiles(@ModelAttribute MultipartFile[] files, HttpServletRequest request) throws IOException {
        ProductCollection productCollection = Utils.getProcductCollectionInSession(request);
        List<ProductImage> images = productCollection.getProductImages();
        if (files != null) Arrays.stream(files).forEach(multipartFile -> {
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
        return new ResponseEntity<String>("Saved to server ", HttpStatus.OK);
    }
*/


    public String toJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonString;
    }

}
