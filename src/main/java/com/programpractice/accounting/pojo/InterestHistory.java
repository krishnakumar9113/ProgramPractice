package com.programpractice.accounting.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
@Table(name = "interesthistory")
public class InterestHistory {
	@Id
    @GeneratedValue(generator = "int_hist_id_generator")
    @SequenceGenerator(
            name = "int_hist_id_generator",
            sequenceName = "id_sequence",
            initialValue = 1
    )
	
	@Getter
	@Column(name = "id")
	private long id;// (PK)				Int 	
	@Getter
	 @Column(name = "calculateddate")
		private LocalDateTime calculatedDate	;//			date

	@Getter
	 @Column(name = "calculatedamt")
	 private BigDecimal calculatedamt	;//	

  
			@JsonIgnore
			@ManyToOne
            @JoinColumn(name = "identification")
            private Account account;

			
			 public Account getAccount() { return account; }
			 
			public void setAccount(Account account) {
				this.account = account;
			}

			public LocalDateTime getCalculatedDate() {
				return calculatedDate;
			}

			public void setCalculatedDate(LocalDateTime calculatedDate) {
				this.calculatedDate = calculatedDate;
			}

			public BigDecimal getCalculatedamt() {
				return calculatedamt;
			}

			public void setCalculatedamt(BigDecimal calculatedamt) {
				this.calculatedamt = calculatedamt;
			}
 

}
