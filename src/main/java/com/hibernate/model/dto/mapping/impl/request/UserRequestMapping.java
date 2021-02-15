package com.hibernate.model.dto.mapping.impl.request;

import com.hibernate.model.User;
import com.hibernate.model.dto.mapping.DtoRequestMapper;
import com.hibernate.model.dto.request.UserRequestDto;

public class UserRequestMapping implements DtoRequestMapper<UserRequestDto, User> {
    @Override
    public User fromDto(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
