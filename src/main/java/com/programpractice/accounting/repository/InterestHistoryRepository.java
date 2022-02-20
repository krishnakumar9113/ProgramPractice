package com.programpractice.accounting.repository;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.programpractice.accounting.pojo.Account;
import com.programpractice.accounting.pojo.AccountTransaction;
import com.programpractice.accounting.pojo.InterestHistory;
import com.programpractice.accounting.pojo.MonthlyStatus;
import com.programpractice.accounting.utils.UtilConstants.TransactionType;
import com.programpractice.accounting.utils.Utilities;

@Repository
public class InterestHistoryRepository {
	
	@PersistenceContext
    private EntityManager entityManager;

	  private static Logger log = LoggerFactory.getLogger(InterestHistoryRepository.class);
 
    protected Session getCurrentSession() {
            return entityManager.unwrap(Session.class);
    }


	@Async("asyncExecutor")
    public CompletableFuture<List<MonthlyStatus>> calculateMonthlyAccuredInterest(String month,String year) {
		// TODO Auto-generated method stub
		Session session =getCurrentSession();
		  List<MonthlyStatus> lst =null;
		session.clear();
	      try{
	    	  
		  String jpql = "select  e.account.identification as identification ,sum(e.calculatedamt) as calculatedamt from InterestHistory e where date_part('month',e.calculatedDate) = :month and date_part('year',e.calculatedDate) = :year GROUP BY e.account.identification";
	    Query query=  session.createQuery(jpql).setParameter("month",Double.parseDouble(month)).setParameter("year",Double.parseDouble(year))
	    		.setResultTransformer(Transformers.aliasToBean(MonthlyStatus.class));
	    lst= query.getResultList();

	      }catch (HibernateException e) {
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return CompletableFuture.completedFuture(lst);
	}

	public boolean updateInterestAmount() throws HibernateException {
		// TODO Auto-generated method stub
		Session session =getCurrentSession();
		session.clear();
			    
		  try{
			  String query="SELECT acc FROM Account acc";
		Stream<Account>  accountsStream=  session.createQuery(query, Account.class).stream();
		accountsStream.forEach(account -> { 	
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
		         interestHistory.setCalculatedDate(LocalDateTime.now(Clock.systemUTC()));
		         
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
	
	
	
}
