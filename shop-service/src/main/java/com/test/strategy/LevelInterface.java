package com.test.strategy;

import java.math.BigDecimal;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className levelInterface
 * @description 获取不同等级会员的价格策略接口
 * @date 2020/5/21 15:56
 */
public interface LevelInterface {

    /**
     * @description  获取不同等级会员的价格
     * @author zhengchunfeng
     * @date 2020/5/21 15:57
     * @return java.math.BigDecimal
     **/
    BigDecimal getLevelPrice();
}
