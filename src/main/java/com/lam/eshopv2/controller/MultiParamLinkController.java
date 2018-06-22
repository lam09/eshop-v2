package com.lam.eshopv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.lam.tuan on 19. 6. 2018.
 */
@Controller("multiParamLinkController")
@RequestMapping(value = "/multiParamLink")
public class MultiParamLinkController
{
    @RequestMapping(value =
            { "/",
                    "" }, method = RequestMethod.GET)
    public String testMultiParamsGenerator(Model model)
    {
        List<String> paramNames = new ArrayList<>();
        paramNames.add("fooValue");
        paramNames.add("barValue");
        paramNames.add("lang");

        model.addAttribute("fooValue", "foo");
        model.addAttribute("barValue", "bar");
        model.addAttribute("lang", "US_us");
        model.addAttribute("paramNames", paramNames);

        return "multiParamLink/multiParamLink.html";
    }

}