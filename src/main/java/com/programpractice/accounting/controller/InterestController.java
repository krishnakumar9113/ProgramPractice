package com.programpractice.accounting.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.programpractice.accounting.pojo.MonthlyStatus;
import com.programpractice.accounting.service.AccountService;
import com.programpractice.accounting.service.InterestService;

@RestController
@CrossOrigin
public class InterestController {

	@Autowired
	InterestService interestService;
	
	@Autowired
	AccountService  accountService;
	
	@PutMapping("/processAccountEndOfDayBalances")
	private ResponseEntity processAccountEndOfDayBalances()
	{				
		try {
			    interestService.processEndOfDayBalances();	
			 // create a JSON object
			 JSONObject jsonObject = new JSONObject();
		      jsonObject.put("balanceDate", LocalDate.now().toString());
		    jsonObject.put("AccountDetails", accountService.getAllAccountBalance());
		
				// fetch all account balances
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(jsonObject.toString());
			} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body("Server error has encountered, failed to save the record");
		}
	}
	
	  @PutMapping("/calculateMonthlyInterest/{month}/{year}") 
	  public ResponseEntity calculateMonthlyInterest(@PathVariable("month")  String  month,@PathVariable("year")  String  year)
	  {
			CompletableFuture<List<MonthlyStatus>> lst= null;
			ResponseEntity respentity=null;
	  try {
	 lst=interestService.calculateMonthlyInterest(month, year);
		
	  ObjectMapper mapper =new ObjectMapper();
	  respentity=   ResponseEntity.status(HttpStatus.CREATED)
				  .body(mapper.writeValueAsString(lst.join()));


	  } catch (Exception e) { e.printStackTrace(); 
	  return	  ResponseEntity.badRequest()
	  .body("Server error has encountered, failed to save the record"); 
	  }
	
	  return respentity;
	  
	  
	  
	  }

}
