package com.tc.dm.rest.dto;

import com.tc.dm.core.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private String name;
    private String password;
    private Long roleId;
    private String roleName;
    private Long id;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User toUser() {
        return User.getInstance(id,name, password, roleId,roleName);
    }

    public List<UserDto> toUserDtos(List<User> users) {
        List<UserDto> list = new ArrayList<UserDto>();
        for(User user : users){
            UserDto userDto = new UserDto();
            userDto.setName(user.getName());
            userDto.setPassword(user.getPassword());
            userDto.setId(user.getId());
            userDto.setRoleId(user.getRole().getId());
            userDto.setRoleName(user.getRole().getName());
            list.add(userDto);
        }
        return list;
    }
}
