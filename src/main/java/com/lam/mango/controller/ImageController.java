package com.lam.mango.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public class ImageController {

    @RequestMapping(value = "/image-manual-response", method = RequestMethod.GET)
    public void getImage(HttpServletResponse response)
    {
        InputStream inputStream =
    }

}
