package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.AccountDao;
import com.tc.dm.core.entities.Account;
import com.tc.dm.core.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sg40304 on 7/10/15.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountDao accountDao;

    @Override
    public Account createAccount(Account account) {

        return accountDao.createAccount(account);
    }
}
