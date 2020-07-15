package com.test.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className A
 * @description 角色-权限配置,比如ROLE_ADMIN
 * @date 2020/7/13 17:39
 */
@Data
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }
}
