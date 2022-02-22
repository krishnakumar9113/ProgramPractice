package com.programpractice.accounting.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(generator = "account_id_generator",strategy =  GenerationType.SEQUENCE)
	@Column(name = "account_id")
	private long identification;

	@Column(name = "bsb")
	private long bsb;

	@DecimalMin(value = "0.00", inclusive = true)
	@Digits(integer = 10, fraction = 2)
	@Column(name = "balance")
	private BigDecimal balance;

	@JsonIgnore
	@Column(name = "openingdate")
	private LocalDateTime openingDate;

	@JsonIgnore
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<AccountTransaction> accountTransaction;

	@JsonIgnore
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<InterestHistory> interestHistory;

	public void setTransactions(List<AccountTransaction> accountTransaction) {
		this.accountTransaction = accountTransaction;
	}

	public long getIdentification() {
		return identification;
	}

	public void setIdentification(long identification) {
		this.identification = identification;
	}

	public long getBsb() {
		return bsb;
	}

	public void setBsb(long bsb) {
		this.bsb = bsb;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setOpeningDate(LocalDateTime openingDate) {
		this.openingDate = openingDate;
	}

}
