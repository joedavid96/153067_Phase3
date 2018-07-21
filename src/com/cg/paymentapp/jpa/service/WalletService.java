package com.cg.paymentapp.jpa.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import com.cg.paymentapp.jpa.exception.InsufficientBalanceException;
import com.cg.paymentapp.jpa.exception.InvalidInputException;
import com.cg.paymentapp.jpa.beans.Customer;

public interface WalletService {
	public Customer createAccount(String name, String mobileno, BigDecimal amount)
			throws InvalidInputException, SQLException;

	public Customer showBalance(String mobileno) throws InvalidInputException, SQLException;

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount)
			throws InvalidInputException, InsufficientBalanceException, SQLException;

	public Customer depositAmount(String mobileNo, BigDecimal amount) throws InvalidInputException, SQLException;

	public Customer withdrawAmount(String mobileNo, BigDecimal amount)
			throws InvalidInputException, InsufficientBalanceException, SQLException;

	public List<String> transactions(String mobileNo) throws InvalidInputException, SQLException;
}
