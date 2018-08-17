package com.lam.eshopv2.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by a.lam.tuan on 16. 8. 2018.
 */
public class UrlInterceptor extends HandlerInterceptorAdapter {
    private static final Log logger = LogFactory.getLog(UrlInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       // logger.info("prehandle function " + request);
        logger.info("Recieved Request URL: " + request.getRequestURL());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        logger.info("Request URL: " + request.getRequestURL()+ " processed");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        logger.info("after function " + request);
        super.afterCompletion(request, response, handler, ex);
    }
}
