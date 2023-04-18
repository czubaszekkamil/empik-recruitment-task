package com.empik.githubadapter.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
class CacheEvictionJob {

    private final CacheManager cacheManager;

    @Scheduled(fixedRate = 60000, initialDelay = 60000)
    public void evictAllCaches() {
        log.info("Czyszczenie cache");

        cacheManager.getCacheNames()
                    .forEach(cacheName -> evictCache(Optional.ofNullable(cacheManager.getCache(cacheName))));
    }

    private void evictCache(Optional<Cache> cacheOpt) {
        cacheOpt.ifPresent(Cache::clear);
    }
}
