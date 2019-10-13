package com.tiggermetrics.accoutservice.service.inf;

import com.tiggermetrics.accoutservice.model.Account;

import java.util.List;

public interface AccountService {

	/**
	 * Finds account by given name
	 *
	 * @param uuid
	 * @return found account
	 */
	Account findByUuid(String uuid);

	List<Account> queryAccouts();

	/**
	 * Checks if account with the same name already exists
	 * Invokes Auth Service user creation
	 * Creates new account with default parameters
	 *
	 * @param account
	 * @return created account
	 */
	Account create(Account account);

	/**
	 * Validates and applies incoming account updates
	 * Invokes Statistics Service update
	 *
	 * @param update
	 */
	void saveChanges(Account update) throws Exception;
}
