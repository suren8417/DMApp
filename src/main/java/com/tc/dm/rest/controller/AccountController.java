package com.tc.dm.rest.controller;

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

/**
 * Created by sg40304 on 7/9/15.
 */
@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

    private UserService userService;

    @Autowired
    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDto> createAccount(@RequestBody UserDto userDto) {
        try {
            User createdUser = userService.createUser(userDto.toUser());
            HttpHeaders headers = new HttpHeaders();

            return new ResponseEntity<UserDto>(userDto, headers, HttpStatus.CREATED);
        } catch (Exception exception) {

        }
        return null;
    }


}
