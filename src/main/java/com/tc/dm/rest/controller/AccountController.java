package com.tc.dm.rest.controller;

import com.tc.dm.core.entities.User;
import com.tc.dm.core.services.UserService;
import com.tc.dm.rest.dto.LoginDto;
import com.tc.dm.rest.dto.PrivilegeDto;
import com.tc.dm.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sg40304 on 7/9/15.
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

    private UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity createAccount(@RequestBody UserDto userDto) {
        try {
            List<User> users = userService.findByName(userDto.getName());
            if (users.size() == 0 ) {
                userService.createUser(userDto.toUser());
                List<UserDto> userDtos = userDto.toUserDtos(userService.findAll());
                return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.CREATED);
            }else{
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception exception) {
            throw exception;
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public ResponseEntity updateAccount(@RequestBody UserDto userDto) {
        try {
            userService.updateUser(userDto.toUser());
            List<UserDto> userDtos = userDto.toUserDtos(userService.findAll());
            return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(@RequestParam("deleteUser") Long deleteUserId) {
        try {
            UserDto userDto = new UserDto();
            userDto.setId(deleteUserId);
            userService.deleteUser(userDto.toUser());
            List<UserDto> userDtos = userDto.toUserDtos(userService.findAll());
            return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
        } catch (Exception exception) {
            throw exception;
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAccounts() {
        try {
            UserDto userDto = new UserDto();
            List<UserDto> userDtos = userDto.toUserDtos(userService.findAll());
            return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);
        } catch (Exception exception) {
            throw exception;
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResponseEntity<LoginDto> getAccount(HttpServletRequest request,
                                               @RequestParam("userName") String userName,
                                               @RequestParam("password") String password) {
        try {
            LoginDto loginDto = new LoginDto();
            List<PrivilegeDto> privileges = new ArrayList<PrivilegeDto>();
            List<User> users = userService.findByName(userName);
            User correctUser = userService.authenticate(userName, password);
            //for (User user : users) {
                if (correctUser != null) {
                    //correctUser = user;
                    loginDto.setUserType(correctUser.getRole().getName());

                    PrivilegeDto privilegeDto = new PrivilegeDto();
                    privilegeDto.setDisplayText("Search");
                    privilegeDto.setRout("search");
                    privileges.add(privilegeDto);

                    if ("Administrator".equals(correctUser.getRole().getName()) | "Curator".equals(correctUser.getRole().getName()) | "DataEntry".equals(correctUser.getRole().getName())) {
                        PrivilegeDto privilegeDto1 = new PrivilegeDto();
                        privilegeDto1.setDisplayText("New Item");
                        privilegeDto1.setRout("newItem");
                        privileges.add(privilegeDto1);

                        PrivilegeDto privilegeDto2 = new PrivilegeDto();
                        privilegeDto2.setDisplayText("Manage Collection");
                        privilegeDto2.setRout("manageCollection");
                        privileges.add(privilegeDto2);
                    }
                    if ("Administrator".equals(correctUser.getRole().getName()) | "Curator".equals(correctUser.getRole().getName())) {
                        PrivilegeDto privilegeDto3 = new PrivilegeDto();
                        privilegeDto3.setDisplayText("Validate Item");
                        privilegeDto3.setRout("validateItem");
                        privileges.add(privilegeDto3);
                    }
                    if ("Administrator".equals(correctUser.getRole().getName())) {

                        PrivilegeDto privilegeDto4 = new PrivilegeDto();
                        privilegeDto4.setDisplayText("Users");
                        privilegeDto4.setRout("users");
                        privileges.add(privilegeDto4);
                    }
                    //break;
                    request.getSession().setAttribute("currentUser", correctUser.getName());
                }
            //}

            loginDto.setPrivilegeTasks(privileges);
            return new ResponseEntity<LoginDto>(loginDto, HttpStatus.OK);
        } catch (Exception exception) {
            throw exception;
        }
    }

}
