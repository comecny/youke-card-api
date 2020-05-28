package com.youke.common.spring.interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.youke.dao.UserMapper;
import com.youke.entity.User;
import com.youke.utils.JWTUtils;
import com.youke.utils.annotation.LoginToken;
import com.youke.utils.annotation.PassToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    static  final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Autowired
    private UserMapper userMapper;

    public  boolean  preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                               Object object) throws Exception {
        logger.debug("---------------token 拦截器----------------------------");
        //从hander里取出token
        String token = httpServletRequest.getHeader("token");
        if (!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        //检验是否有passtoken注解
        if (method.isAnnotationPresent(PassToken.class)){
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()){
                return true;
            }
        }

        //检验是否有授权的注解
        if (method.isAnnotationPresent(LoginToken.class)){
            LoginToken userLoginToken = method.getAnnotation(LoginToken.class);
            if (userLoginToken.required()){

                //认证
                if (token == null){
                    httpServletResponse.setStatus(400);
                }
                Integer userId = null;
                try {

                    userId = Integer.valueOf(JWTUtils.decode(token));

                    User user = userMapper.getUser(userId);
                    if (user == null) {
                        throw new RuntimeException("用户不存在，请重新登录");
                    }

                    JWTUtils.verity(token);
                }catch (JWTVerificationException e){
                    httpServletResponse.setStatus(401);
                }
                return true;
            }
        }

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
