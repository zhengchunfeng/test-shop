package com.test.controller;

import com.test.security.UserBaseInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className LoginController
 * @description
 * @date 2020/7/13 17:53
 */
@RestController
public class LoginController {




    @PostMapping("/login")
    public void login(UserBaseInfo userBaseInfo){

    }
}
