package com.empik.githubadapter.github.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GitHubUserDto {

    private Integer id;
    private String login;
    private String name;
    private String type;
    private String avatar_url;
    private String created_at;
    private Integer followers;
    private Integer public_repos;
}

