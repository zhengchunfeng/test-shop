package com.test.controller;

import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.test.bean.constant.ApolloConstant;
import com.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className TestController
 * @description
 * @date 2019/10/9 10:44
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private ApolloConstant apolloConstant;


    @GetMapping("/api")
    public String getApi(){

        return testService.getApi();
    }

    @GetMapping("/apollo")
    public String getApollo(){
        return apolloConstant.getTestNotes();
    }
}
