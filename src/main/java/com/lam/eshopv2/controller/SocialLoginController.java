package com.lam.eshopv2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lam.eshopv2.socials.dao.facebook.FacebookUserDao;
import com.lam.eshopv2.socials.dao.google.GoogleUserDao;
import com.lam.eshopv2.socials.facebook.FacebookUser;
import com.lam.eshopv2.socials.facebook.RestFB;
import com.lam.eshopv2.socials.google.GooglePojo;
import com.lam.eshopv2.socials.google.GoogleUtils;
import com.lam.eshopv2.socials.google.Google_user;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by a.lam.tuan on 7. 8. 2018.
 */
@Controller
public class SocialLoginController {

    @Autowired
    private RestFB restFb;

    @Autowired
    private GoogleUtils googleUtils;


    @Autowired
    private FacebookUserDao facebookUserDao;

    @Autowired
    private GoogleUserDao googleUserDao;

/*
    @RequestMapping("/error")
    public String getError(){
        return "403";
    }

*/

    @RequestMapping(value = { "/login" })
    public String login() {
        return "login";
    }

    @RequestMapping("/login-facebook")
    public String loginFacebook(HttpServletRequest request) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");
        System.out.print(code);

        if (code == null || code.isEmpty()) {
            return "redirect:/login?facebook=error";
        }
        String accessToken = restFb.getToken(code);

        com.restfb.types.User user = restFb.getUserInfo(accessToken);
        System.out.println("\nUser info: "+toJson(user));
  /*      FacebookUser facebookUser=new FacebookUser();
        facebookUser.setId(user.getId());
        facebookUser.setUserInfo(toJson(user));
        facebookUserDao.save(facebookUser);
*/
        UserDetails userDetail = restFb.buildUser(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
                userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/user";
    }

    public void setDataSource(String key){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        request.setAttribute("keyDS", key);
    }

    @RequestMapping("/login-google")
    public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");


        System.out.print(code);
        if (code == null || code.isEmpty()) {
            return "redirect:/login?google=error";
        }


        String accessToken = googleUtils.getToken(code);

        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);

        Google_user google_user=new Google_user();
        google_user.setEmail(googlePojo.getEmail());
        google_user.setInfo(toJson(googlePojo));
/*        setDataSource(Constants.JPA_UNIT_NAME_2);

        googleUserDao.save(google_user);
        System.out.println("\nUser info: "+toJson(googlePojo));
*/

        UserDetails userDetail = googleUtils.buildUser(googlePojo);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
                userDetail.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/user";
    }

    public String toJson(Object o){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonString;
    }

    @RequestMapping("/user")
    public String user() {
        return "user";
    }
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }

}
