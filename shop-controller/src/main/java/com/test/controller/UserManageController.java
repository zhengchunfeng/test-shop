package com.test.controller;

import com.test.bean.vo.AddUserFormVO;
import com.test.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author zhengchunfeng
 * @version 1.0
 * @className UserManageController
 * @description 用户测试类控制层
 * @date 2019/12/4 19:06
 */
@Api(tags = "UserManageController")
@RestController
@RequestMapping("/user")
public class UserManageController {

    @Autowired
    private TestService testService;


    /**
     * @description 添加用户
     * @author zhengchunfeng
     * @date 2020/2/12 20:19
     * @return java.lang.String
     **/
    @PostMapping("/add")
    @ApiOperation(value = "添加用户")
    public String addUser(@Valid AddUserFormVO addUserFormVO){
        testService.userSignIn();
        return testService.getDefaultValue();
    }


    /**
     * @description semaphore测试
     * @author zhengchunfeng
     * @date 2019/12/4 19:08
     * @return java.lang.String
     **/
    @GetMapping("/semaphoreTest")
    public void semaphoreTest(){
        testService.semaphoreTest();
    }


    @GetMapping("/strategy")
    public void strategy(){
        testService.strategy();
    }
}
