package com.lam.eshopv2.chat;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model)
    {
        String username = (String) request.getSession().getAttribute("username");
        if(username==null||username.isEmpty())
        return "redirect:/login";
        model.addAttribute("username",username);
        return "chat";
    }


    public String showLoginPage()
    {
        return "login";
    }

}
