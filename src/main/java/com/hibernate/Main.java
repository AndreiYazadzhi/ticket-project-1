package com.hibernate;

import com.hibernate.lib.Injector;
import com.hibernate.model.User;
import com.hibernate.service.AuthenticationService;
import com.hibernate.util.HashUtil;

public class Main {
    private static Injector injector = Injector.getInstance("com.hibernate");
    private static final AuthenticationService authService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);

    public static void main(String[] args) {
        User newUser = authService.register("test@gmail.com", "1234");
        byte[] salt = newUser.getSalt();
        String pass = newUser.getPassword();
        String hash = HashUtil.hashPassword("1234", salt);
        User actualUser = authService.login("test@gmail.com", "1234");
        System.out.println(actualUser);
    }
}
