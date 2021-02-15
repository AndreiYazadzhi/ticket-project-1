package com.hibernate.service;

import com.hibernate.model.User;
import java.util.List;

public interface UserService {
    User add(User user);

    User getByEmail(String email);

    User get(Long id);

    List<User> getAll();
}
