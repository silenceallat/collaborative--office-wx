package com.example.emos.wx.config.shiro;/*
 * @ClassName:OAuth2Token
 * @author: song
 * @date:2021/12/1
 */

import org.apache.shiro.authc.AuthenticationToken;

public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
