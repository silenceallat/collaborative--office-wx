package com.example.emos.wx.config;

import com.example.emos.wx.exception.EmosException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * name: ExceptionAdice
 * Describe: 全局异常精简处理，用于捕获 springmvc 框架产生的全局异常。
 * user: song
 * Date: 2021/12/4
 **/

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String ValidExceptionHandler(Exception e){
        log.error("执行异常",e);
        if (e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            return exception.getBindingResult().getFieldError().getDefaultMessage();
        }
        else if (e instanceof EmosException){
            EmosException exception = (EmosException) e;
            return exception.getMsg();
        }
        else if (e instanceof UnauthenticatedException){
            return "你不具备相关权限";
        }
        else {
            return "后端执行异常";
        }
    }
}
