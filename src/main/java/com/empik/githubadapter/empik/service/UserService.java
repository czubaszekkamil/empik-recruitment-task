package com.empik.githubadapter.empik.service;

import com.empik.githubadapter.empik.dto.UserDto;
import com.empik.githubadapter.github.dto.GitHubUserDto;
import com.empik.githubadapter.github.service.GithubApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final GithubApiService githubApiService;
    private final RequestService requestService;

    public Optional<UserDto> findByLogin(String login) {
        requestService.refreshCounterFor(login);

        Optional<GitHubUserDto> githubUserOpt = githubApiService.findByLogin(login);
        return githubUserOpt.map(UserDtoMapper::mapFrom);
    }
}
