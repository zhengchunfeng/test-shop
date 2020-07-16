package com.test.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className UserBaseInfo
 * @description 用户基础信息(非用户实体)
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

    // 权限
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    // 账户是否过期，过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 指定用户是否解锁，锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 指示是否已过期的用户的凭据（密码），过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 是否可用，禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        return true;
    }
}
