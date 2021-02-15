package com.hibernate.dao;

import com.hibernate.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> getByEmail(String email);

    Optional<User> get(Long id);

    List<User> getAll();
}
