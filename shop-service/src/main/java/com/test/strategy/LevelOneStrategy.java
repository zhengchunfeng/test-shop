package com.test.strategy;

import java.math.BigDecimal;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className LevelOneStrategy
 * @description 1级会员策略类
 * @date 2020/5/21 15:58
 */
public class LevelOneStrategy implements LevelInterface{


    @Override
    public BigDecimal getLevelPrice() {
        return new BigDecimal("0.1");
    }
}
