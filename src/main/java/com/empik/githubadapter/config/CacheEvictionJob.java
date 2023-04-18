package com.empik.githubadapter.config;

import com.empik.githubadapter.empik.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.empik.githubadapter.empik.service.CacheService.GITHUB_USERS_CACHE;

@Component
@RequiredArgsConstructor
@Slf4j
class CacheEvictionJob {

    private final CacheService cacheService;

    @Scheduled(fixedRate = 60000, initialDelay = 60000)
    public void evictGithubCache() {
        log.info("Czyszczenie cache");

        cacheService.clearCache(GITHUB_USERS_CACHE);
    }
}
