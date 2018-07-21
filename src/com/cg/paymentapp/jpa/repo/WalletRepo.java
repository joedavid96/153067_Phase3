package com.cg.paymentapp.jpa.repo;
import java.sql.SQLException;
import java.util.List;
import com.cg.paymentapp.jpa.beans.Customer;
public interface WalletRepo {
	public boolean save(Customer customer);
	public Customer findOne(String mobileNo);
	public boolean saveTransactions(String mobileNo, String log) throws SQLException;
	public List<String> getList(String mobileNo) throws SQLException;
}
