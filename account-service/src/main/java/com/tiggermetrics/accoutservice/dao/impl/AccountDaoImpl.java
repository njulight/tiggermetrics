package com.tiggermetrics.accoutservice.dao.impl;

import com.tiggermetrics.accoutservice.dao.inf.AccountDao;
import com.tiggermetrics.accoutservice.dao.inf.ItemDao;
import com.tiggermetrics.accoutservice.dao.inf.SavingDao;
import com.tiggermetrics.accoutservice.dao.mapper.AccountMapper;
import com.tiggermetrics.accoutservice.dao.model.AccountPo;
import com.tiggermetrics.accoutservice.model.*;
import com.tiggermetrics.accoutservice.model.Currency;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private SavingDao savingDao;


    @Override
    public void insertAccount(Account account) {
        if (StringUtils.isEmpty(account.getUuid())) {
            account.setUuid(UUID.randomUUID().toString());
        }
        accountMapper.insert(new AccountPo(account));
    }

    @Override
    public Account queryAccount(String uuid) {
        AccountPo accountPo = accountMapper.selectByPrimaryKey(uuid);
        if (accountPo != null) {
            Account account = accountPo.toMo();
            List<Item> items = itemDao.queryByAccountId(accountPo.getUuid());
            Map<ItemType, List<Item>> itemTypeListMap = items.stream().collect(Collectors.groupingBy(item -> item.getItemType()));
            if (itemTypeListMap.get(ItemType.INCOMES) != null) {
                account.setIncomes(itemTypeListMap.get(ItemType.INCOMES));
            }
            if (itemTypeListMap.get(ItemType.EXPENSES) != null) {
                account.setExpenses(itemTypeListMap.get(ItemType.EXPENSES));
            }
            List<Saving> savings = savingDao.queryByAccountId(accountPo.getUuid());
            if (!CollectionUtils.isEmpty(savings)) {
                account.setSaving(savings);
            }
            return account;
        }
        return null;
    }

    @Override
    public List<Account> queryAllAccounts() {
        List<AccountPo> accountPos = accountMapper.selectAll();
        return accountPos.stream().map(item -> item.toMo()).collect(Collectors.toList());
    }

    @Override
    public void updateAccount(Account account) {
        accountMapper.update(new AccountPo(account));
        String accoutId = account.getUuid();
        List<Item> incomes = account.getIncomes();
        itemDao.insertItem(incomes, accoutId, ItemType.INCOMES);
        List<Item> expenses = account.getExpenses();
        itemDao.insertItem(expenses, accoutId, ItemType.EXPENSES);
        List<Item> itemsDB = itemDao.queryByAccountId(accoutId);
        Map<Currency, Saving> currencySavingMap = calcSaving(itemsDB);
        List<Saving> savingsDB = savingDao.queryByAccountId(accoutId);
        List<Saving> insertSavings = new ArrayList<>();
        List<Saving> updateSavings = new ArrayList<>();
        for (Map.Entry<Currency, Saving> entry : currencySavingMap.entrySet()) {
            Saving saving = entry.getValue();
            Saving savingDB = findExistCurrencySaving(saving, savingsDB);
            if (savingDB != null) {
                saving.setUuid(savingDB.getUuid());
                updateSavings.add(saving);
            } else {
                insertSavings.add(saving);
            }
        }
        savingDao.insertSaving(insertSavings, accoutId);
        savingDao.updateSaving(updateSavings, accoutId);
    }

    private Saving findExistCurrencySaving(Saving saving, List<Saving> savingsDB) {
        List<Saving> savings = savingsDB.stream()
                .filter(item -> saving.getCurrency() != null && saving.getCurrency().equals(item.getCurrency()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(savings)) {
            return null;
        }
        return savings.get(0);
    }

    private Map<Currency, Saving> calcSaving(List<Item> itemsBD) {
        Map<Currency, List<Item>> itemsBDGroupByCurrency = itemsBD.stream().collect(Collectors.groupingBy(item -> item.getCurrency()));
        Map<Currency, Saving> currencySavingMap = new HashMap<>();
        for (Map.Entry<Currency, List<Item>> entry : itemsBDGroupByCurrency.entrySet()) {
            Currency currency = entry.getKey();
            List<Item> items = entry.getValue();
            BigDecimal bigDecimal = new BigDecimal(0);
            for (Item item : items) {
                if (ItemType.INCOMES.equals(item.getItemType())) {
                    bigDecimal = bigDecimal.add(item.getAmount());
                }
                if (ItemType.EXPENSES.equals(item.getItemType())) {
                    bigDecimal = bigDecimal.subtract(item.getAmount());
                }
            }
            Saving saving = new Saving();
            saving.setCurrency(currency);
            saving.setAmount(bigDecimal);
            currencySavingMap.put(currency, saving);
        }
        return currencySavingMap;
    }
}
