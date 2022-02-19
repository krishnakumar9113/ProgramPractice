package com.programpractice.accounting.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "account")
public class AccountOpening {
	
	
	@Id
    @GeneratedValue(generator = "eid_generator")
    @SequenceGenerator(
            name = "eid_generator",
            sequenceName = "id_sequence",
            initialValue = 1
    )

    @Column(name = "identification")
	private long identification;// (PK)				Int 	

	@Column(name = "bsb")
	private long bsb;//				Char ( 50)	
	
	 @JsonIgnore
	 @Column(name = "opening_date")
	 private LocalDateTime opening_date;//				date	
	 
	 @DecimalMin(value = "0.00", inclusive = true)
	 @Digits(integer=10, fraction=2)
	 @Column(name = "balance")
	 @JsonIgnore
	 private BigDecimal balance	;
	
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

	public LocalDateTime getOpening_date() {
		return opening_date;
	}
	
	public void setOpening_date(LocalDateTime opening_date) {
		this.opening_date = opening_date;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
	
}
