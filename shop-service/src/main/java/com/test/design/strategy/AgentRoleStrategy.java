package com.test.design.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className AgentRoleStrategy
 * @description 代理商角色
 * @date 2020/4/20 15:23
 */
@Slf4j
public class AgentRoleStrategy implements RoleStrategy {


    /**
     * @description 获取角色名称
     * @author zhengchunfeng
     * @date 2020/4/20 15:24
     * @param  1
     * @return void
     **/
    @Override
    public void getRoleName() {

       log.info("策略模式-代理商角色");

    }
}
