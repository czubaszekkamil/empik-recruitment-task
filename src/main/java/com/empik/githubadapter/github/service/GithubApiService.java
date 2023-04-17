package com.empik.githubadapter.github.service;

import com.empik.githubadapter.github.dto.GitHubUserDto;

import java.util.Optional;

public interface GithubApiService {
    Optional<GitHubUserDto> findByLogin(String login);
}
