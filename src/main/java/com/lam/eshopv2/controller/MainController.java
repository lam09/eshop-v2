package com.lam.eshopv2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.eshopv2.entity.*;
import com.lam.eshopv2.form.ShippingAddressForm;
import com.lam.eshopv2.model.ShippingAddressInfo;
import com.lam.eshopv2.model.CartInfo;
import com.lam.eshopv2.model.ProductInfo;
import com.lam.eshopv2.repository.*;
import com.lam.eshopv2.utils.Eshop;
import com.lam.eshopv2.utils.Utils;
import com.lam.eshopv2.validation.ShippingAddressFormValidator;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by a.lam.tuan on 13. 6. 2018.
 */

@Controller
@Transactional
@RequestMapping("/shop")
public class MainController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ShippingAddressFormValidator shippingAddressFormValidator;

    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
        if (target.getClass() == CartInfo.class) {

        }

        // Trường hợp save thông tin khách hàng.
        // (@ModelAttribute @Validated CustomerInfo customerForm)
        else if (target.getClass() == ShippingAddressForm.class) {
            dataBinder.setValidator(shippingAddressFormValidator);
        }

    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) {
        model.addAttribute("hotProducts", hotProducts());
        model.addAttribute("cartForm", Utils.getCartInSession(request));
        return "index";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ShippingMethodRepository shippingMethodRepository;

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    OrderRepository orderRepository;


    @ModelAttribute("cartForm")
    public CartInfo cartInfo(HttpServletRequest request) {
        return Utils.getCartInSession(request);
    }

    @ModelAttribute("paymentMethodList")
    public List<String> paymentMethods() {
        List<String> payments=new ArrayList<>();
        paymentMethodRepository.findAll().stream().forEach(paymentMethod -> payments.add(paymentMethod.getName()));
        return payments;
    }

    @ModelAttribute("shippingMethodList")
    public List<String> shippingMethods() {
        List<String> shippings=new ArrayList<>();
        shippingMethodRepository.findAll().stream().forEach(ship -> shippings.add(ship.getName()));
        return shippings;
    }

    // @ModelAttribute("hotProducts")
    public List<ProductInfo> hotProducts() {
        System.out.print("Get hot product");
        List<ProductInfo> productInfos = new ArrayList<>();
        productRepository.findProductsByCategory("HOT").stream().forEach(product -> productInfos.add(fromProduct(product)));
        return productInfos;
    }

    ProductInfo fromProduct(Product product) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProduct(product);
        List<Integer> imageIds = new ArrayList<>();
        productImageRepository.findProductImagesByProduct(product).stream().forEach(image -> imageIds.add(image.getId()));
        productInfo.setProductImageIds(imageIds);
        ProductImage profil = productImageRepository.findProfilByProduct(product.getId());
        if (profil != null) productInfo.setProfilImageId(profil.getId());

        return productInfo;
    }

    @RequestMapping("/product/{id}")
    public String product(HttpServletRequest request, @PathVariable Integer id, Model model) {
        System.out.print("get product id " + id);
        Product product = productRepository.findProductById(id);
        ProductInfo productInfo = null;
        if (product != null) {
            productInfo = fromProduct(product);
        }
        productInfo.setProductImages(productImageRepository.findProductImagesByProduct(product));
        model.addAttribute("productInfo", productInfo);
        model.addAttribute("cartForm", Utils.getCartInSession(request));

        return "productdetail";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String productsList(HttpServletRequest request, Model model, //
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "type", defaultValue = "list") String type,
                               @RequestParam(value = "category", defaultValue = "") String category) {
        /*pagination style 1 */
        System.out.print("Hello all product");
        final int product_count = productRepository.countProducts();
        final int max_cout_of_page = 12;
        List<ProductInfo> productInfos = new ArrayList<>();
        //List<Product> products =
        productRepository.paginationProduct(max_cout_of_page, max_cout_of_page * (page - 1)).stream().forEach(product -> productInfos.add(fromProduct(product)));
        System.out.println("product count " + product_count + " max_cout_of_page " + max_cout_of_page);
        Integer maxPage = (int) Math.ceil(product_count / (double) max_cout_of_page);
        System.out.println("max page number " + maxPage);

        List<Integer> listPages = new ArrayList<>();
        for (int i = 1; i <= maxPage; i++) listPages.add(i);
        model.addAttribute("listPages", listPages);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("productInfos", productInfos);

        model.addAttribute("cartForm", Utils.getCartInSession(request));
        return "productgrid";
    }

    @RequestMapping("/products/{category}")
    public String productCategoryList(HttpServletRequest request, @PathVariable String category, Model model, //
                                      @RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "type", defaultValue = "list") String type) {
        /*pagination style 1 */
        System.out.print("Hello product category");
        final int product_count = productRepository.countProductsByCategory(category);
        final int max_cout_of_page = 12;
        List<ProductInfo> productInfos = new ArrayList<>();
        productRepository.paginationProductByCategory(category, max_cout_of_page, max_cout_of_page * (page - 1)).stream().forEach(product -> productInfos.add(fromProduct(product)));
        System.out.println("product count " + product_count + " max_cout_of_page " + max_cout_of_page);
        Integer maxPage = (int) Math.ceil(product_count / (double) max_cout_of_page);
        System.out.println("max page number " + maxPage);

        List<Integer> listPages = new ArrayList<>();
        for (int i = 1; i <= maxPage; i++) listPages.add(i);
        model.addAttribute("listPages", listPages);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("productInfos", productInfos);

        System.out.print("Product count " + productInfos.size() + " max" + product_count);
        model.addAttribute("cartForm", Utils.getCartInSession(request));
        return "productgrid";
    }

    @RequestMapping({"/buyProduct"})
    public String listProductHandler(HttpServletRequest request, HttpServletResponse response, Model model, //
                                     @RequestParam(value = "id", defaultValue = "") Integer id,
                                     @RequestParam(value = "quantity", defaultValue = "1") Integer quantity) {
        Product product = null;
        if (id != null) {
            product = productRepository.findProductById(id);
        }
        if (product != null) {
            System.out.print("add to cart product " + product.getId());
            //
            CartInfo cartInfo = Utils.getCartInSession(request);
            cartInfo.addProduct(product, quantity);
        } else {
            try {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("cartForm", Utils.getCartInSession(request));
        return "_header::#cart-item-list";
    }

    @RequestMapping({"/smallCart"})
    public String smallCart(HttpServletRequest request, Model model, //
                            @RequestParam(value = "id", defaultValue = "") Integer id,
                            @RequestParam(value = "quantity", defaultValue = "1") Integer quantity) {

        model.addAttribute("cartForm", Utils.getCartInSession(request));
        return "_header::#cart-item-list";
    }

    @RequestMapping(value = {"/updateCartProductQuantity"})
    public String updateProductQuantityHandler(HttpServletRequest request, Model model, //
                                               @RequestParam(value = "id", defaultValue = "") Integer id,
                                               @RequestParam(value = "quantity", defaultValue = "1") Integer quantity) {
        Product product = null;
        if (id != null) {
            product = productRepository.findProductById(id);
        }
        if (product != null) {
            CartInfo cartInfo = Utils.getCartInSession(request);
            cartInfo.updateProductQuantity(product, quantity);
        }
        model.addAttribute("cartForm", Utils.getCartInSession(request));
        return "cart::#cart";
    }

    @RequestMapping(value = {"/removeCartProduct"})
    public String removeProductHandler(HttpServletRequest request, Model model, //
                                       @RequestParam(value = "id", defaultValue = "") Integer id) {
        Product product = null;
        if (id != null) {
            product = productRepository.findProductById(id);
        }
        if (product != null) {
            CartInfo cartInfo = Utils.getCartInSession(request);
            cartInfo.removeProduct(product);
        }
        return "cart::#cart";
    }

    // GET: Hiển thị giỏ hàng.
    @RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET)
    public String shoppingCartHandler(HttpServletRequest request, Model model) {
        ShippingAddressForm shippingAddressForm = null;
        if(!cartInfo(request).isValidCustomer()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Account acc = null;
            if (auth.isAuthenticated()) {
                acc = accountRepository.findAccountByName(auth.getName());
            }
            shippingAddressForm = new ShippingAddressForm();
            if (acc != null) {
                shippingAddressForm = new ShippingAddressForm(new ShippingAddressInfo(acc));
            }
        }
        else {
            shippingAddressForm=new ShippingAddressForm(cartInfo(request).getShippingAddressInfo());
        }
        model.addAttribute("shippingAddressForm", shippingAddressForm);
        return "cart";
    }

    @RequestMapping(value = {"/authorizedShoppingCart"}, method = RequestMethod.GET)
    public String shoppingCartAuthorizedHandler(HttpServletRequest request, Model model) {
        return "redirect:/shop/shoppingCart";
    }


    @RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.POST)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model,
                                                 @ModelAttribute("shippingAddressForm") @Validated ShippingAddressForm shippingAddressForm,
                                                 BindingResult result) {
        CartInfo cartInfo = Utils.getCartInSession(request);
        if (result.hasErrors()||cartInfo == null || cartInfo.isEmpty()) {
            shippingAddressForm.setValid(false);
            // Forward tới trang nhập lại.
            return "redirect:/shop/shoppingCart";
        }
        shippingAddressForm.setValid(true);
        ShippingAddressInfo shippingAddressInfoInfo = new ShippingAddressInfo(shippingAddressForm);
        cartInfo.setShippingAddressInfo(shippingAddressInfoInfo);
        PaymentMethod paymentMethod=paymentMethodRepository.findPaymentMethodByName(shippingAddressForm.getPaymentMethod());
        ShippingMethod shippingMethod=shippingMethodRepository.findShippingMethodByName(shippingAddressForm.getShippingMethod());
        if (paymentMethod == null || shippingMethod == null) {
            return "redirect:/shop/shoppingCart";
        } else {
            cartInfo.setShippingMethod(shippingMethod);
            cartInfo.setPaymentMethod(paymentMethod);

            try {

                System.out.print("fucking function");
                Order order = new Order();
                order.setOrderNum(UUID.randomUUID().toString());
                order.setOrderDate(new Date(System.currentTimeMillis()));
                order.setPaid(false);
                order.setState(Eshop.OrderState.RECIEVED.toString());
                order.setCustomerEmail(cartInfo.getShippingAddressInfo().getEmail());
                order.setCustomerAddress(cartInfo.getShippingAddressInfo().getAddress());
                order.setCustomerCity(cartInfo.getShippingAddressInfo().getCity());
                order.setCustomerFirstName(cartInfo.getShippingAddressInfo().getFirstname());
                order.setCustomerLastName(cartInfo.getShippingAddressInfo().getLastname());
                order.setCustomerPhone(cartInfo.getShippingAddressInfo().getPhone());
                order.setCustomerPostalCode(cartInfo.getShippingAddressInfo().getPostalCode());
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth.isAuthenticated()) {
                    order.setAccount(accountRepository.findAccountByName(auth.getName()));
                }
                order.setPaymentMethod(cartInfo.getPaymentMethod());
                order.setShippingMethod(cartInfo.getShippingMethod());
                List<OrderDetail> orderDetails=new ArrayList<>();
                cartInfo.getCartLines().stream().forEach(line->{
                    OrderDetail orderDetail=new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(line.getProduct());
                    orderDetail.setPrice(line.getProduct().getPrice());
                    orderDetail.setQuantity(line.getQuantity());
                    orderDetail.setAmount(line.getAmount());
                    orderDetails.add(orderDetail);
                });
                order.setOrderDetails(orderDetails);
                orderRepository.save(order);
                // Xóa giỏ hàng khỏi session.
                cartInfo.setOrderNum(order.getId().toString());
                Utils.removeCartInSession(request);
                // Lưu thông tin đơn hàng cuối đã xác nhận mua.
                Utils.storeLastOrderedCartInSession(request, cartInfo);
            } catch (Exception e) {
                return "redirect:/shop/shoppingCart";
            }
            model.addAttribute("lastOrderedCart", Utils.getLastOrderedCartInSession(request));
            return "redirect:/shop/shoppingCartFinalize";
        }
    }



    @RequestMapping(value = {"/shoppingCartFinalize"}, method = RequestMethod.GET)
    public String shoppingCartFinalize(HttpServletRequest request, Model model) {
        CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
        if (lastOrderedCart == null) {
            return "redirect:/shoppingCart";
        }
        model.addAttribute("lastOrderedCart", lastOrderedCart);
        return "shoppingCartFinalize";
    }
}