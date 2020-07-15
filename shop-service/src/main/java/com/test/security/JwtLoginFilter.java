package com.test.security;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bean.constant.ResultData;
import feign.Response;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className JwtLoginFilter
 * @description Jwt登录过滤器
 * @date 2020/7/13 17:38
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JwtLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }


    /**
     * @description 登录时需要验证时候调用
     * @author zhengchunfeng
     * @date 2020/7/14 16:02
     * @param httpServletRequest 1
     * @param httpServletResponse 2
     * @return org.springframework.security.core.Authentication
     **/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        // 将请求参数 映射到用户信息中
        UserBaseInfo userBaseInfo = new ObjectMapper().readValue(httpServletRequest.getInputStream(), UserBaseInfo.class);

        // 验证码验证
        // 返回一个验证令牌
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        userBaseInfo.getUsername(),
                        userBaseInfo.getPassword()
                )
        );
    }

    /**
     * @description 登录成功后，生成用户jwtToken
     * @author zhengchunfeng
     * @date 2020/7/14 16:01
     * @param req 1
     * @param res 2
     * @param chain 3
     * @param authentication 4
     * @return void
     **/
    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication authentication) throws IOException, ServletException {

        // 为用户生成jwtToken
        AuthTokenUtils.addAuthentication(res, authentication);
    }



    /**
     * @description 验证失败后调用
     * @author zhengchunfeng
     * @date 2020/7/14 16:02
     * @param request 1
     * @param response 2
     * @param failed 3
     * @return void
     **/
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setContentType("application/json");
        ResultData<String> resultData = new ResultData<String>("403", "认证失败", "");
        response.getOutputStream().println(JSON.toJSONString(resultData));
    }

}
