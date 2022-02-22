package com.programpractice.accounting.service;

import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programpractice.accounting.pojo.Account;
import com.programpractice.accounting.pojo.AccountTransaction;
import com.programpractice.accounting.repository.AccountRepository;
import com.programpractice.accounting.repository.AccountTransactionsRepository;
import com.programpractice.accounting.utils.UtilConstants.TransactionStates;
import com.programpractice.accounting.utils.UtilConstants.TransactionType;

@Service
public class AccountTransactionService {

	@Autowired
	AccountTransactionsRepository transactionRepository;

	@Autowired
	AccountRepository accountRepository;

	public AccountTransaction updateTransaction(Account account, AccountTransaction accountTransaction) {

		if (TransactionType.CRDT.equals(accountTransaction.getTxnType())) {
			account.setBalance(account.getBalance().add(accountTransaction.getTxnAmount()));
		}
		if (TransactionType.DBIT.equals(accountTransaction.getTxnType())) {
			account.setBalance(account.getBalance().subtract(accountTransaction.getTxnAmount()));
		}
		if (TransactionType.EMPTY.equals(accountTransaction.getTxnType())) {
			// do Nothing
		}
		accountTransaction.setAccount(account);
		return accountTransaction;
	}

	@Transactional(rollbackFor = HibernateException.class)
	public TransactionStates createTransaction(AccountTransaction transaction, long identification) {
		if (transactionRepository.checkValidTransaction(transaction.getIkey())) {
			Optional<Account> accountOptional = accountRepository.getAccountById(identification);
			if (accountOptional.isPresent()) {
				Account accountObj = accountOptional.get();
				AccountTransaction accountTransaction = updateTransaction(accountObj, transaction);
				if (accountRepository.updateAccount(accountObj)
						&& transactionRepository.saveOrUpdate(accountTransaction)) {
					return TransactionStates.CREATED;
				}

			} else {
				return TransactionStates.INVALID_ACCOUNT;
			}
		} else {
			return TransactionStates.DUPLICATE_KEY;
		}
		return TransactionStates.FAILED;
	}
}
