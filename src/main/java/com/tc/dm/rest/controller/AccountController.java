package com.tc.dm.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.dm.core.entities.User;
import com.tc.dm.core.services.UserService;
import com.tc.dm.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
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
            userService.createUser(userDto.toUser());
            List<UserDto> userDtos = userDto.toUserDtos(userService.findAll());
            return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.CREATED);
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

}
