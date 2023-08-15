package com.opensource.easyddd.infrastructure.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.*;

/**
 * @author wang tengkun
 * @date 2022/5/23
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper,Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key 采用 String 的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash 的 key 也采用 String 的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value 的序列化方式采用 JSON
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash value 的序列化方式也采用 JSON
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


    @Bean
    @Primary
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10));
//                .disableCachingNullValues();

        Set<String> cacheNames = new HashSet<>();
        Map<String, RedisCacheConfiguration> cacheConfig = new HashMap<>();
        for (Map.Entry<String, Duration> entry : initCacheNameMap().entrySet()) {
            cacheNames.add(entry.getKey());
            cacheConfig.put(entry.getKey(), defaultCacheConfig.entryTtl(entry.getValue()));
        }
        return RedisCacheManager.builder(Objects.requireNonNull(redisTemplate.getConnectionFactory()))
                .cacheDefaults(defaultCacheConfig)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(cacheConfig)
                .build();

    }




    private Map<String, Duration> initCacheNameMap() {
        Map<String, Duration> cacheNameMap = new HashMap<>(16);
        cacheNameMap.put("10s", Duration.ofSeconds(10));
        cacheNameMap.put("30s", Duration.ofSeconds(30));
        cacheNameMap.put("1m", Duration.ofMinutes(1));
        cacheNameMap.put("2m", Duration.ofMinutes(2));
        cacheNameMap.put("3m", Duration.ofMinutes(3));
        cacheNameMap.put("4m", Duration.ofMinutes(4));
        cacheNameMap.put("5m", Duration.ofMinutes(5));
        cacheNameMap.put("10m", Duration.ofMinutes(10));
        cacheNameMap.put("30m", Duration.ofMinutes(30));
        cacheNameMap.put("1h", Duration.ofHours(1));
        cacheNameMap.put("2h", Duration.ofHours(2));
        cacheNameMap.put("3h", Duration.ofHours(3));
        cacheNameMap.put("5h", Duration.ofHours(5));
        cacheNameMap.put("10h", Duration.ofHours(10));
        cacheNameMap.put("1d", Duration.ofDays(1));
        return cacheNameMap;
    }
}
