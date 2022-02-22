package com.programpractice.accounting.customvalidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = TransactionTypeValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionTypeConstraint {
	String message() default "Please  enter a valid tranaction type either \"CRDT\" or \"DBIT\"";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
