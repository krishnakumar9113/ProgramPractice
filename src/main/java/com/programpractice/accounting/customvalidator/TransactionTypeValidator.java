package com.programpractice.accounting.customvalidator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.programpractice.accounting.utils.UtilConstants.TransactionType;

public class TransactionTypeValidator implements ConstraintValidator<TransactionTypeConstraint, TransactionType> {
	private TransactionType[] validTransactionsList;

	@Override
	public void initialize(TransactionTypeConstraint constraint) {
		this.validTransactionsList = TransactionType.values();
	}

	@Override
	public boolean isValid(TransactionType txnType, ConstraintValidatorContext cxt) {
		return Arrays.asList(validTransactionsList).contains(txnType);
	}
}
