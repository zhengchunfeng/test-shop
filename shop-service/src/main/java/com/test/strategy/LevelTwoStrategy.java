package com.test.strategy;

import java.math.BigDecimal;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className LevelTwoStrategy
 * @description
 * @date 2020/5/21 15:59
 */
public class LevelTwoStrategy implements LevelInterface {


    @Override
    public BigDecimal getLevelPrice() {
        return new BigDecimal("0.2");
    }
}
