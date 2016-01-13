package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.GenericDaoJpaImpl;
import com.tc.dm.core.dao.impl.UserDaoImpl;
import com.tc.dm.core.entities.User;
import com.tc.dm.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    //@Autowired
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserDaoImpl userDao;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.create(user);
    }

    @Override
    public void deleteUser(User user) {
        user= userDao.find(User.class,user.getId());
        userDao.delete(user);
    }

    @Override
    public User updateUser(User user) {
        if(!user.getPassword().equals(userDao.find(User.class, user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
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

    public User authenticate(String user, String password) {
        List<User> matchedUsers = findByName(user);
        if(matchedUsers.size() != 1) return null;
        User authenticatedUser = null;
        if(passwordEncoder.matches(password, matchedUsers.get(0).getPassword())) {
            authenticatedUser = matchedUsers.get(0);
        }
        return authenticatedUser;
    }
}
