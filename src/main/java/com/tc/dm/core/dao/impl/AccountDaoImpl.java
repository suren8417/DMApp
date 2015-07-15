package com.tc.dm.core.dao.impl;

import com.tc.dm.core.entities.Account;
import com.tc.dm.core.dao.AccountDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by sg40304 on 7/14/15.
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Account createAccount(Account account) {
        em.persist(account);
        return account;
    }
}
