package com.hibernate.service.impl;

import com.hibernate.dao.UserDao;
import com.hibernate.dao.impl.UserDaoImpl;
import com.hibernate.lib.Inject;
import com.hibernate.lib.Service;
import com.hibernate.model.User;
import com.hibernate.service.UserService;
import com.hibernate.util.HashUtil;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User add(User user) {
        byte[] salt = HashUtil.getSalt();
        user.setSalt(salt);
        String hashedPassword = HashUtil.hashPassword(user.getPassword(), salt);
        user.setPassword(hashedPassword);
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
