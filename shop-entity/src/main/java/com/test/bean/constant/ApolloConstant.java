package com.test.bean.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className ApolloConstant
 * @description 读取Apollo配置文件信息
 * @date 2019/12/3 19:32
 */
@Component
public class ApolloConstant {

    /**
     * @description 备注
     * @author zhengchunfeng
     * @date 2019/12/4 11:35
     * @param null 1
     * @return
     **/
    @Value("${test.notes}")
    private String testNotes;

    /**
     * @description 获取备注
     * @author zhengchunfeng
     * @date 2019/12/4 11:36
     * @param  1
     * @return java.lang.String
     **/
    public String getTestNotes(){
        return testNotes;
    }


}
