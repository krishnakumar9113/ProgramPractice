package com.programpractice.accounting.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utilities {

	public static BigDecimal calculateSimpleInterestPerDay(BigDecimal balanceAmount) {

		return (balanceAmount.multiply(UtilConstants.RATE_OF_INTEREST)).divide(UtilConstants.NO_OF_DAYS_YEAR, 2,
				RoundingMode.HALF_EVEN);

	}

	public static String createJsonMessage(String message) {
		return "{\"result\":\"" + message + "\"}";
	}
}
