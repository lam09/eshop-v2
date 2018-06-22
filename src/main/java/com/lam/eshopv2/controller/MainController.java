package com.lam.eshopv2.controller;

import com.lam.eshopv2.entity.Customer;
import com.lam.eshopv2.entity.Product;
import com.lam.eshopv2.form.CustomerForm;
import com.lam.eshopv2.model.CartInfo;
import com.lam.eshopv2.repository.CategoryRepository;
import com.lam.eshopv2.repository.ProductRepository;
import com.lam.eshopv2.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.lam.tuan on 13. 6. 2018.
 */

@Controller
@Transactional
@RequestMapping("/shop")
public class MainController {


    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);


    }

    @RequestMapping(value={"/"}, method = RequestMethod.GET)
    public String home(Model model){
        return "index";
    }

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String login( Model model){
        return "login";
    }

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @RequestMapping("/product/{id}")
    public String product(@PathVariable Integer id, Model model){
        System.out.print("get product id "+ id);
        Product product = productRepository.findProductById(id);
        model.addAttribute("product", product);

        return "productdetail";
    }

    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public String productsList(Model model, //
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "type", defaultValue = "list") String type){
        /*pagination style 1 */
        System.out.print("Hello all product");
        final int product_count=productRepository.countProducts();
        final int max_cout_of_page = 12;
        List<Product> products = productRepository.paginationProduct(max_cout_of_page,max_cout_of_page*(page-1));
        System.out.println("product count " + product_count + " max_cout_of_page "+max_cout_of_page);
        Integer maxPage=(int)Math.ceil(product_count/(double)max_cout_of_page);
        System.out.println("max page number " + maxPage);

        List<Integer> listPages=new ArrayList<>();
        for(int i=1;i<=maxPage;i++) listPages.add(i);
        model.addAttribute("listPages", listPages);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("products", products);

        System.out.print("Product count " + products.size() + " max" + product_count);
        //if(type!="grid") return "products";
        //else
        return "productgrid";
    }

    @RequestMapping("/products/{category}")
    public String productCategoryList(@PathVariable String category, Model model, //
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "type", defaultValue = "list") String type){
        /*pagination style 1 */
        System.out.print("Hello product category");
        final int product_count=productRepository.countProductsByCategory(category);
        final int max_cout_of_page = 12;
        List<Product> products =productRepository.paginationProductByCategory(category, max_cout_of_page,max_cout_of_page*(page-1));
        System.out.println("product count " + product_count + " max_cout_of_page "+max_cout_of_page);
        Integer maxPage=(int)Math.ceil(product_count/(double)max_cout_of_page);
        System.out.println("max page number " + maxPage);

        List<Integer> listPages=new ArrayList<>();
        for(int i=1;i<=maxPage;i++) listPages.add(i);
        model.addAttribute("listPages", listPages);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("products", products);

        System.out.print("Product count " + products.size() + " max" + product_count);
        //if(type!="grid") return "products";
        //else
        return "productgrid";
    }

    @RequestMapping({ "/buyProduct" })
    public String listProductHandler(HttpServletRequest request, Model model, //
                                     @RequestParam(value = "code", defaultValue = "") String code) {

        Product product = null;
        if (code != null && code.length() > 0) {
            product = productRepository.findProductByCode(code);
        }
        if (product != null) {

            //
            CartInfo cartInfo = Utils.getCartInSession(request);

           // ProductInfo productInfo = new ProductInfo(product);

            cartInfo.addProduct(product, 1);
        }

        return "redirect:shoppingCart";
    }

    @RequestMapping({ "/shoppingCartRemoveProduct" })
    public String removeProductHandler(HttpServletRequest request, Model model, //
                                       @RequestParam(value = "code", defaultValue = "") String code) {
        Product product = null;
        if (code != null && code.length() > 0) {
            product = productRepository.findProductByCode(code);
        }
        if (product != null) {

            CartInfo cartInfo = Utils.getCartInSession(request);

          //  ProductInfo productInfo = new ProductInfo(product);

            cartInfo.removeProduct(product);

        }

        return "redirect:shoppingCart";
    }

    // POST: Cập nhập số lượng cho các sản phẩm đã mua.
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
    public String shoppingCartUpdateQty(HttpServletRequest request, //
                                        Model model, //
                                        @ModelAttribute("cartForm") CartInfo cartForm) {

        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);

        return "redirect:shoppingCart";
    }

    // GET: Hiển thị giỏ hàng.
    @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        CartInfo myCart = Utils.getCartInSession(request);

        model.addAttribute("cartForm", myCart);
        return "shoppingCart";
    }

    // GET: Nhập thông tin khách hàng.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

        CartInfo cartInfo = Utils.getCartInSession(request);

        if (cartInfo.isEmpty()) {

            return "redirect:shoppingCart";
        }
        Customer customerInfo = cartInfo.getCustomer();

        CustomerForm customerForm = new CustomerForm(customerInfo);

        model.addAttribute("customerForm", customerForm);

        return "shoppingCartCustomer";
    }

    // POST: Save thông tin khách hàng.
    @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request, //
                                           Model model, //
                                           @ModelAttribute("customerForm") @Validated CustomerForm customerForm, //
                                           BindingResult result, //
                                           final RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            customerForm.setValid(false);
            // Forward tới trang nhập lại.
            return "shoppingCartCustomer";
        }

        customerForm.setValid(true);
        CartInfo cartInfo = Utils.getCartInSession(request);
        Customer customerInfo = new Customer(customerForm);
        cartInfo.setCustomer(customerInfo);

        return "redirect:shoppingCartConfirmation";
    }

    // GET: Xem lại thông tin để xác nhận.
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);

        if (cartInfo == null || cartInfo.isEmpty()) {

            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {

            return "redirect:/shoppingCartCustomer";
        }
        model.addAttribute("myCart", cartInfo);

        return "shoppingCartConfirmation";
    }

    // POST: Gửi đơn hàng (Save).
    @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)

    public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);

        if (cartInfo.isEmpty()) {

            return "redirect:/shoppingCart";
        } else if (!cartInfo.isValidCustomer()) {

            return "redirect:/shoppingCartCustomer";
        }
        try {
            //orderDAO.saveOrder(cartInfo);
        } catch (Exception e) {

            return "shoppingCartConfirmation";
        }

        // Xóa giỏ hàng khỏi session.
        Utils.removeCartInSession(request);

        // Lưu thông tin đơn hàng cuối đã xác nhận mua.
        Utils.storeLastOrderedCartInSession(request, cartInfo);

        return "redirect:/shoppingCartFinalize";
    }

    @RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {

        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);

        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }
        model.addAttribute("lastOrderedCart", lastOrderedCart);
        return "shoppingCartFinalize";
    }






}