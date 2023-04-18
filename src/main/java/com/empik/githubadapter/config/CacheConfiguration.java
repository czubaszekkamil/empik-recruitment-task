package com.empik.githubadapter.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.empik.githubadapter.empik.service.CacheService.GITHUB_USERS_CACHE;

@EnableCaching
@Configuration
class CacheConfiguration {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(GITHUB_USERS_CACHE);
    }
}
