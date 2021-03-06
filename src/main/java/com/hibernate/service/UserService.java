package com.hibernate.service;

import com.hibernate.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User add(User user);

    Optional<User> getByEmail(String email);

    User get(Long id);

    List<User> getAll();
}
