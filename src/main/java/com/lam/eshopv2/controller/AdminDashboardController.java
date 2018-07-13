package com.lam.eshopv2.controller;

import com.lam.eshopv2.entity.Order;
import com.lam.eshopv2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminDashboardController {

    @RequestMapping("/admin/dashboard")
    public String dashBoard(){
        return "/admin-dashboard/dashboard";
    }

    @RequestMapping("/admin/user")
    public String account(){
        return "/admin-dashboard/user";
    }

    @RequestMapping("/admin/orders")
    public String orders(){
        return "/admin-dashboard/orders";
    }

    @RequestMapping("/admin/order")
    public String order(){
        return "/admin-dashboard/user";
    }

    @RequestMapping("/admin/products")
    public String products(){
        return "/admin-dashboard/products";
    }

    @RequestMapping("/admin/productDetail")
    public String productDetail(@RequestParam("id")Integer id){
        return "/admin-dashboard/product";
    }

    @RequestMapping("/admin/icons")
    public String icons(){
        return "/admin-dashboard/icons";
    }

    @RequestMapping("/admin/accounts")
    public String accounts(){
        return "/admin-dashboard/accounts";
    }



/*
    @RequestMapping("/admin/products")
    public String products(){
        return "/admin-dashboard/table";
    }

    @RequestMapping("/admin/product")
    public String product(){
        return "/admin-dashboard/user";
    }
*/
}
