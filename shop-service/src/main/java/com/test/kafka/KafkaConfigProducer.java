package com.test.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;


/**
 * @description Kafka生产者
 * @author zhengchunfeng
 * @date 2019/11/19 17:15
 * @return
 **/
@Slf4j
@Component
public class KafkaConfigProducer {

	// kafkaTemplate相当于生产者
	@Autowired
	private  KafkaTemplate<String, Object> kafkaTemplate;
	

	/**
	 *版本：V 1.0
	 *功能描述：Kafka发送消息
	 *作者：zhengchunfeng
	 *时间：@date 2019年2月19日  
	 *内容：
	 */
	public void sendKafkaMessage(String topic, String message){
		
		log.info("send message to [{}] ", topic);
		ListenableFuture<SendResult<String, Object>> listenableFuture = kafkaTemplate.send(topic, "unikey-20191", message);
		log.info("返回结果 [{}] ");
		
	}
}
