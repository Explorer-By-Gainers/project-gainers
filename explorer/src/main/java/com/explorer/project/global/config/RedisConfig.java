package com.explorer.project.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    private LettuceConnectionFactory createReactiveConnectionFactory(String host, int port) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
        return new LettuceConnectionFactory(config);
    }

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return createReactiveConnectionFactory(host, port);
    }

    @Bean
    public ReactiveRedisTemplate<String, Object> reactiveRedisTemplate() {
        return new ReactiveRedisTemplate<>(
                reactiveRedisConnectionFactory(),
                RedisSerializationContext.<String, Object>newSerializationContext(new StringRedisSerializer())
                        .value(new GenericJackson2JsonRedisSerializer())
                        .build());
    }

}
