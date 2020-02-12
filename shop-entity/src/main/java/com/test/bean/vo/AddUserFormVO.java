package com.test.bean.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className AddUserFormVO
 * @description 注册用户VO
 * @date 2020/2/12 19:55
 */
@Data
@ApiModel(value = "AddUserFormVO")
public class AddUserFormVO {

    /**
     * @description 用户实际姓名
     * @author zhengchunfeng
     * @date 2020/2/12 19:56
     * @param null 1
     * @return
     **/
    @Length(min = 2, max = 4, message = "用户姓名最多4个汉字!")
    @NotNull(message = "用户实际姓名不能为空")
    @ApiModelProperty(name = "userTrueName", value = "用户实际姓名")
    private String userTrueName;
}
