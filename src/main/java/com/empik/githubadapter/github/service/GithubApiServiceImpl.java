package com.empik.githubadapter.github.service;

import com.empik.githubadapter.github.dto.GitHubUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
class GithubApiServiceImpl implements GithubApiService {

    private final RestTemplate restTemplate;

    @Value("${github.url}")
    private String githubUrl;

    @Override
    public Optional<GitHubUserDto> findByLogin(String login) {
        String resultUrl = prepareUrl(login);
        ResponseEntity<GitHubUserDto> response = restTemplate.getForEntity(resultUrl, GitHubUserDto.class);

        return Optional.ofNullable(response.getBody());
    }

    private String prepareUrl(String login) {
        return githubUrl.concat(login);
    }
}
