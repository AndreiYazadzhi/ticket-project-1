package com.hibernate.security;

import com.hibernate.model.User;
import com.hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email).get();
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User
                .withUsername(email);
        userBuilder.username(user.getEmail());
        userBuilder.password(user.getPassword());
        userBuilder.authorities(user.getRoles()
                .stream().map(r -> r.getRoleName().name()).toArray(String[]::new));
        return userBuilder.build();
    }
}
