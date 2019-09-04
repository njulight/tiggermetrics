package com.tiggermetrics.accoutservice.dao.impl;

import com.tiggermetrics.accoutservice.dao.inf.AccountDao;
import com.tiggermetrics.accoutservice.dao.mapper.AccountMapper;
import com.tiggermetrics.accoutservice.dao.model.AccountPo;
import com.tiggermetrics.accoutservice.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private AccountMapper accountMapper;


    @Override
    public void addAccount(Account account) {
        accountMapper.insert(new AccountPo(account));
    }
}
