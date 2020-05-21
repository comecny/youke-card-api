package com.youke.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    private RequestUtil() {
    }

    public static String getCurrentServletPath() {
        HttpServletRequest request = getCurrentReuest();
        String servletPath = request.getServletPath();
        return servletPath;
    }

    public static String getCurrentMethod() {
        HttpServletRequest request = getCurrentReuest();
        return request.getMethod();
    }

    public static HttpServletRequest getCurrentReuest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        
        return request;
    }
}
