package com.recom.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;


/**
 * Created by rayd on 2017/3/27.
 */
@Aspect
@Component
public class HttpAspect {
    @Pointcut("execution(public * com.recom.controller.*.*(..))")
    public void log(){
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       // HttpServletRequest request = attributes.getRequest();

         //获取response
        HttpServletResponse response = attributes.getResponse();
        response.setHeader("Access-Control-Allow-Origin", "*");     //设置允许哪些url可以跨域请求到本域，*表示所有

    }


}
