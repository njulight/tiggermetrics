package com.tiggermetrics.accoutservice.dao.model;

import com.tiggermetrics.accoutservice.model.Currency;
import com.tiggermetrics.accoutservice.model.Saving;

import java.math.BigDecimal;

public class SavingPo extends AbsPo<Saving> {

    private String uuid;

    private String accountId;

    private BigDecimal amount;

    private int currency;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public SavingPo() {

    }

    public SavingPo(Saving saving, String accountId) {
        this.uuid = saving.getUuid();
        this.accountId = accountId;
        this.amount = saving.getAmount();
        this.currency = saving.getCurrency() == null ? Currency.getDefault().getValue() : saving.getCurrency().getValue();
    }

    @Override
    public Saving toMo() {
        Saving saving = new Saving();
        saving.setUuid(this.uuid);
        saving.setAmount(this.amount);
        saving.setCurrency(Currency.parseByValue(this.currency));
        return saving;
    }
}
