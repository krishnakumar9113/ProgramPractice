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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.programpractice.accounting.customvalidator.TransactionTypeConstraint;
import com.programpractice.accounting.utils.UtilConstants.TransactionType;

@Entity
@Table(name = "AccountTransaction")
public class AccountTransaction {
	
	@Id
	@GeneratedValue(generator = "txn_id_generator",strategy =  GenerationType.SEQUENCE)
	@Column(name = "txn_id")
	private long txnId;

	@JsonIgnore
	@Column(name = "txn_date")
	private LocalDateTime txnDate;

	@TransactionTypeConstraint
	@Column(name = "txn_type")
	private TransactionType txnType;

	@DecimalMin(value = "0.00", inclusive = true)
	@Digits(integer = 10, fraction = 2)
	@Column(name = "txn_amt")
	private BigDecimal txnAmount;

	@Column(name = "txn_remarks")
	private String txnRemarks;

	@Column(name = "ikey", unique = true)
	private String ikey;

	public String getIkey() {
		return ikey;
	}

	public void setIkey(String ikey) {
		this.ikey = ikey;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "identification")
	private Account account;

	public void setAccount(Account account) {
		this.account = account;
	}

	public long getTxnid() {
		return txnId;
	}

	public void setTxnid(long txnid) {
		this.txnId = txnid;
	}

	public LocalDateTime getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(LocalDateTime txnDate) {
		this.txnDate = txnDate;
	}

	public TransactionType getTxnType() {
		return txnType;
	}

	public void setTxnType(TransactionType txnType) {
		this.txnType = txnType;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}

	public String getTxnRemarks() {
		return txnRemarks;
	}

	public void setTxnRemarks(String txnRemarks) {
		this.txnRemarks = txnRemarks;
	}

}
