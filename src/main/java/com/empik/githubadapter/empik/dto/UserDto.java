package com.empik.githubadapter.empik.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class UserDto {

    Integer id;
    String login;
    String name;
    String type;
    String avatarUrl;
    String createdAt;
    Double calculations;
}
