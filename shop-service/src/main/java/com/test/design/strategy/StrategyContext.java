package com.test.design.strategy;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className StrategyContext
 * @description 策略模式上下文
 * @date 2020/4/20 15:24
 */
public class StrategyContext {


    private RoleStrategy roleStrategy;

    public StrategyContext(RoleStrategy roleStrategy) {
        this.roleStrategy = roleStrategy;
    }

    public void execute(){
        roleStrategy.getRoleName();
    }

}
