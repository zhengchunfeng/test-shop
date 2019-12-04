package com.test.controller;

import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.test.bean.constant.ApolloConstant;
import com.test.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.service.Tags;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className TestController
 * @description
 * @date 2019/10/9 10:44
 */
@Api(tags = "TestController")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private ApolloConstant apolloConstant;


    /**
     * @description 测试Api接口
     * @author zhengchunfeng
     * @date 2019/12/4 17:38
     * @param  1
     * @return java.lang.String
     **/
    @ApiOperation(value = "测试Api接口")
    @GetMapping("/api")
    public String getApi(){

        return testService.getApi();
    }

    /**
     * @description Apollo测试接口
     * @author zhengchunfeng
     * @date 2019/12/4 17:38
     * @param  1
     * @return java.lang.String
     **/
    @ApiOperation(value = "Apollo测试接口")
    @GetMapping("/apollo")
    public String getApollo(){
        return apolloConstant.getTestNotes();
    }
}
