package com.empik.githubadapter.empik.controller;

import com.empik.githubadapter.empik.dto.UserDto;
import com.empik.githubadapter.empik.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{login}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable String login) {
        Optional<UserDto> userDtoOpt = userService.findByLogin(login);

        return userDtoOpt.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                         .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
