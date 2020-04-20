package com.test.design.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className SellerRoleStrategy
 * @description 经销商角色
 * @date 2020/4/20 15:22
 */
@Slf4j
public class SellerRoleStrategy implements RoleStrategy {

    /**
     * @description 获取角色名称
     * @author zhengchunfeng
     * @date 2020/4/20 15:25
     * @param  1
     * @return void
     **/
    @Override
    public void getRoleName() {
       log.info("策略模式-经销商角色");
    }
}
