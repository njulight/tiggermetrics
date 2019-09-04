package com.tiggermetrics.accoutservice.dao.model;

import com.tiggermetrics.accoutservice.model.Account;

import java.util.Date;

public class AccountPo extends AbsPo<Account>{

	private String uuid;

	private String name;

	private Date lastSeen;

	private String note;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public AccountPo(Account account) {
		this.uuid = account.getUuid();
		this.name = account.getName();
		this.lastSeen = account.getLastSeen();
		this.note = account.getNote();
	}

	@Override
	Account toMo() {
		Account account = new Account();
		account.setUuid(this.uuid);
		account.setName(this.name);
		account.setLastSeen(this.lastSeen);
		account.setNote(this.note);
		return account;
	}
}
