package com.tc.dm.rest.dto;

import java.util.List;

/**
 * Created by sg40304 on 11/13/15.
 */
public class LoginDto {

    private String userType;
    private List<PrivilegeDto> privilegeTasks;

    public String getUserType() {
        return userType;
    }

    public List<PrivilegeDto> getPrivilegeTasks() {
        return privilegeTasks;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setPrivilegeTasks(List<PrivilegeDto> privilegeTasks) {
        this.privilegeTasks = privilegeTasks;
    }
}
