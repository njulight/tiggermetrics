package com.tiggermetrics.accoutservice.model;

import java.math.BigDecimal;
import java.util.Date;

public class Item {

	private String uuid;

	private String title;

	private BigDecimal amount;

	private Currency currency;

	private Date itemTime;

	private String icon;

	private ItemType itemType;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getItemTime() {
		return itemTime;
	}

	public void setItemTime(Date itemTime) {
		this.itemTime = itemTime;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
}
