package com.tiggermetrics.accoutservice.dao.inf;

import com.tiggermetrics.accoutservice.model.Account;

import java.util.List;

public interface AccountDao {

    void insertAccount(Account account);

    Account queryAccount(String uuid);

    List<Account> queryAllAccounts();

    void updateAccount(Account account);
}
