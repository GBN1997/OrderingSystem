package com.xxx.ordersystem.aspect;

import com.xxx.ordersystem.constant.CookieConstant;
import com.xxx.ordersystem.constant.RedisConstant;
import com.xxx.ordersystem.exception.SellerAuthorizeException;
import com.xxx.ordersystem.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: GuBoNan
 * @Date: 2020/2/7 15:24
 * @Version: 1.0
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.xxx.ordersystem.controller.Seller*.*(..))" +
    "&& !execution(public * com.xxx.ordersystem.controller.SellerController.*(..))")
    public void verify(){

    }

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null){
            log.warn("【登录校验】 Cookie中查不到Token");
            throw new SellerAuthorizeException();
        }

        //查询redis
        String redisToken = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(redisToken)){
            log.warn("【登录校验】 Cookie中查不到Token");
            throw new SellerAuthorizeException();
        }
    }
}
