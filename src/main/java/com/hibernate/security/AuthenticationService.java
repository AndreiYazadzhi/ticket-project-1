package com.hibernate.security;

import com.hibernate.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
