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
	
	@Transactional(rollbackFor = HibernateException.class)
	public TransactionStates createTransaction (AccountTransaction transaction, long identification) {
		 if(transactionRepository.checkValidTransaction(transaction.getIkey())) {
			 	if(accountRepository.getAccountById(identification).isPresent()) {	
			 		Account account=accountRepository.getAccountById(identification).get();
			 		 if(TransactionType.CRDT.equals(transaction.getTxnType())) {
		 	        	 account.setBalance(account.getBalance().add(transaction.getTxnAmount()));
		 	         }
		 	         if(TransactionType.DBIT.equals(transaction.getTxnType())) {
		 	        	 account.setBalance(account.getBalance().subtract(transaction.getTxnAmount()));
		 	         }
		 	         if(TransactionType.EMPTY.equals(transaction.getTxnType())) {
		 	        	//do Nothing
		 	         }
		 	         transaction.setAccount(account);
		 	       if( accountRepository.updateAccount(account) && transactionRepository.saveOrUpdate(transaction)) {
		 	    	  return TransactionStates.CREATED;
		 	       }
		 	    
		 }else {
 		    return TransactionStates.INVALID_ACCOUNT;
 	  }
	}else {
		    return TransactionStates.DUPLICATE_KEY;
	  }
return TransactionStates.FAILED;
}
}
