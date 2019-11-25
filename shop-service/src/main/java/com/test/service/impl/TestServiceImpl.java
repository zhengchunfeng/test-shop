package com.test.service.impl;

import com.test.bean.constant.NumberConstant;
import com.test.bean.constant.RedisKeyConstant;
import com.test.kafka.KafkaConfigProducer;
import com.test.mapper.TestUMapper;
import com.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

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

        return "欢迎您访问";
    }

}
