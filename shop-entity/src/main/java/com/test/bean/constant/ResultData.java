package com.test.bean.constant;

import lombok.Data;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className ResultData
 * @description 返回结果
 * @date 2020/7/14 15:53
 */
@Data
public class ResultData<T> {

    private String code;

    private String message;

    private T data;

    public ResultData(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }





}
