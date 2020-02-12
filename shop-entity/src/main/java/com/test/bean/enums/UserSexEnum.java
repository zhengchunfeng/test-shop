package com.test.bean.enums;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className UserSexEnum
 * @description 用户性别枚举
 * @date 2020/2/12 20:05
 */
public enum UserSexEnum {

    /**
     * @description 0- 未知
     * @author zhengchunfeng
     * @date 2020/2/12 20:06
     * @param null 1
     * @return
     **/
    UNKOWN("0", "未知"),

    /**
     * @description 1-男
     * @author zhengchunfeng
     * @date 2020/2/12 20:06
     * @param null 1
     * @return
     **/
    MAN("1", "男"),

    /**
     * @description 2-女
     * @author zhengchunfeng
     * @date 2020/2/12 20:07
     * @param null 1
     * @return
     **/
    WOMEN("2", "女");

    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    UserSexEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
