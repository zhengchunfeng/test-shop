/**
 * 功能描述：用于构建Restful API
 */
package com.test.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description swagger2配置
 * @author zhengchunfeng
 * @date 2019/12/4 17:33
 * @param null 1
 * @return
 **/
@Configuration
@EnableSwagger2
public class Swagger2 {
     
	@Bean
	 public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.controller"))//指定扫码包下文件
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot使用Swagger2构建RestfulAPI")
                .description("Hello SpringBoot")
                .termsOfServiceUrl("http://localhost/")
                .build();
    }
	
}
