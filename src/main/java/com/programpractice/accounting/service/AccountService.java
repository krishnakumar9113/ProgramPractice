package com.programpractice.accounting.service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programpractice.accounting.pojo.Account;
import com.programpractice.accounting.pojo.AccountOpening;
import com.programpractice.accounting.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public AccountOpening createNewAccount(AccountOpening account) {
		account.setOpeningDate(LocalDateTime.now(Clock.systemUTC()));
		account.setBalance(BigDecimal.valueOf(0.00));
		return accountRepository.createAccount(account);
	}

	public List<Account> getAllAccountBalance() {
		return accountRepository.getAllAccountsBalance();
	}

	public boolean deleteAccountById(Long identification) {
		return accountRepository.deleteAccount(identification);
	}
}
