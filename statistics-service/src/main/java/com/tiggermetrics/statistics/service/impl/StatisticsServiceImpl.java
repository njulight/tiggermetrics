package com.tiggermetrics.statistics.service.impl;

import com.tiggermetrics.statistics.client.AccountClient;
import com.tiggermetrics.statistics.config.Rate;
import com.tiggermetrics.statistics.model.Account;
import com.tiggermetrics.statistics.model.AccountView;
import com.tiggermetrics.statistics.model.Currency;
import com.tiggermetrics.statistics.model.Saving;
import com.tiggermetrics.statistics.service.inf.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private Rate rate;

    @Override
    public AccountView getCurrentAccount(String uuid) {
        Account account = accountClient.getCurrentAccount(uuid);
        AccountView accountView = new AccountView();
        accountView.setUuid(account.getUuid());
        accountView.setName(account.getName());
        accountView.setLastSeen(account.getLastSeen());
        accountView.setNote(account.getNote());
        Saving saving = new Saving();
        saving.setCurrency(Currency.RMB);
        BigDecimal total = new BigDecimal(0);
        List<Saving> savings = account.getSaving();
        if (!CollectionUtils.isEmpty(savings)) {
            for (Saving savingCur : savings) {
                total = calcSaving(total, savingCur);
            }
        }
        saving.setAmount(total);
        accountView.setSaving(saving);
        return accountView;
    }

    private BigDecimal calcSaving(BigDecimal total, Saving savingCur) {
        if (Currency.RMB.equals(savingCur.getCurrency())) {
            return total.add(savingCur.getAmount());
        } else if (Currency.USD.equals(savingCur.getCurrency())) {
            BigDecimal rmb = savingCur.getAmount().multiply(BigDecimal.valueOf(Double.parseDouble(rate.getUsdToRmb())));
            return total.add(rmb);
        } else if (Currency.EUR.equals(savingCur.getCurrency())) {
            BigDecimal rmb = savingCur.getAmount().multiply(BigDecimal.valueOf(Double.parseDouble(rate.getEurToRmb())));
            return total.add(rmb);
        }
        return new BigDecimal(0);
    }
}
