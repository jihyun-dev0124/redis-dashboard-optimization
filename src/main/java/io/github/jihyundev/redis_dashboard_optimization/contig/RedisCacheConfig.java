package io.github.jihyundev.redis_dashboard_optimization.contig;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration defaultCacheConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair
                                        .fromSerializer(new GenericJackson2JsonRedisSerializer())
                        )
                        .entryTtl(Duration.ofDays(2));// 기본 TTL: 2일

        //캐시별 TTL 설정
        Map<String, RedisCacheConfiguration> cacheConfigs = new HashMap<>();

        //회원 대시보드 캐시
        cacheConfigs.put("membersDashboard", defaultCacheConfig);

        //매출 대시보드 캐시
        cacheConfigs.put("salesDashboard", defaultCacheConfig);

        /*// 예: 어떤 리스트 캐시는 30초만 - override 가능
        cacheConfigs.put(
                "hotItems",
                defaultCacheConfig.entryTtl(Duration.ofSeconds(30))
        );*/

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultCacheConfig)
                .withInitialCacheConfigurations(cacheConfigs)
                .build();

    }

    /**
     * Redis 캐시 작업 (get/put/evict/clear) 중 예외 발생 시
     * 서비스 로직까지 터지지 않게 잡아주는 핸들러
     * @return
     */
    @Bean
    public CacheErrorHandler cacheErrorHandler() {
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                logWarn("GET", exception, cache, key);
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                logWarn("PUT", exception, cache, key);
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                logWarn("EVICT", exception, cache, key);
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                logWarn("CLEAR", exception, cache, null);
            }

            public void logWarn(String operation, RuntimeException exception, Cache cache, Object key) {
                System.err.printf("[Redis cache %s Error] cache=%s, key=%s\n, reason=%s%n", operation, cache != null ? cache.getName() : "unknown", key, exception.getMessage());
            }
        };
    }


}
