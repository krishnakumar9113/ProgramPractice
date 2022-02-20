package com.programpractice.accounting.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Utilities {

	/*	public static BigDecimal calculateSimpleInterestPerMonth(BigDecimal balanceAmount) {
			   BigDecimal totalInterestPerMonth=calculateSimpleInterestPerDay(balanceAmount).multiply(BigDecimal.valueOf(LocalDate.now ().lengthOfMonth()));
			  return totalInterestPerMonth;
			 
		}*/
	
	public static BigDecimal calculateSimpleInterestPerDay(BigDecimal balanceAmount) {
		
		  BigDecimal dailyInterest=(balanceAmount.multiply(UtilConstants. RATE_OF_INTEREST)).divide(UtilConstants.NO_OF_DAYS_YEAR,2,RoundingMode.HALF_EVEN);
		  return dailyInterest;
		 
	}
	
	public static String createJsonMessage(String message) {
		return "{\"result\":\""+message+"\"}";
	}
}
