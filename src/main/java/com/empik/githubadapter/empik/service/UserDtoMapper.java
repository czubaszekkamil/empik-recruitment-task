package com.empik.githubadapter.empik.service;

import com.empik.githubadapter.empik.dto.Calculations;
import com.empik.githubadapter.empik.dto.UserDto;
import com.empik.githubadapter.github.dto.GitHubUserDto;

class UserDtoMapper {

    static UserDto mapFrom(GitHubUserDto gitHubUserDto) {
        return UserDto.of(gitHubUserDto.getId(),
                          gitHubUserDto.getLogin(),
                          gitHubUserDto.getName(),
                          gitHubUserDto.getType(),
                          gitHubUserDto.getAvatar_url(),
                          gitHubUserDto.getCreated_at(),
                          Calculations.of(gitHubUserDto.getFollowers(),
                                          gitHubUserDto.getPublic_repos())
                                      .getResult()
        );
    }
}
