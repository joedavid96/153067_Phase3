package com.cg.paymentapp.jpa.repo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.cg.paymentapp.jpa.beans.Customer;
import com.cg.paymentapp.jpa.beans.Transaction;

public class WalletRepoImpl implements WalletRepo {
	private static EntityManagerFactory factory;

	public WalletRepoImpl() {
		factory = Persistence.createEntityManagerFactory("JPA-PU");
	}

	@Override
	public boolean save(Customer customer) {
		EntityManager entityManager1 = null;
		EntityManager entityManager2 = null;
		if (entityManager1 == null || !entityManager1.isOpen()) {
			entityManager1 = factory.createEntityManager();
		}
		if (entityManager2 == null || !entityManager2.isOpen()) {
			entityManager2 = factory.createEntityManager();
		}
		EntityTransaction trans = entityManager1.getTransaction();
		trans.begin();
		if (entityManager1.find(Customer.class, customer.getMobileNo()) != null) {
			entityManager1.merge(customer);
			trans.commit();
			return true;
		} else {
			entityManager2.persist(customer);
			Transaction t = new Transaction(customer.getMobileNo(),
					new java.util.Date() + "\tAccount Created. Balance in A/C : " + customer.getWallet().getBalance());
			entityManager2.persist(t);
			trans.commit();
			return true;
		}
	}

	@Override
	public Customer findOne(String mobileNo) {
		EntityManager entityManager3 = null;
		if (entityManager3 == null || !entityManager3.isOpen()) {
			entityManager3 = factory.createEntityManager();
		}
		EntityTransaction trans = entityManager3.getTransaction();
		trans.begin();
		Customer c = entityManager3.find(Customer.class, mobileNo);
		System.out.println(c);
		if (c != null) {
			trans.commit();
			return c;
		} else {
			trans.rollback();
			return null;
		}
	}

	@Override
	public boolean saveTransactions(String mobileNo, String log) throws SQLException {
		EntityManager entityManager4 = null;
		if (entityManager4 == null || !entityManager4.isOpen()) {
			entityManager4 = factory.createEntityManager();
		}
		EntityTransaction trans = entityManager4.getTransaction();
		trans.begin();
		Transaction t = new Transaction(mobileNo, log);
		entityManager4.persist(t);
		trans.commit();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getList(String mobileNo) throws SQLException {
		List<String> l = new ArrayList<String>();
		EntityManager entityManager5 = null;
		if (entityManager5 == null || !entityManager5.isOpen()) {
			entityManager5 = factory.createEntityManager();
		}
		EntityTransaction trans = entityManager5.getTransaction();
		trans.begin();
		Query query = entityManager5.createQuery("select t.log from Transaction t where t.mobileNo = :tmobile",
				String.class);
		query.setParameter("tmobile", mobileNo);
		l = query.getResultList();
		if (l != null) {
			trans.commit();
			return l;
		} else {
			trans.rollback();
			return null;
		}
	}
}
