package com.test.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * @description redis的序列化载配置
 * @author zhengchunfeng
 * @date 2019/11/25 11:39
 * @return
 **/
@Configuration
public class RedisConfig {
    
	/**
	 * @description 序列化使用的jdkSerializeable, 存储二进制字节码, 所以自定义序列化类
	 * @author zhengchunfeng
	 * @date 2019/11/25 11:39
	 * @param redisConnectionFactory 1
	 * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
	 **/
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
            ObjectMapper om = new ObjectMapper();
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            jackson2JsonRedisSerializer.setObjectMapper(om);
            RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
            template.setConnectionFactory(redisConnectionFactory);
            template.setKeySerializer(new StringRedisSerializer());
            template.setValueSerializer(jackson2JsonRedisSerializer);
            template.setHashKeySerializer(jackson2JsonRedisSerializer);
            template.setHashValueSerializer(jackson2JsonRedisSerializer);
            template.afterPropertiesSet();
            return template;

    }
}
