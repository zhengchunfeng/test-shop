package com.test.service.impl;

import com.test.Thread.UserCountDownThread;
import com.test.bean.constant.NumberConstant;
import com.test.bean.constant.RedisKeyConstant;
import com.test.kafka.KafkaConfigProducer;
import com.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className TestServiceImpl
 * @description 业务层
 * @date 2019/10/9 14:36
 */
@Service
public class TestServiceImpl implements TestService {


    @Autowired
    private KafkaConfigProducer kafkaConfigProducer;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @description 定义线程池(核心线程数10, 最大线程数20, KeepAliveTime60s)
     * 饱和策略：默认是直接abort
     * @author zhengchunfeng
     * @date 2019/11/29 19:21
     * @param null 1
     * @return
     **/
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(NumberConstant.NUMBER_TEN_INTEGER, NumberConstant.NUMBER_TWENTY_INTEGER, NumberConstant.NUMBER_SIXTY_INTEGER, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());


    /**
     * @description 测试方法
     * @author zhengchunfeng
     * @date 2019/11/25 10:37
     * @param  1
     * @return java.lang.String
     **/
    @Override
    public String getApi(){

        // 1.发送一条消息
        kafkaConfigProducer.sendKafkaMessage("prebuy-20191121-01", "我是一条kafka消息");

        // 2.累计加1
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String testKey = RedisKeyConstant.TEST_API_INCR + LocalDate.now().getYear();
        valueOperations.increment(testKey, NumberConstant.NUMBER_ONE_INTEGER);

        // 3.定义同步类会阻塞主线程执行
        CountDownLatch countDownLatch = new CountDownLatch(NumberConstant.NUMBER_TEN_INTEGER);
        for(int i = 0; i < 10; i ++){
            THREAD_POOL_EXECUTOR.execute(new UserCountDownThread(i, countDownLatch));
        }

        valueOperations.set("1", "2", 60, TimeUnit.SECONDS);

        return "欢迎您访问";
    }

}
