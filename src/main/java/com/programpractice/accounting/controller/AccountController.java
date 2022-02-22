package com.programpractice.accounting.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programpractice.accounting.pojo.AccountOpening;
import com.programpractice.accounting.service.AccountService;
import static com.programpractice.accounting.utils.Utilities.createJsonMessage;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@PostMapping("/process-account-opening")
	public ResponseEntity<Object> processAccountOpening(@Valid @RequestBody AccountOpening accountOpening,
			BindingResult bindingResult)
	// Todo: update with DTO Objects
	{
		try {
			List<FieldError> be = new ArrayList<>();
			if (bindingResult.hasErrors()) {
				be.addAll(bindingResult.getFieldErrors());
			}
			if (be.isEmpty()) {
				return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createNewAccount(accountOpening));
			} else {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(be);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest()
					.body(createJsonMessage("Server error has encountered, failed to save the record"));
		}
	}

	@DeleteMapping("/delete-account/{identification}")
	public ResponseEntity<String> deleteAccount(@PathVariable("identification") long identifcationNumber) {
		if (accountService.deleteAccountById(identifcationNumber)) {
			return ResponseEntity.status(HttpStatus.OK).body(createJsonMessage("Account deleted successfully"));

		}
		return ResponseEntity.badRequest().body(createJsonMessage("Account deletion failed"));

	}
}
