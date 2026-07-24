package com.dxc.dxconlinebanking.transaction;

import java.security.Principal;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class MoneyTransactionController {

	private final MoneyTransactionService transactionService;

	public MoneyTransactionController(MoneyTransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/deposits")
	public ResponseEntity<TransactionResponse> deposit(
			Principal principal, @Valid @RequestBody DepositRequest request) {
		TransactionResponse transaction = transactionService.deposit(principal.getName(), request);
		return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
	}

	@PostMapping("/withdrawals")
	public ResponseEntity<TransactionResponse> withdraw(
			Principal principal, @Valid @RequestBody WithdrawRequest request) {
		TransactionResponse transaction = transactionService.withdraw(principal.getName(), request);
		return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
	}

	@PostMapping("/transfers")
	public ResponseEntity<TransactionResponse> transfer(
			Principal principal, @Valid @RequestBody TransferRequest request) {
		TransactionResponse transaction = transactionService.transfer(principal.getName(), request);
		return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
	}
}
