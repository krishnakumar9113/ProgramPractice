package com.programpractice.accounting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.programpractice.accounting.pojo.Account;
import com.programpractice.accounting.pojo.AccountOpening;
import com.programpractice.accounting.repository.AccountRepository;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class AccountServiceTest {

	@Mock
	AccountRepository accountRepository ;
	
	@InjectMocks
	AccountService accountService;
	
	@Test
	void testNewAccount() {
			long expectedvalue=123456;
			AccountOpening accountOpening = new AccountOpening();
			accountOpening.setBsb(123456);
			Mockito.when(accountRepository.createAccount(accountOpening)).thenReturn(accountOpening);
			
			assertEquals(expectedvalue,accountService.createNewAccount(accountOpening).getBsb());
	}
	
	@Test
	void testGetAllAccount() {
	
			Account account1 = new Account();
			account1.setBsb(123456);
			Account account2 = new Account();
			account2.setBsb(123456);
			List<Account> accOpeningList= new ArrayList<Account>();
			accOpeningList.add(account1);
			accOpeningList.add(account2);
			
			Mockito.when(accountRepository.getAllAccountsBalance()).thenReturn(accOpeningList);
			assertEquals(2,accountService.getAllAccountBalance().size());
	}
	


}
