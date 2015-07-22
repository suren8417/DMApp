package com.tc.dm.rest.controller;

import com.tc.dm.core.entities.Account;
import com.tc.dm.core.services.AccountService;
import com.tc.dm.rest.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;

import java.net.URI;

/**
 * Created by sg40304 on 7/9/15.
 */
@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        try {
            Account createdAccount = accountService.createAccount(accountDto.toAccount());
            HttpHeaders headers = new HttpHeaders();

            return new ResponseEntity<AccountDto>(accountDto, headers, HttpStatus.CREATED);
        } catch (Exception exception) {

        }
        return null;
    }


}
