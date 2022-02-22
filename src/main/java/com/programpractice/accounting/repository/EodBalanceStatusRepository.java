package com.programpractice.accounting.repository;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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
		Session session = getCurrentSession();
		try {

			String query = "select e from EodBalanceStatus e where e.executedDate = :date";
			Optional<EodBalanceStatus> eodbalanceStatus = session.createQuery(query, EodBalanceStatus.class)
					.setParameter("date", LocalDate.now(Clock.systemUTC())).uniqueResultOptional();
			if (eodbalanceStatus.isPresent()) {
				return eodbalanceStatus.get().isExecuted();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	public void setEodBalanceStatusForToday() {
		Session session = getCurrentSession();

		try {
			EodBalanceStatus eodBalanceStatus = new EodBalanceStatus();
			eodBalanceStatus.setExecuted(true);
			eodBalanceStatus.setExecuteddate(LocalDate.now(Clock.systemUTC()));
			session.saveOrUpdate(eodBalanceStatus);
		} catch (HibernateException e) {

			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}
