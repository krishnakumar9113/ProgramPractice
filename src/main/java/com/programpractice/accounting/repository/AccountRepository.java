package com.programpractice.accounting.repository;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.programpractice.accounting.pojo.Account;
import com.programpractice.accounting.pojo.AccountOpening;
import com.programpractice.accounting.pojo.AccountTransaction;
import com.programpractice.accounting.pojo.InterestHistory;
import com.programpractice.accounting.utils.UtilConstants.TransactionType;
import com.programpractice.accounting.utils.Utilities;




@Repository
public class AccountRepository {
	
	@PersistenceContext
    private EntityManager entityManager;


	protected Session getCurrentSession() {
            return entityManager.unwrap(Session.class);
    }
   
    public List<Account> getAllAccountsBalance() {
		// TODO Auto-generated method stub
		Session session =getCurrentSession();
		List<Account> accountsStream = null;
		session.clear();
	      try{
	    	  String query="SELECT b FROM Account b";
	    	  accountsStream=  session.createQuery(query, Account.class).list();
	      }catch (HibernateException e) {
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return accountsStream;
	}

	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		Session session =getCurrentSession();
		session.clear();
	      try{
	         session.saveOrUpdate(account); 
	         return true;
	      }catch (HibernateException e) {
	    	    e.printStackTrace(); 
	    	  return false;
	      }finally {
	        session.close(); 
	      }
	}
	
	public AccountOpening createAccount(AccountOpening account) {
		// TODO Auto-generated method stub
		Session session =getCurrentSession();
		session.clear();
		  Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         session.saveOrUpdate(account); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	        session.close(); 
	      }
		return account;
	}

	public boolean updateInterestAmount() throws HibernateException {
		// TODO Auto-generated method stub
		Session session =getCurrentSession();
		session.clear();
		  try{
			  String query="SELECT acc FROM Account acc";
		List<Account>  accountsList=  session.createQuery(query, Account.class).list();
		accountsList.forEach(account -> { 	
			 try{
		    	 BigDecimal interestAmount=Utilities.calculateSimpleInterestPerDay(account.getBalance());
		     	 account.setBalance(account.getBalance().add(interestAmount));
		     	 
		         AccountTransaction accountTransaction= new AccountTransaction();
		         accountTransaction.setTxnAmount(interestAmount);
		         accountTransaction.setTxnDate(LocalDateTime.now(Clock.systemUTC()));
		         accountTransaction.setTxnRemarks("Credit interest amount");
		         accountTransaction.setTxnType(TransactionType.CRDT);
		         accountTransaction.setAccount(account);
		         
		         InterestHistory interestHistory= new InterestHistory();
		         interestHistory.setAccount(account);
		         interestHistory.setCalculatedamt(interestAmount);
		         interestHistory.setCalculatedDate(LocalDateTime.now());
		         
		         session.save(interestHistory);
		         session.save(accountTransaction);
		         session.saveOrUpdate(account); 
		         
		        
			 }catch (HibernateException e) {
		         throw e;
		      }
			
          });
		
	      }finally {
	        session.close(); 
	      }
		  return true;
	}
	
	public Optional<Account> getAccountById(Long identification) {
		return Optional.of((Account) getCurrentSession().get(Account.class, identification));
	}
	
}
