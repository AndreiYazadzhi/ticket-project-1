package com.hibernate.service.impl;

import com.hibernate.exception.AuthenticationException;
import com.hibernate.lib.Service;
import com.hibernate.model.User;
import com.hibernate.service.AuthenticationService;
import com.hibernate.service.ShoppingCardService;
import com.hibernate.service.UserService;
import com.hibernate.util.HashUtil;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserService userService = new UserServiceImpl();
    private ShoppingCardService shoppingCardService = new ShoppingCardServiceImpl();

    @Override
    public User register(String email, String password) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser = userService.add(newUser);
        shoppingCardService.registerNewShoppingCart(newUser);
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
