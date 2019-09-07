package com.tiggermetrics.accoutservice.dao.inf;

import com.tiggermetrics.accoutservice.model.Account;

public interface AccountDao {

    void addAccount(Account account);

    Account queryAccount(String uuid);

    void updateAccount(Account account);
}
