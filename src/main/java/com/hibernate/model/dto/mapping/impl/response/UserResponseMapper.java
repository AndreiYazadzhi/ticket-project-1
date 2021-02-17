package com.hibernate.model.dto.mapping.impl.response;

import com.hibernate.model.User;
import com.hibernate.model.dto.mapping.DtoResponseMapper;
import com.hibernate.model.dto.response.UserResponseDto;

public class UserResponseMapper implements DtoResponseMapper<UserResponseDto, User> {
    @Override
    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setId(user.getId());
        return userResponseDto;
    }
}
