package com.yang.jk.shiro;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @auther yhjStart
 * @create 2022-04-01 13:36
 */
@Data
public class Token implements AuthenticationToken {
    private String token;
    public  Token(String token) {
        this.token = token;
    }
    @Override
    public Object  getPrincipal() {
        return token;
    }

    @Override
    public Object  getCredentials() {
        return token;
    }
}
