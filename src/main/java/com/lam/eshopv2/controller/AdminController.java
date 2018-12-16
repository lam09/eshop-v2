package com.lam.eshopv2.controller;

/**
 * Created by a.lam.tuan on 12. 6. 2018.
 */
import com.lam.eshopv2.entity.Category;
import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.entity.ProductImage;
import com.lam.eshopv2.form.CategoryForm;
import com.lam.eshopv2.form.ImageForm;
import com.lam.eshopv2.repository.CategoryRepository;
import com.lam.eshopv2.repository.ProductImageRepository;
import com.lam.eshopv2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductImageRepository productImageRepository;



    @ModelAttribute("allCategoryNames")
    public List<String> allCategoryNames() {
        return categoryRepository.findAllCategoryNames();
    }

    @RequestMapping(value = "/createProduct",method = RequestMethod.GET)
    public String createProduct(Model model){
        return "admin/addproduct";
    }

    @RequestMapping("/editproduct/{id}")
    public String eidtProduct( @PathVariable Integer id,Model model){
        Product product=productRepository.findProductById(id);
        model.addAttribute("product", product);

        List<String> allCategoryNames=categoryRepository.findAllCategoryNames();
        model.addAttribute("allCategoryNames",allCategoryNames);

        List<Category> categories=categoryRepository.findCategoriesByProduct(product);
        model.addAttribute("categories",categories);

        CategoryForm categoryForm=null;
        if(product!=null){
            categoryForm=new CategoryForm();
            categoryForm.setProduct(product);
            model.addAttribute("categoryForm",categoryForm);
        }
        //GALLERY
        List<Integer> imageIds = new ArrayList<>();
        productImageRepository.findProductImagesByProduct(product).stream().forEach(e->imageIds.add(e.getId()));
        model.addAttribute("imageIds",imageIds);

        ImageForm imageForm=new ImageForm();
        imageForm.setProduct(product);
        model.addAttribute("imageForm",imageForm);

        return "admin/editproduct";
    }



    @RequestMapping(value = "/editproduct/saveedittedproduct", method = RequestMethod.POST)
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
    public
    @ResponseBody
     String saveProduct(@RequestBody Product product) {
        if(product.getId()!=null){
            if(productRepository.existsById(product.getId())) {
                productRepository.save(product);
                return product.getId().toString();
            }
        }
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

    @RequestMapping(value="/editproduct/productcategory/{id}", method=RequestMethod.GET)
    public String getProductCategory(@PathVariable Integer id, ModelMap map) {
        // TODO: retrieve the new value here so you can add it to model map
        List<Category> categories=categoryRepository.findCategoriesByProduct(productRepository.findProductById(id));
        map.addAttribute("categories", categories);

        // change "myview" to the name of your view
        return "admin/editproduct::#product_category_list";
    }

    @RequestMapping(value = "/editproduct/{id}/addcategory", method = RequestMethod.POST/*,produces = MediaType.APPLICATION_JSON_VALUE*/)
    public String addCategory(@RequestBody CategoryForm categoryForm, @PathVariable("id")Integer productId , Model model) {

        Product product= productRepository.findProductById(productId);
        Arrays.asList(categoryForm.getNames()).stream().forEach(name->{
            System.out.print(name + " fcc ");
            Category category = new Category();
            category.setName(name);
            category.setProduct(product);
            categoryRepository.save(category);
        });

        // TODO: retrieve the new value here so you can add it to model map
        List<Category> categories1=categoryRepository.findCategoriesByProduct(product);
        model.addAttribute("categories", categories1);

        model.addAttribute("allCategoryNames",allCategoryNames());
        // change "myview" to the name of your view
        return "admin/editproduct::#product_category_list";
    }

    @RequestMapping(value = "/editproduct/removecategory", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCategory(@RequestParam("categoryId") Integer categoryId,ModelMap map) {
        Category category=categoryRepository.findCategoryById(categoryId);
        Integer product_id= category.getProduct().getId();
        if(category!=null)categoryRepository.delete(category);

        // TODO: retrieve the new value here so you can add it to model map
        List<Category> categories=categoryRepository.findCategoriesByProduct(productRepository.findProductById(product_id));
        map.addAttribute("categories", categories);

        System.out.println("Product category is removed");
        // change "myview" to the name of your view
       return "admin/editproduct::#product_category_list";
    }

    //UPDATE GALLERY
    @RequestMapping(value = "/editproduct/deleteimage",method = RequestMethod.DELETE)
    public String deleteProductImage(@RequestParam("imageId") Integer imageId, ModelMap map)
    {
        ProductImage productImage=productImageRepository.findProductImageById(imageId);

        Product product=productImage.getProduct();

        if(productImage!=null) productImageRepository.delete(productImage);

        //GALLERY
        List<Integer> imageIds = new ArrayList<>();
        productImageRepository.findProductImagesByProduct(product).stream().forEach(e->imageIds.add(e.getId()));
        map.addAttribute("imageIds",imageIds);

        return "admin/editproduct::#product_gallery_list";
    }

    @RequestMapping(value = "/editproduct/setprofilimage",method = RequestMethod.POST)
    public String setProductImageAsProfil(@RequestParam("imageId") Integer imageId, ModelMap map)
    {
        ProductImage productImage=productImageRepository.findProductImageById(imageId);
        Product product=productImage.getProduct();
        ProductImage currentProfil = productImageRepository.findProfilByProduct(product.getId());
        currentProfil.setType("");
        productImageRepository.save(currentProfil);
        productImage.setType("PROFIL");
        productImageRepository.save(productImage);

        //GALLERY
        List<Integer> imageIds = new ArrayList<>();
        productImageRepository.findProductImagesByProduct(product).stream().forEach(e->imageIds.add(e.getId()));
        map.addAttribute("imageIds",imageIds);

        return "admin/editproduct::#product_gallery_list";
    }

    @RequestMapping(value = "/editproduct/{id}/addimage",method = RequestMethod.POST)
    public String addProductImage(@ModelAttribute("imageForm") @Validated ImageForm imageForm, @PathVariable Integer id, ModelMap map)
    {
        Product product=productRepository.findProductById(id);
        Arrays.stream(imageForm.getFileData()).forEach(e->{
            ProductImage currentProfil = productImageRepository.findProfilByProduct(product.getId());

            ProductImage productImage=new ProductImage();
            productImage.setProduct(product);
            try {
                byte[] image=e.getBytes();
                System.out.print("I am here");
                if(image!=null) {
                    productImage.setImage(image);
                    if(currentProfil==null) productImage.setType("PROFIL");
                    productImageRepository.save(productImage);
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
        return "redirect:";
    }



}