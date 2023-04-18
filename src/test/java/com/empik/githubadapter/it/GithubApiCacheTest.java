package com.empik.githubadapter.it;

import com.empik.githubadapter.empik.service.CacheService;
import com.empik.githubadapter.github.service.GithubApiService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.empik.githubadapter.empik.service.CacheService.GITHUB_USERS_CACHE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

class GithubApiCacheTest extends AbstractTestBase {

    @Autowired
    private GithubApiService githubApiService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CacheService cacheService;
    private MockRestServiceServer mockServer;

    @Value("${github.url}")
    private String githubUrl;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(this.restTemplate);
    }


    @Test
    void shouldUseCacheForSecondCallApi() {
        int expectedCount = 1;
        String randomLogin = RandomStringUtils.randomAlphanumeric(10);
        mockGithubApi(expectedCount, randomLogin);

        githubApiService.findByLogin(randomLogin);
        githubApiService.findByLogin(randomLogin);

        mockServer.verify();
    }

    @Test
    void shouldCallApiAfterClearCache() {
        int expectedCount = 2;
        String randomLogin = RandomStringUtils.randomAlphanumeric(10);
        mockGithubApi(expectedCount, randomLogin);

        githubApiService.findByLogin(randomLogin);

        cacheService.clearCache(GITHUB_USERS_CACHE);
        githubApiService.findByLogin(randomLogin);

        mockServer.verify();
    }

    private String prepareUrl(String login) {
        return githubUrl.concat(login);
    }

    private void mockGithubApi(int expectedCount, String login) {
        mockServer.expect(ExpectedCount.times(expectedCount),
                          requestTo(prepareUrl(login)))
                  .andExpect(method(HttpMethod.GET))
                  .andRespond((response) -> {
                      throw new HttpClientErrorException(NOT_FOUND);
                  });
    }
}
