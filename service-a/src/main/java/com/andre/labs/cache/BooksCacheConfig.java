package com.andre.labs.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;

@EnableCaching
@Configuration
public class BooksCacheConfig {

    private RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }

    @Bean("booksCacheManager")
    public CacheManager cacheManager() {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))
                .disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(config).build();
    }

    @Bean("booksCacheKeyGen")
    public KeyGenerator booksCacheKeyGen() {
        return (target, method, params) ->
                target.getClass().getSimpleName() + "_" +
                    method.getName() + "_" +
                    Arrays.stream(params)
                        .map(p -> String.valueOf(p.hashCode()))
                        .collect(Collectors.joining(","));
    }

}
