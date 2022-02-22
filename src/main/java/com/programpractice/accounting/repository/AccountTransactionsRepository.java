package com.programpractice.accounting.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.programpractice.accounting.pojo.AccountTransaction;

@Repository
public class AccountTransactionsRepository {

	@PersistenceContext
	private EntityManager entityManager;

	protected Session getCurrentSession() {
		return entityManager.unwrap(Session.class);
	}

	public boolean saveOrUpdate(AccountTransaction transaction) {

		Session session = getCurrentSession();
		try {
			session.saveOrUpdate(transaction);

			return true;
		} catch (HibernateException e) {
			return false;
		} finally {
			session.close();
		}
	}

	public boolean checkValidTransaction(String iKey) {

		Session session = getCurrentSession();
		try {
			String jpql = "select 1  from AccountTransaction  where ikey = :param1";
			Query<Integer> query = session.createQuery(jpql, Integer.class);
			query.setParameter("param1", iKey);
			Optional<Integer> val = query.uniqueResultOptional();
			if (val.isPresent()) {
				return false;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

}
