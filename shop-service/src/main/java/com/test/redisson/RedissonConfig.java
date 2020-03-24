package com.test.redisson;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author zhengchunfeng
 * @version 1.0
 * @className RedissonLockConfig
 * @description Redisson分布式锁配置
 * @date 2020/3/23 21:27
 */
@Configuration
public class RedissonConfig {


    /**
     * @return org.redisson.api.RedissonClient
     * @description redisson配置
     * @author zhengchunfeng
     * @date 2020/3/24 12:32
     **/
    @Bean
    public RedissonClient redissonClient() {

        Config config = new Config();
        // 目前配置为redis配置，选择0号库。对于集群配置只需要修改一下即可
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("123456").setDatabase(0);
        return Redisson.create(config);
    }
}
