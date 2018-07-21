package com.cg.paymentapp.jpa.exception;
@SuppressWarnings("serial")
public class InsufficientBalanceException extends RuntimeException {
	public InsufficientBalanceException(String msg) {
		super(msg);
	}
}
