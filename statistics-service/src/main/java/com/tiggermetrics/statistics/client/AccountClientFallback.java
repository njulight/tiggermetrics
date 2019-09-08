package com.tiggermetrics.statistics.client;

import com.tiggermetrics.statistics.model.Account;
import com.tiggermetrics.statistics.model.Currency;
import com.tiggermetrics.statistics.model.Saving;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

@Component
public class AccountClientFallback {

    public Account getCurrentAccount(String uuid) {
        Account account = new Account();
        account.setUuid(uuid);
        account.setName("unknow");
        Saving saving = new Saving();
        saving.setAmount(new BigDecimal(0));
        saving.setCurrency(Currency.RMB);
        account.setSaving(Collections.singletonList(saving));
        return account;
    }
}
