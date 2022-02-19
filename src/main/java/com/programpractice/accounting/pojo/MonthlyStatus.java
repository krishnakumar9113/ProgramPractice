package com.programpractice.accounting.pojo;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonGetter;

public class MonthlyStatus {

	public long getIdentification() {
		return identification;
	}
	public void setIdentification(long identification) {
		this.identification = identification;
	}
	public BigDecimal getCalculatedamt() {
		return calculatedamt;
	}
	public void setCalculatedamt(BigDecimal calculatedamt) {
		this.calculatedamt = calculatedamt;
	}

	private long  identification;

	private BigDecimal calculatedamt;
	    
	    
}
