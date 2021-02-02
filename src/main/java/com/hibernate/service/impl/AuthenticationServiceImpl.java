package com.hibernate.service.impl;

import com.hibernate.exception.AuthenticationException;
import com.hibernate.lib.Service;
import com.hibernate.model.User;
import com.hibernate.service.AuthenticationService;
import com.hibernate.service.UserService;
import com.hibernate.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserService userService = new UserServiceImpl();

    @Override
    public User register(String email, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        return userService.add(newUser);
    }

    @Override
    public User login(String email, String password) {
        User user = userService.getByEmail(email);
        String hashedPassword = HashUtil.hashPassword(password, user.getSalt());
        if (hashedPassword.equals(user.getPassword())) {
            return user;
        }
        throw new AuthenticationException("Can`t authenticate user with email:" + email);
    }
}
