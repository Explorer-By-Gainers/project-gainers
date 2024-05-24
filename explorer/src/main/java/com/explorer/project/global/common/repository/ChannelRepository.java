package com.explorer.project.global.common.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class ChannelRepository {

    private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;

    private static final String KEY_PREFIX = "channel:";

    public Mono<Long> add(String channelId, Long id) {
        String key = KEY_PREFIX + channelId;
        String value = String.valueOf(id);
        return reactiveRedisTemplate.opsForSet().add(key, value);
    }

    public Mono<Long> remove(String channelId, Long id) {
        String key = KEY_PREFIX + channelId;
        String value = String.valueOf(id);
        return reactiveRedisTemplate.opsForSet().remove(key, value);
    }

}
