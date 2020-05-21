package com.youke.common.spring.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthenticationInterceptor implements HandlerInterceptor {


    private static  final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    public  boolean  preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                               Object object) throws Exception {

        return true;
    }
    @Override
     public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                            Object o, ModelAndView modelAndView) throws Exception {
    }
    @Override
     public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                 Object o, Exception e) throws Exception {
        
    }
    
}
