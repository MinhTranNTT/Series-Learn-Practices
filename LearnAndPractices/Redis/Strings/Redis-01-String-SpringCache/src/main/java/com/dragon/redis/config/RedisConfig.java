package com.dragon.redis.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {

    @Primary
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration
                //configure lifetime, lifetime for cache entries, current every 30 minutes
                .entryTtl(Duration.ofMinutes(30L))
                //do not store null values in cache
                .disableCachingNullValues()

                //config serializer
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
                //设置value序列化器
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));

        //Here is the configuration for RedisCacheManager. It uses RedisCacheWriter to write to Redis.
        //nonLockingRedisCacheWriter() is used to create a non-locking RedisCacheWriter.

        //This is the default configuration for RedisCacheManager. It uses the RedisCacheConfiguration configured in the previous step.

        //This method constructs and returns a configured CacheManager object ready for use in the application.
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

    /**
     * key序列化器
     */
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }
    /**
     * value序列化器
     */
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

}
