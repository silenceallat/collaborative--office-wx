package com.example.emos.wx.config.shiro;

import org.springframework.stereotype.Component;

/*
 * @ClassName:ThreadLocal
 * @author: song
 * @date:2021/12/1
 */

@Component
public class ThreadLocalToken {

    private ThreadLocal local = new ThreadLocal();

    public void setToken(String token) {
        local.set(token);
    }

    public String getToken() {
        return (String) local.get();
    }

    public void clear() {
        local.remove();
    }
}
