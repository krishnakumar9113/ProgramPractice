package com.programpractice.accounting.controller;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.programpractice.accounting.pojo.AccountOpening;
import com.programpractice.accounting.repository.AccountRepository;
import com.programpractice.accounting.service.AccountService;

@RestController
@CrossOrigin
//@RequestMapping("/api")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PostMapping("/processAccountOpening")
	public ResponseEntity processAccountOpening(@Valid @RequestBody AccountOpening account, BindingResult bindingResult)

	{
		try {
			List<FieldError> be= new ArrayList<FieldError>();
			 if (bindingResult.hasErrors()) {
				be.addAll(bindingResult.getFieldErrors());
			 }
			if(be.isEmpty()) {
			
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(accountService.createNewAccount(account));
					
			} else {
				
			
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(be);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body("Server error has encountered, failed to save the record");
		}
	}


}
