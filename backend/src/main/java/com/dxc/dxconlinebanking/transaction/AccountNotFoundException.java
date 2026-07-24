package com.dxc.dxconlinebanking.transaction;

public class AccountNotFoundException extends RuntimeException {

	public AccountNotFoundException(Long accountId) {
		super("No such account with account id '%s'".formatted(accountId));
	}
}
