package com.lam.eshopv2.controller;

import com.lam.eshopv2.model.CartInfo;
import com.lam.eshopv2.utils.Utils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by a.lam.tuan on 27. 6. 2018.
 */
@Controller
public class DefautController {


    @ModelAttribute("cartForm")
    public CartInfo cartInfo(HttpServletRequest request){
        return Utils.getCartInSession(request);
    }

    @RequestMapping(value = {"","/"})
    public String home(){
        return "index";
    }

    @RequestMapping(value = {"/account"})
    public String account(Authentication authentication, Model model)
    {
        return "account";
    }
}
