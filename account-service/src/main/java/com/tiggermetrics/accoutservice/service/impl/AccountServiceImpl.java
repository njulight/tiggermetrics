package com.tiggermetrics.accoutservice.service.impl;

import com.tiggermetrics.accoutservice.dao.inf.AccountDao;
import com.tiggermetrics.accoutservice.model.Account;
import com.tiggermetrics.accoutservice.service.inf.AccountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account findByUuid(String uuid) {
        return accountDao.queryAccount(uuid);
    }

    @Override
    public Account create(Account account) {
        if (StringUtils.isEmpty(account.getUuid())) {
            account.setUuid(UUID.randomUUID().toString());
        }
        accountDao.addAccount(account);
        return account;
    }

    @Override
    public void saveChanges(Account update) throws Exception {
        Account account = accountDao.queryAccount(update.getUuid());
        if (account == null) {
            throw new Exception("account is not exist");
        }
        accountDao.updateAccount(update);
    }
}
