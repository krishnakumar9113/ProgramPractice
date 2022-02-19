package com.programpractice.accounting.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.programpractice.accounting.pojo.Account;
import com.programpractice.accounting.pojo.AccountTransaction;
import com.programpractice.accounting.repository.AccountRepository;
import com.programpractice.accounting.repository.AccountTransactionsRepository;
import com.programpractice.accounting.utils.UtilConstants.TransactionStates;
import com.programpractice.accounting.utils.UtilConstants.TransactionType;

@SpringBootTest
@RunWith(SpringRunner.class)
class AccountTransactionServiceTest {

	
	@Mock
	AccountTransactionsRepository transactionRepository ;
	
	@Mock
	AccountRepository accountRepository ;
	
	
	@InjectMocks
	AccountTransactionService accountTransactionService;

	@Test
	void createSuccessfulTransaction() {
		
		 	
		Account account= new Account();
		account.setBsb(1234);
		account.setBalance(new BigDecimal(100));
		
		AccountTransaction accountTransaction =new AccountTransaction();
		accountTransaction.setIkey("abcd");
		accountTransaction.setTxnAmount(new BigDecimal(10));
		accountTransaction.setTxnDate(LocalDateTime.now(Clock.systemUTC()));
		accountTransaction.setTxnType(TransactionType.CRDT);
		accountTransaction.setAccount(account);
		
		
		Mockito.when(accountRepository.getAccountById(anyLong())).thenReturn(Optional.of(account));
		Mockito.when(transactionRepository.checkValidTransaction(Mockito.anyString())).thenReturn(true);
		Mockito.when(accountRepository.updateAccount(account)).thenReturn(true);
		Mockito.when(transactionRepository.saveOrUpdate(accountTransaction)).thenReturn(true);
		
		assertEquals(TransactionStates.CREATED,accountTransactionService.createTransaction(accountTransaction, 1234));
	}

}
