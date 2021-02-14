package com.hibernate.service.impl;

import com.hibernate.dao.UserDao;
import com.hibernate.model.User;
import com.hibernate.service.UserService;
import com.hibernate.util.HashUtil;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        String hashedPassword = HashUtil.hashPassword(user.getPassword(), salt);
        user.setPassword(hashedPassword);
        return userDao.add(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
