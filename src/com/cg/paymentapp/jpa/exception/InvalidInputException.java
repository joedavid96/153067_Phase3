package com.cg.paymentapp.jpa.exception;
@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {
	public InvalidInputException(String msg) {
		super(msg);
	}
}
