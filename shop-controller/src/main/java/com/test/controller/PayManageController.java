package com.test.controller;

import com.test.feign.TestClientFeign;
import com.test.bean.constant.NumberConstant;
import com.test.bean.vo.PayOrderDetailVO;
import com.test.service.GoodsManageService;
import com.test.util.CommonDateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description 支付管理
 * @author zhengchunfeng
 * @date 2020/2/12 18:21
 * @return
 **/
@Slf4j
@Api(tags = "支付管理接口API")
@RestController
@RequestMapping(value = "/api")
public class PayManageController {

    @Autowired
    TestClientFeign testClientFeign;

    @Autowired
    GoodsManageService goodsManageService;

    /**
     * @description 查询支付订单详情
     * @author zhengchunfeng
     * @date 2020/2/13 19:39
     * @param orderId 1
     * @return java.lang.String
     **/
    @ApiOperation(value = "查询支付订单详情")
    @GetMapping("/get/order/{orderId}")
    public PayOrderDetailVO getOrderDetailById(@PathVariable(value = "orderId") Long orderId) {

        log.info("查询支付订单详情orderId=[{}]", orderId);
        orderId = orderId == null ? NumberConstant.NUMBER_ONE_INTEGER.longValue() : orderId;
        PayOrderDetailVO payOrderDetailVO = new PayOrderDetailVO();
        payOrderDetailVO.setOrderNo("123");
        payOrderDetailVO.setTime(CommonDateUtils.getYYYY_MM_DD_HH_MM_SS());

        String str = testClientFeign.test();
        System.out.println(str);
        return payOrderDetailVO;
    }

    /**
     * @description 秒杀商品
     * @author zhengchunfeng
     * @date 2020/3/24 17:31
     * @param  1
     * @return void
     **/
    @GetMapping("/skill/buy/goods")
    public void skillBuyGoods(){
        goodsManageService.skillBuyGoods();
    }
}
