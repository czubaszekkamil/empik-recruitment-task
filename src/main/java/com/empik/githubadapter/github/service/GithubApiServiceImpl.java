package com.empik.githubadapter.github.service;

import com.empik.githubadapter.github.dto.GitHubUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Component
@RequiredArgsConstructor
@Slf4j
class GithubApiServiceImpl implements GithubApiService {

    private final RestTemplate restTemplate;

    @Value("${github.url}")
    private String githubUrl;

    @Cacheable("github-users")
    @Override
    public Optional<GitHubUserDto> findByLogin(String login) {
        String resultUrl = prepareUrl(login);

        try {
            ResponseEntity<GitHubUserDto> response = restTemplate.getForEntity(resultUrl, GitHubUserDto.class);
            return Optional.ofNullable(response.getBody());
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().isSameCodeAs(NOT_FOUND)) {
                log.info("Nie znaleziono użytkownika z loginem: {}", login);
            } else {
                log.error(exception.getMessage(), exception);
            }
        }

        return Optional.empty();
    }

    private String prepareUrl(String login) {
        return githubUrl.concat(login);
    }
}
