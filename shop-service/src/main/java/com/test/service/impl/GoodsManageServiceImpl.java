package com.test.service.impl;

import com.test.service.GoodsManageService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className GoodsManageServiceImpl
 * @description 商品管理
 * @date 2020/3/24 12:39
 */
@Slf4j
@Service
public class GoodsManageServiceImpl implements GoodsManageService {



    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * @description 秒杀商品
     * @author zhengchunfeng
     * @date 2020/3/24 12:45
     * @return void
     **/
    @Override
    public void skillBuyGoods() {

        log.info("秒杀商品开始");
        String lockKey = "lockKey";
        // 获取分布式锁
        RLock rLock = redissonClient.getLock(lockKey);
        try {
            // 获取锁等待时间 30s
            rLock.tryLock(30, TimeUnit.SECONDS);
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            Integer goodsCount = (Integer) valueOperations.get("shop:goods:count");
            log.info("剩余商品数量[{}]", goodsCount);
            if (goodsCount > 0) {
                // 扣减库存
                goodsCount = goodsCount - 1;
                valueOperations.set("shop:goods:count", goodsCount);
                // 具体逻辑
            } else {
                log.info("[===========>商品库存为0]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            rLock.unlock();
        }


    }
}
