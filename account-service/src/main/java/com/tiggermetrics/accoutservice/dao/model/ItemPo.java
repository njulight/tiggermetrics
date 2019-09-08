package com.tiggermetrics.accoutservice.dao.model;

import com.tiggermetrics.accoutservice.model.Currency;
import com.tiggermetrics.accoutservice.model.Item;
import com.tiggermetrics.accoutservice.model.ItemType;

import java.math.BigDecimal;
import java.util.Date;

public class ItemPo extends AbsPo<Item> {

    private String uuid;

    private String accountId;

    private String title;

    private BigDecimal amount;

    private int currency;

    private Date itemTime;

    private String icon;

    private int incomesOrExpenses;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Date getItemTime() {
        return itemTime;
    }

    public void setItemTime(Date itemTime) {
        this.itemTime = itemTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIncomesOrExpenses() {
        return incomesOrExpenses;
    }

    public void setIncomesOrExpenses(int incomesOrExpenses) {
        this.incomesOrExpenses = incomesOrExpenses;
    }

    public ItemPo() {

    }

    public ItemPo(Item item, String accountId, ItemType itemType) {
        this.uuid = item.getUuid();
        this.accountId = accountId;
        this.amount = item.getAmount();
        this.currency = item.getCurrency() == null ? Currency.getDefault().getValue() : item.getCurrency().getValue();
        this.icon = item.getIcon();
        this.itemTime = item.getItemTime();
        this.title = item.getTitle();
        this.incomesOrExpenses = itemType == null ? ItemType.getDefault().getValue() : itemType.getValue();
    }

    @Override
    public Item toMo() {
        Item item = new Item();
        item.setUuid(this.uuid);
        item.setAmount(this.amount);
        item.setCurrency(Currency.parseByValue(this.currency));
        item.setIcon(this.icon);
        item.setItemTime(this.itemTime);
        item.setTitle(this.title);
        item.setItemType(ItemType.parseByValue(this.incomesOrExpenses));
        return item;
    }
}
