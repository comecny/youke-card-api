package com.youke.shiro;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShiroUserFilter extends UserFilter {

    /*在访问过来的时候检测是否为OPTIONS请求，如果是就直接返回true*/
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            setHeader(httpRequest,httpResponse);
            return true;
        }
        return super.preHandle(request,response);
    }

     /* 该方法会在验证失败后调用,因此重写改成传输JSON数据*/
    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        saveRequest(request);
        setHeader((HttpServletRequest) request,(HttpServletResponse) response);
        PrintWriter out = response.getWriter();
        out.flush();
        out.close();
    }

 /* 实现跨域*/
    private void setHeader(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Access-Control-Allow-Headers",  "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT,OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }

    @Bean
    public FilterRegistrationBean registration(ShiroUserFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

}
