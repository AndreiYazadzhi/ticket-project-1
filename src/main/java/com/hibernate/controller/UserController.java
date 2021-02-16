package com.hibernate.controller;

import com.hibernate.model.User;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.response.UserResponseDto;
import com.hibernate.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final DtoResponseMapper<UserResponseDto, User> responseMapper;
    private final UserService userService;

    public UserController(DtoResponseMapper<UserResponseDto, User> responseMapper,
                          UserService userService) {
        this.responseMapper = responseMapper;
        this.userService = userService;
    }

    @GetMapping
    public UserResponseDto getByEmail(@RequestParam String email) {
        return responseMapper.toDto(userService.getByEmail(email).get());
    }
}
