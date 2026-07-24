package com.dxc.dxconlinebanking.transaction;

public class TransactionRejectedException extends RuntimeException {

	public TransactionRejectedException(String message) {
		super(message);
	}
}
