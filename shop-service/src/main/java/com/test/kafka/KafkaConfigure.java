package com.test.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;


/**
 * @description Kafka配置类
 * @author zhengchunfeng
 * @date 2019/11/19 17:15
 * @return
 **/
@Slf4j
@Configuration
@EnableKafka
@AllArgsConstructor
@EnableConfigurationProperties({KafkaProperties.class})
public class KafkaConfigure {

	private final KafkaProperties kafkaProperties;

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		// 分区为3
		factory.setConcurrency(3);
		factory.setBatchListener(true);
		factory.getContainerProperties().setPollTimeout(3000);
		return factory;

	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> ackContainerFactory() {

		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		// 手动提交位移
		factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);
		factory.setConcurrency(3);
		return factory;

	}
}
