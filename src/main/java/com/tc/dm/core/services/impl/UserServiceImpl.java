package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.GenericDaoJpaImpl;
import com.tc.dm.core.dao.impl.UserDaoImpl;
import com.tc.dm.core.entities.User;
import com.tc.dm.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        userDao.delete(findById(user.getId()));
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }

    @Override
    public User findById(Long userId) {
        return userDao.find(User.class, userId);
    }

    @Override
    public List<User> findByName(String userName) {
        List<User> matchedUsers = new ArrayList<User>();
        for(User user : findAll()){
            if(user.getName().equals(userName)) {
                matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
