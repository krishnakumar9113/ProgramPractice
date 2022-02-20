package com.programpractice.accounting.service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programpractice.accounting.pojo.Account;
import com.programpractice.accounting.pojo.AccountOpening;
import com.programpractice.accounting.repository.AccountRepository;

@Service
public class AccountService {
	
	
	@Autowired
	private AccountRepository accountRepository;

	
	public AccountOpening createNewAccount(AccountOpening account){
		account.setOpening_date(LocalDateTime.now(Clock.systemUTC()));
		account.setBalance(new BigDecimal(0.00));
		AccountOpening createdAccount = accountRepository.createAccount(account);
		return createdAccount;
	}
	
	public List<Account>getAllAccountBalance() {
		return accountRepository.getAllAccountsBalance();
	}
	
	public Account getAccountById(Long identification) {
		return accountRepository.getAccountById(identification).get();
	}
	public boolean deleteAccountById(Long identification) {
		return accountRepository.deleteAccount(identification);
	}
}
