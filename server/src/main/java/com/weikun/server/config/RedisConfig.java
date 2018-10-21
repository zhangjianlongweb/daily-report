package com.weikun.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 创建者：weikun【YST】   日期：2018/10/21
 * 说说功能：
 */
@Configuration
public class RedisConfig {
    @Bean
    public <T> RedisTemplate<String,T> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate redisTemplate=new RedisTemplate();

        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;


    }

    @Bean
    public <T> RedisTemplate<String, T> redisTemplate1(RedisConnectionFactory collectionFactoryBean){
        RedisTemplate template=new RedisTemplate();
        template.setConnectionFactory(collectionFactoryBean);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;

    }


}
