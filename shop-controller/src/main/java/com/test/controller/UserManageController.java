package com.test.controller;

import com.test.service.TestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhengchunfeng
 * @version 1.0
 * @className UserManageController
 * @description
 * @date 2019/12/4 19:06
 */
@Api(tags = "UserManageController")
@RestController
@RequestMapping("/user")
public class UserManageController {

    @Autowired
    private TestService testService;


    /**
     * @description 用户签到
     * @author zhengchunfeng
     * @date 2019/12/4 19:08
     * @return java.lang.String
     **/
    @GetMapping("/signIn")
    public String userSignIn(){

        testService.userSignIn();
        return "CyclicBarrier";
    }
}
