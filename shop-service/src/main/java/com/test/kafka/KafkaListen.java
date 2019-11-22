package com.test.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className KafkaListen
 * @description
 * @date 2019/11/19 19:38
 */
@Slf4j
@Component
public class KafkaListen {



    /**
     * @description Kafka消费相应主题topic
     * @author zhengchunfeng
     * @date 2019/11/19 17:27
     * @param message 1
     * @return void
     **/
    @KafkaListener(topics = {"prebuy-20191121-01"})
    public void receiveMessage(String message){

        //收到通道的消息之后执行秒杀操作
        log.info("Kafka开始消费message=[{}]", message);
    }
}
