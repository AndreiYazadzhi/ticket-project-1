package com.hibernate.service.impl;

import com.hibernate.dao.UserDao;
import com.hibernate.lib.Inject;
import com.hibernate.lib.Service;
import com.hibernate.model.User;
import com.hibernate.service.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email).get();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
