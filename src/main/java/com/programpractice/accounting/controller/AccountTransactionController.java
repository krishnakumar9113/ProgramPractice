package com.programpractice.accounting.controller;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programpractice.accounting.pojo.AccountTransaction;
import com.programpractice.accounting.service.AccountTransactionService;
import com.programpractice.accounting.utils.UtilConstants;
import com.programpractice.accounting.utils.UtilConstants.TransactionStates;

import static com.programpractice.accounting.utils.Utilities.createJsonMessage;

@RestController
@CrossOrigin
@RequestMapping("/transactions")
public class AccountTransactionController {

	@Autowired
	private AccountTransactionService accountTransactionService;

	@PostMapping("/create-transactions/{identification}")
	public ResponseEntity<Object> saveTransactions(@Valid @RequestBody AccountTransaction transaction,
			@PathVariable("identification") long identification, @RequestHeader("Idempotency-Key") String iKey,
			BindingResult bindingResult) {
		// TODO: update with DTO Objects
		try {
			List<FieldError> be = new ArrayList<>();
			if (bindingResult.hasErrors()) {
				be.addAll(bindingResult.getFieldErrors());
			}
			if (!be.isEmpty()) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(be);
			}

			transaction.setIkey(iKey);
			transaction.setTxnDate(LocalDateTime.now(Clock.systemUTC()));
			TransactionStates transactionResult = accountTransactionService.createTransaction(transaction,
					identification);
			if (transactionResult.equals(TransactionStates.CREATED)) {
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(createJsonMessage(UtilConstants.VALID_TRANSACTION_MESSAGE));
			}
			if (transactionResult.equals(TransactionStates.DUPLICATE_KEY)) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(createJsonMessage(UtilConstants.DUPLICATE_IKEY_MESSAGE));
			}
			if (transactionResult.equals(TransactionStates.INVALID_ACCOUNT)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(createJsonMessage(UtilConstants.ACCOUNT_NOT_FOUND));
			}
			if (transactionResult.equals(TransactionStates.FAILED)) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(createJsonMessage(UtilConstants.TRANSACTION_FAILED_MESSAGE));
			}

		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(createJsonMessage(UtilConstants.TRANSACTION_FAILED_MESSAGE));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(createJsonMessage(UtilConstants.TRANSACTION_FAILED_MESSAGE));

	}

}
