package com.programpractice.accounting.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programpractice.accounting.pojo.MonthlyStatus;
import com.programpractice.accounting.repository.EodBalanceStatusRepository;
import com.programpractice.accounting.repository.InterestHistoryRepository;

@Service
public class InterestService {

	@Autowired
	private InterestHistoryRepository interestHistoryRepository;

	@Autowired
	private EodBalanceStatusRepository eodBalanceStatusRepository;

	public CompletableFuture<List<MonthlyStatus>> calculateMonthlyInterest(String month, String year) {
		return interestHistoryRepository.calculateMonthlyAccuredInterest(month, year);
	}

	@Transactional(rollbackFor = HibernateException.class)
	public void processEndOfDayBalances() {

		if (!eodBalanceStatusRepository.getEodBalanceStatusForToday()
				&& interestHistoryRepository.updateInterestAmount()) {
			eodBalanceStatusRepository.setEodBalanceStatusForToday();
		}
	}
}
