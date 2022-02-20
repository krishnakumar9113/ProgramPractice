package com.programpractice.accounting.pojo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;


@Data
@Entity
@Table(name = "eodbalancestatus")
public class EodBalanceStatus {
	
	// For handling idempotency while claculating EOFD balances
	@Id
    @GeneratedValue(generator = "eodid_generator")
    @SequenceGenerator(
            name = "eodid_generator",
            sequenceName = "id_sequence",
            initialValue = 1
    )
	@Getter
    @Column(name = "id")
	private long id;// (PK)				Int 	
	

	// @Size(min = 1, max = 50,message="Input between 1 to 50 characters!")
	// @Pattern(regexp="[a-zA-Z]*", message="Please input alphabet only!")
	@Getter
	 @Column(name = "executed")
	 private boolean executed	;//			Char(50)	
	
	 
	 
	// @Past(message="Date must be a Past Date")
	// @TransactionTypeConstraint
	  @Getter
	 @Column(name = "executeddate")
	 private LocalDate executeddate;//				date	



	public boolean isExecuted() {
		return executed;
	}



	public void setExecuted(boolean executed) {
		this.executed = executed;
	}



	public LocalDate getExecuteddate() {
		return executeddate;
	}



	public void setExecuteddate(LocalDate executeddate) {
		this.executeddate = executeddate;
	}
	 
	 

	
	
	
}
