package com.tc.dm.core.services;

import com.tc.dm.core.entities.User;
import java.util.List;

public interface UserService {


    public User createUser(User user);

    public void deleteUser(User user);

    public User updateUser(User user);

    public User findById(Long userId);

    public List<User> findByName(String userName);

    public List<User> findAll();

    public User authenticate(String user, String password);

}
