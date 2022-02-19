package com.programpractice.accounting.customvalidator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.programpractice.accounting.pojo.AccountTransaction;
import com.programpractice.accounting.utils.UtilConstants.TransactionType;

class TransactionTypeValidatorTest {

	 
	  private TransactionTypeConstraint transactionTypeConstraint=   Mockito.mock(TransactionTypeConstraint.class);

	  private ConstraintValidatorContext constraintValidatorContext;

	  @Test
	  public void testIsValidCredit() {


	    TransactionTypeValidator transactionTypeValidator = new TransactionTypeValidator();
	    transactionTypeValidator.initialize(transactionTypeConstraint);
	    AccountTransaction accountTransaction = new AccountTransaction();
	    accountTransaction.setTxnType(TransactionType.CRDT);
	    boolean result = transactionTypeValidator.isValid(accountTransaction.getTxnType(), constraintValidatorContext);
	    assertTrue(result);
	  }

}
