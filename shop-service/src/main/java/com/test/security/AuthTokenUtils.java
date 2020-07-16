package com.test.security;

import com.alibaba.fastjson.JSON;
import com.test.bean.constant.ResultData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className AuthTokenUtils
 * @description JWT的工具类
 * @date 2020/7/13 17:28
 */
@Slf4j
public class AuthTokenUtils {

    // jwt过期时间2h 单位：ms
    private static final Long JWT_EXPIRE_TIME = 2 * 60 * 60 * 1000L;

    // refreshToken 过期时间7天 单位：ms
    private static final Long REFRESH_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;

    // 生成JWT秘钥的签名秘钥(注意是生成签名的秘钥，并不是生成JWT的秘钥)
    private static final String SECRET = "secret";

    // Token前缀
    private static final String TOKEN_PREFIX = "Bearer";

    // 存放Token的Header Key
    private static final String HEADER_STRING = "Authorization";

    /**
     * @description 生成用户JWT
     * @author zhengchunfeng
     * @date 2020/7/14 15:38
     * @param response 1
     * @param authentication 2
     * @return void
     **/
    public static void addAuthentication(HttpServletResponse response, Authentication authentication) {

        // 1.获取用户角色
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        StringBuffer stringBuffer = new StringBuffer();
        authorities.forEach(authority ->{
            stringBuffer.append(authority.getAuthority()).append(",");
        });

        // 生成JWT
        String jwtToken = Jwts.builder()
                // 保存权限（角色）
                .claim("authorities", stringBuffer)
                // 用户名写入标题
                .setSubject(authentication.getName())
                // 有效期设置 ,一旦jwtToken过期，便会抛出io.jsonwebtoken.ExpiredJwtException异常
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRE_TIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        log.info("userName=[{}]的jwtToken为[{}]", authentication.getName(), jwtToken);

        // 解密后
        // header: {"alg":"HS512"}
        // body:{"authorities":"ROLE_ADMIN,AUTH_WRITE","sub":"admin","exp":1595146541}


        // 将JWT写入body, 服务端无须保存jwtToken
        try {
            response.setContentType("application/json");
            // 如果需要其它用户信息，可在此处返回
            ResultData<String> resultData = new ResultData<String>("200", "success", jwtToken);
            String resultStr = JSON.toJSONString(resultData);
            response.getOutputStream().println(resultStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    /**
     * @description 每次请求，都需要验证JWT
     * @author zhengchunfeng
     * @date 2020/7/14 15:43
     * @param request 1
     * @return org.springframework.security.core.Authentication
     **/
    public static Authentication getAuthentication(HttpServletRequest request) {

        // 从Header中拿到token
        String jwtToken = request.getHeader(HEADER_STRING);

        if (StringUtils.isBlank(jwtToken)) {
            log.info("从Header中获取用户Authorization为空, 403无权限");
            return null;
        }

        // 解析 Token
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    // 验签（防止数据被篡改）
                    .setSigningKey(SECRET)
                    // 去掉 Bearer
                    .parseClaimsJws(jwtToken.replace(TOKEN_PREFIX, ""))
                    .getBody();
        } catch (ExpiredJwtException e){
            // 说明jwtToken失效，获取refreshToken判断其是否失效，如果都失效，则提示用户重新登录
            log.info("当前jwtToken失效，将使用refreshToken换取新jwtToken[{}]", jwtToken);
            // Todo 具体逻辑待完善

            return null;
        } catch (Exception e){
            e.printStackTrace();
            // 其它异常，直接返回
            return null;
        }

        // 获取用户名
        String userName = claims.getSubject();

        // 得到 权限（角色）
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));

        // 返回验证令牌
        return userName != null ?
                new UsernamePasswordAuthenticationToken(userName, null, authorities) :
                null;
    }
}
