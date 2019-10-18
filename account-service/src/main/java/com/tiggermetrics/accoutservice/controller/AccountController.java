package com.tiggermetrics.accoutservice.controller;

import com.tiggermetrics.accoutservice.model.Account;
import com.tiggermetrics.accoutservice.service.inf.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
	public Account getCurrentAccount(@PathVariable String uuid) {
		return accountService.findByUuid(uuid);
	}

	@RequestMapping(path = "/{uuid}", method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable String uuid) {
		accountService.deleteAccount(uuid);
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public List<Account> getAllAccount() {
		return accountService.queryAccouts();
	}

	@RequestMapping(path = "/", method = RequestMethod.PUT)
	public void saveCurrentAccount(@Valid @RequestBody Account account) throws Exception {
		accountService.saveChanges(account);
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public Account createNewAccount(@Valid @RequestBody Account account) {
		return accountService.create(account);
	}
}
