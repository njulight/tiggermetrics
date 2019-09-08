package com.tiggermetrics.statistics.service.impl;

import com.tiggermetrics.statistics.client.AccountClient;
import com.tiggermetrics.statistics.model.Account;
import com.tiggermetrics.statistics.model.AccountView;
import com.tiggermetrics.statistics.model.Saving;
import com.tiggermetrics.statistics.service.inf.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private AccountClient accountClient;

    @Override
    public AccountView getCurrentAccount(String uuid) {
        Account account = accountClient.getCurrentAccount(uuid);
        AccountView accountView = new AccountView();
        accountView.setUuid(account.getUuid());
        accountView.setName(account.getName());
        accountView.setLastSeen(account.getLastSeen());
        accountView.setNote(account.getNote());
        List<Saving> savings = account.getSaving();
        if (!CollectionUtils.isEmpty(savings)) {
            accountView.setSaving(savings.get(0));
        }
        return accountView;
    }
}
