package com.empik.githubadapter.it;

import com.empik.githubadapter.empik.dto.UserDto;
import com.empik.githubadapter.empik.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

class RequestTest extends AbstractTestBase {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RequestTestHelper requestTestHelper;

    private MockRestServiceServer mockServer;

    @Value("${github.url}")
    private String githubUrl;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(this.restTemplate);
    }

    @Test
    void shouldFindLoginAndSaveRequestCounter() {
        String randomExistingLogin = RandomStringUtils.randomAlphanumeric(10);
        mockGithubApiForExisting(randomExistingLogin);

        Optional<UserDto> userDtoOpt = userService.findByLogin(randomExistingLogin);

        int counter = requestTestHelper.requestCountFor(randomExistingLogin);

        Assertions.assertTrue(userDtoOpt.isPresent());
        Assertions.assertEquals(1, counter);
    }

    @Test
    void shouldFindLoginAndUpdateRequestCounter() {
        String randomExistingLogin = RandomStringUtils.randomAlphanumeric(10);
        mockGithubApiForExisting(randomExistingLogin);

        Optional<UserDto> userDtoOpt = userService.findByLogin(randomExistingLogin);
        userService.findByLogin(randomExistingLogin);

        int counter = requestTestHelper.requestCountFor(randomExistingLogin);

        Assertions.assertTrue(userDtoOpt.isPresent());
        Assertions.assertEquals(2, counter);
    }

    @Test
    void shouldNotFindLoginAndSaveRequestCounter() {
        String randomNonExistingLogin = RandomStringUtils.randomAlphanumeric(10);
        mockGithubApiForNonExisting(randomNonExistingLogin);

        Optional<UserDto> userDtoOpt = userService.findByLogin(randomNonExistingLogin);

        int counter = requestTestHelper.requestCountFor(randomNonExistingLogin);

        Assertions.assertTrue(userDtoOpt.isEmpty());
        Assertions.assertEquals(1, counter);
    }

    @Test
    void shouldNotFindLoginAndUpdateRequestCounter() {
        String randomNonExistingLogin = RandomStringUtils.randomAlphanumeric(10);
        mockGithubApiForNonExisting(randomNonExistingLogin);

        Optional<UserDto> userDtoOpt = userService.findByLogin(randomNonExistingLogin);
        userService.findByLogin(randomNonExistingLogin);

        int counter = requestTestHelper.requestCountFor(randomNonExistingLogin);

        Assertions.assertTrue(userDtoOpt.isEmpty());
        Assertions.assertEquals(2, counter);
    }

    private void mockGithubApiForExisting(String login) {
        mockServer.expect(ExpectedCount.manyTimes(),
                          requestTo(githubUrl.concat(login)))
                  .andExpect(method(HttpMethod.GET))
                  .andRespond(withStatus(HttpStatus.OK)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .body("{\"login\": \"" + login + "\"}")
                  );
    }

    private void mockGithubApiForNonExisting(String login) {
        mockServer.expect(ExpectedCount.manyTimes(),
                          requestTo(githubUrl.concat(login)))
                  .andExpect(method(HttpMethod.GET))
                  .andRespond((response) -> {
                      throw new HttpClientErrorException(NOT_FOUND);
                  });
    }
}
