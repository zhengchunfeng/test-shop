package com.test.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className UserAuthProvider
 * @description 登录时校验用户密码验证
 * @date 2020/7/13 17:33
 */
public class UserAuthProvider implements AuthenticationProvider {

    // 这个类就是提供密码验证功能，在实际使用时换成自己相应的验证逻辑，从数据库中取出、比对、赋予用户相应权限。

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("Bad credentials");
        }

        // 获取认证的用户名和密码
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (userName.equals("admin") && password.equals("123456")) {

            // 这里设置权限和角色，实际可从数据库中获取
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
            authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
            // 生成令牌
            Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, authorities);
            return auth;
        }else {
            throw new BadCredentialsException("登录密码错误");
        }
    }


    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }



}
