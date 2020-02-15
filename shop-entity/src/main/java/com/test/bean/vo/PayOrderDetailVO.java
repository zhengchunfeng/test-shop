package com.test.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className PayOrderDetailVO
 * @description 支付订单详情
 * @date 2020/2/13 20:48
 */
@Data
@ApiModel("PayOrderDetailVO")
public class PayOrderDetailVO {


    @ApiModelProperty(name = "orderNo", value = "订单号")
    private String orderNo;

    @ApiModelProperty(name = "time", value = "时间")
    private String time;
}
