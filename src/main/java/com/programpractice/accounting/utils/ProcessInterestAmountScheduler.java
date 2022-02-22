package com.programpractice.accounting.utils;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.programpractice.accounting.service.InterestService;

@EnableAsync
@Component
public class ProcessInterestAmountScheduler {
	private static final Logger logger = LoggerFactory.getLogger(ProcessInterestAmountScheduler.class);
	@Autowired
	InterestService interestService;

	@Async("asyncExecutor")
	@Scheduled(cron = "0 00 22 * * *")
	public void scheduleProcessEndOfDayBalances() {
		logger.info("Scheduled processing of Interest rate started at {}", LocalDate.now());
		interestService.processEndOfDayBalances();
		logger.info("Scheduled processing of Interest rate ended  at {}", LocalDate.now());

	}
}
