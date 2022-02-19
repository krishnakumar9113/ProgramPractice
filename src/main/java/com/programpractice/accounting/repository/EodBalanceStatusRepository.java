package com.programpractice.accounting.repository;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.programpractice.accounting.pojo.EodBalanceStatus;


@Repository
public class EodBalanceStatusRepository {
	
	@PersistenceContext
    private EntityManager entityManager;

    protected Session getCurrentSession() {
            return entityManager.unwrap(Session.class);
    }

   
    public boolean getEodBalanceStatusForToday() {
		// TODO Auto-generated method stub
		Session session =getCurrentSession();
		session.clear();
	      try{
			   	  
	    	  String jpql = "select e from EodBalanceStatus e where e.executeddate = :date";
	    	  Optional<EodBalanceStatus> eodbalanceStatus = 
	    			  session.createQuery(jpql)
	    	        .setParameter("date", LocalDate.now(Clock.systemUTC())).uniqueResultOptional();
	    	  if(eodbalanceStatus.isPresent()) {
	    		 return eodbalanceStatus.get().isExecuted();
	    	  }
	      }catch (HibernateException e) {
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return false;
	}

	public void setEodBalanceStatusForToday() {
		// TODO Auto-generated method stub
		Session session =getCurrentSession();
		session.clear();
	      try{
	         EodBalanceStatus eodBalanceStatus = new EodBalanceStatus();
	         eodBalanceStatus.setExecuted(true);
	         eodBalanceStatus.setExecuteddate(LocalDate.now(Clock.systemUTC()));
	         session.saveOrUpdate(eodBalanceStatus); 
	      }catch (HibernateException e) {
	      
	         e.printStackTrace(); 
	      }finally {
	        session.close(); 
	      }
	
	}



	
}
