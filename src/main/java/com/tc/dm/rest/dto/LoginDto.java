package com.tc.dm.rest.dto;

import java.util.List;

/**
 * Created by sg40304 on 11/13/15.
 */
public class LoginDto {

    private String userType;
    private List<String> privilegeTasks;

    public String getUserType() {
        return userType;
    }

    public List<String> getPrivilegeTasks() {
        return privilegeTasks;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setPrivilegeTasks(List<String> privilegeTasks) {
        this.privilegeTasks = privilegeTasks;
    }
}
