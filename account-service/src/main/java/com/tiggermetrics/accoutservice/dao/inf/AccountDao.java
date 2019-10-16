package com.tiggermetrics.accoutservice.dao.inf;

import com.tiggermetrics.accoutservice.model.Account;

import java.util.List;

public interface AccountDao {

    void insertAccount(Account account);

    void deleteAccount(String uuid);

    Account queryAccount(String uuid);

    List<Account> queryAllAccounts();

    void updateAccount(Account account);
}
