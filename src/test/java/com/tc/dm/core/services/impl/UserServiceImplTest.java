package com.tc.dm.core.services.impl;

import com.tc.dm.core.entities.User;
import com.tc.dm.core.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/business-config.xml")
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testUserServices() throws Exception {
        User user = User.getInstance("mathot", "123", 1L);
        user = this.userService.createUser(user);
        Assert.notNull(user.getId(), "no id created");

        user.setRoleId(2L);
        user = this.userService.updateUser(user);
        Assert.isTrue(((Long) 2L).equals(user.getRoleId()), "Not updated");

        boolean isUserFound = false;
        for (User aUser : this.userService.findAll()){
            if(aUser.getId().equals(user.getId())){
                isUserFound = true;
                break;
            }
        }
        Assert.isTrue(isUserFound, "Created user not return for findAll");

        Long lastUserId = user.getId();
        this.userService.deleteUser(user);
        isUserFound = false;
        for (User aUser : this.userService.findAll()){
            if(aUser.getId().equals(lastUserId)){
                isUserFound = true;
                break;
            }
        }
        Assert.isTrue(!isUserFound, "Deleted user return for findAll");
    }
}