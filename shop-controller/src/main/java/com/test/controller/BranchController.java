package com.test.controller;

import com.test.design.strategy.AgentRoleStrategy;
import com.test.design.strategy.StrategyContext;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className BranchController
 * @description
 * @date 2020/4/3 16:24
 */
public class BranchController {


    public static void main(String[] args) {

        // 获取代理商角色-策略模式
        StrategyContext strategyContext = new StrategyContext(new AgentRoleStrategy());
        strategyContext.execute();


    }
}
