package com.lam.eshopv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
