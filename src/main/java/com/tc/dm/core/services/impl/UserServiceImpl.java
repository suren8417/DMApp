package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.GenericDaoJpaImpl;
import com.tc.dm.core.dao.impl.UserDaoImpl;
import com.tc.dm.core.entities.User;
import com.tc.dm.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDaoImpl userDao;

    @Override
    public User createUser(User user) {

        return userDao.create(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
