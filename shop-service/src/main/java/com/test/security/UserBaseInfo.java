package com.test.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className UserBaseInfo
 * @description 用户基础信息
 * @date 2020/7/13 17:24
 */
@Data
public class UserBaseInfo implements UserDetails {

    // 用户名
    private String username;

    // 密码
    private String password;

    // 验证码
    private String verifyCode;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
