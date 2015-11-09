package com.tc.dm.rest.dto;

import com.tc.dm.core.entities.User;

public class UserDto {

    private String name;
    private String password;
    private Long roleId;

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public User toUser() {
        return User.getInstance(name, password, roleId);
    }
}
