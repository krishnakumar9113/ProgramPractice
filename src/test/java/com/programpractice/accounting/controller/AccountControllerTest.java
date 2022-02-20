package com.programpractice.accounting.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.programpractice.accounting.pojo.AccountOpening;
import com.programpractice.accounting.repository.AccountRepository;
import com.programpractice.accounting.service.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
    @Mock
    AccountRepository  accountRepsoitory;
     

	@MockBean
	private AccountService service;
	
    @Test
    public void testCreateAccount() 
    {
		AccountOpening accountOpening = new AccountOpening();
		accountOpening.setBsb(123456);
    	when(service.createNewAccount( Mockito.any(AccountOpening.class))).thenReturn(accountOpening);
		try {
			mockMvc.perform( MockMvcRequestBuilders
				      .post("/account/process-account-opening")
				      .content(" { \"bsb\":\"123456\" }")
				      .contentType(MediaType.APPLICATION_JSON)
				      .accept(MediaType.APPLICATION_JSON))	
				      .andExpect(status().isCreated())
				      .andExpect(MockMvcResultMatchers.jsonPath("$.identification").exists());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			}
			 
    
}
