package com.test;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className Application
 * @description 启动类
 * @date 2019/10/9 10:44
 */
@EnableApolloConfig
@ServletComponentScan
@SpringBootApplication
@EnableDiscoveryClient
public class Application {

    public static void main(String[] args) {
        //new SpringApplicationBuilder(Application.class).web(true).run(args);
        SpringApplication.run(Application.class, args);
    }


}
