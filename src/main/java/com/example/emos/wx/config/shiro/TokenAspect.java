package com.example.emos.wx.config.shiro;

import com.example.emos.wx.common.util.R;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * name: TokenAspect
 * Describe:
 * user: song
 * Date: 2021/12/4
 **/

@Aspect
@Component
public class TokenAspect {
    @Autowired
    private ThreadLocalToken threadLocalToken;

    @Pointcut("execution(public * com.example.emos.wx.controller.*.*(..)))")
    public void aspect(){

    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        R r = (R) point.proceed();
        String token = threadLocalToken.getToken();
        //如果ThreadLocalToken中存在Token，说明是更新的Token
        if (token !=null){
            r.put("token",token);
            threadLocalToken.clear();
        }
        return r;
    }
}
