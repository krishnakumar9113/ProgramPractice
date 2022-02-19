package com.programpractice.accounting.utils;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UtilConstants {

	public enum TransactionType
	{
	    CRDT,DBIT, EMPTY
	}
	
	public enum TransactionStates{
		
		CREATED,DUPLICATE_KEY,FAILED, INVALID_ACCOUNT
	}
	public static final String VALID_TRANSACTION_MESSAGE="Transaction created successfully";
	public static final String DUPLICATE_IKEY_MESSAGE="Similar transaction has been already created";
	public static final String TRANSACTION_FAILED_MESSAGE="Transaction failed";
	public static final  BigDecimal RATE_OF_INTEREST= BigDecimal.valueOf(0.05);
	public static final BigDecimal NO_OF_DAYS_YEAR ;
	
	static {
	    int year=	LocalDateTime.now(Clock.systemUTC()).getYear();
		 Calendar calendar = new GregorianCalendar(year, 11, 31);
	      NO_OF_DAYS_YEAR =    BigDecimal.valueOf( calendar.get(Calendar.DAY_OF_YEAR));
	}
		
}
