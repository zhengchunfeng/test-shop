package com.test.bean.enums;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className UserRoleEnum
 * @description 用户角色枚举
 * @date 2020/3/11 20:41
 */
public enum UserRoleEnum {

    CUSTOMER("1", "消费者"),
    SALLER("2", "经销商"),
    OPERATER("3", "运营商"),
    AGENTER("4", "代理商");

    private String code;
    private String message;
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    UserRoleEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public static UserRoleEnum getUserRole(String code){
        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()){
            if(userRoleEnum.getCode().equals(code)){
                return userRoleEnum;
            }
        }
        return null;
    }

}
