package com.programpractice.accounting.pojo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eod_balance_status")
public class EodBalanceStatus {

	@Id
	@GeneratedValue(generator = "eod_id_generator",strategy =  GenerationType.SEQUENCE)
	@Column(name = "eod_balance_status_id")
	private long id;

	@Column(name = "executed")
	private boolean executed;

	@Column(name = "executed_date")
	private LocalDate executedDate;

	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}

	public LocalDate getExecuteddate() {
		return executedDate;
	}

	public void setExecuteddate(LocalDate executeddate) {
		this.executedDate = executeddate;
	}

}
