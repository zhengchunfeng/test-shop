package com.test.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className WebSecurityConfigure
 * @description 程序启动加载，身份认证规则
 * @date 2020/7/13 17:21
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 开放访问的请求
     */
    private final static String[] PERMIT_ALL_MAPPING = {
            "/swagger-ui.html","/webjars/**","/v2/**","/swagger-resources/**"
    };


    /**
     * 跨域配置
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        // 允许跨域访问的 URL
        List<String> allowedOriginsUrl = new ArrayList<>();
        allowedOriginsUrl.add("http://localhost:8080");
        allowedOriginsUrl.add("http://127.0.0.1:8080");
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        // 设置允许跨域访问的 URL
        config.setAllowedOrigins(allowedOriginsUrl);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;

    }


    /**
     * @description 配置请求认证规则，程序启动时加载
     * @author zhengchunfeng
     * @date 2020/7/14 15:27
     * @param http 1
     * @return void
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // permitAll()表示不需要认证,所匹配的URL任何人都允许访问。
        // anyRequest() 表示匹配所有的请求。一般情况下此方法都会使用，设置全部内容都需要进行认证。
        // antMatchers() 表示要放行的资源，比如访问公有图片等。

        // 关闭csrf验证
        http.csrf().disable()

                // 下面开始配置权限
           .authorizeRequests()
                // 1.PERMIT_ALL_MAPPING请求放行
                .antMatchers(PERMIT_ALL_MAPPING).permitAll()
                // 2.login 的POST请求都放行
                .antMatchers(HttpMethod.POST, "/login").permitAll()

                // 3.只有角色ROLE_ADMIN,ROLE_USER才能访问
                .antMatchers("/order").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")

                // 4.除上面外的剩下其余所有请求需要权限身份认证,必须登录后才能访问
                .anyRequest().authenticated()

                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JwtLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)

                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * @description 使用自定义身份验证组件
     * @author zhengchunfeng
     * @date 2020/7/14 15:34 
     * @param auth 1
     * @return void
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        // 使用自定义身份验证组件
        auth.authenticationProvider(new UserAuthProvider());

    }
}
