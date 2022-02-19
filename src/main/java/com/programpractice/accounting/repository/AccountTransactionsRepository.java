package com.programpractice.accounting.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.programpractice.accounting.pojo.Account;
import com.programpractice.accounting.pojo.AccountTransaction;
import com.programpractice.accounting.utils.UtilConstants.TransactionStates;
import com.programpractice.accounting.utils.UtilConstants.TransactionType;

@Repository
public class AccountTransactionsRepository {
	
	@PersistenceContext
    private EntityManager entityManager;

  
    protected Session getCurrentSession() {
            return entityManager.unwrap(Session.class);
    }
    
	public boolean saveOrUpdate(AccountTransaction transaction) {
		// TODO Auto-generated method stub
		Session session =getCurrentSession();
	      try{   	        
	    	  session.saveOrUpdate(transaction); 
	 		 	      
	 		 return true;
	      }
	      catch (HibernateException e) {
	         return false;
	      }finally {
	         session.close(); 
	      }
	}
	
	
	public boolean checkValidTransaction (String Ikey) {
		// TODO Auto-generated method stub
		Session session =getCurrentSession();
	      try{
	    	  String jpql = "select 1  from AccountTransaction  where ikey = :param1";
	    	  Query query= session.createQuery(jpql);
	    	  query.setParameter("param1", Ikey);
	    	  Optional<Integer>val= query.uniqueResultOptional();
	    	  if(val.isEmpty()) {
	    		  return true;
	    	  }
	      }
	      catch (HibernateException e) {
	    	  e.printStackTrace();
	    	  return false;
	      }finally {
	         session.close(); 
	      }
		return false;
	}
	


	
}
