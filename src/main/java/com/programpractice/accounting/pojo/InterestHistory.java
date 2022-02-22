package com.programpractice.accounting.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "interest_history")
public class InterestHistory {
	
	@Id
	@GeneratedValue(generator = "int_hist_id_generator",strategy =  GenerationType.SEQUENCE)
	@Column(name = "interest_history_id")
	private long id;

	@Column(name = "calculated_date")
	private LocalDateTime calculatedDate;

	@Column(name = "calculated_amount")
	private BigDecimal calculatedAmount;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "identification")
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public LocalDateTime getCalculatedDate() {
		return calculatedDate;
	}

	public void setCalculatedDate(LocalDateTime calculatedDate) {
		this.calculatedDate = calculatedDate;
	}

	public BigDecimal getCalculatedAmount() {
		return calculatedAmount;
	}

	public void setCalculatedAmount(BigDecimal calculatedAmount) {
		this.calculatedAmount = calculatedAmount;
	}


}
