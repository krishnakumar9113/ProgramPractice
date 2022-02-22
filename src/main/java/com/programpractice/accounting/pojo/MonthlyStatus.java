package com.programpractice.accounting.pojo;

import java.math.BigDecimal;

public class MonthlyStatus {

	private long identification;

	private BigDecimal calculatedAmount;

	public MonthlyStatus(Long identification, BigDecimal calculatedAmount) {
		this.identification = identification;
		this.calculatedAmount = calculatedAmount;
	}

	public long getIdentification() {
		return identification;
	}

	public void setIdentification(long identification) {
		this.identification = identification;
	}

	public BigDecimal getCalculatedAmount() {
		return calculatedAmount;
	}

	public void setCalculatedAmount(BigDecimal calculatedAmount) {
		this.calculatedAmount = calculatedAmount;
	}

}
