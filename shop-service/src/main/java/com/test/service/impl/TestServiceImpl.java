package com.test.service.impl;

import com.test.kafka.KafkaConfigProducer;
import com.test.mapper.TestUMapper;
import com.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public String getApi(){

        kafkaConfigProducer.sendKafkaMessage("prebuy-20191121-01", "我是一条kafka消息");
        return "我是父子工程Demo";
    }

}
