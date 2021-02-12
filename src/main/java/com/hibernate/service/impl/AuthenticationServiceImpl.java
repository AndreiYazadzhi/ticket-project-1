package com.hibernate.service.impl;

import com.hibernate.exception.AuthenticationException;
import com.hibernate.model.User;
import com.hibernate.service.AuthenticationService;
import com.hibernate.service.ShoppingCartService;
import com.hibernate.service.UserService;
import com.hibernate.util.HashUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User register(String email, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser = userService.add(newUser);
        shoppingCartService.registerNewShoppingCart(newUser);
        return newUser;
    }

    @Override
    public User login(String email, String password) {
        Optional<User> user = userService.getByEmail(email);
        if (user.isPresent() && user.get().getPassword()
                .equals(HashUtil.hashPassword(password, user.get().getSalt()))) {
            return user.get();
        }
        throw new AuthenticationException("Can`t authenticate user with email:" + email);
    }
}
