package com.test.service;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className TestService
 * @description
 * @date 2019/10/9 14:36
 */
public interface TestService {

     String getApi();

     /**
      * @description 用户签到
      * @author zhengchunfeng
      * @date 2019/12/4 19:11
      * @param  1
      * @return void
      **/
     void userSignIn();

     /**
      * @description 默认方法
      * @author zhengchunfeng
      * @date 2019/12/9 14:11
      * @return java.lang.String
      **/
     default String getDefaultValue(){
          return "我是默认方法";
     }


     void semaphoreTest();
}
