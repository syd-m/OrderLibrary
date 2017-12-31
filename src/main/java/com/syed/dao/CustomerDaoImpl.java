package com.syed.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.syed.entity.Customer;
import com.syed.util.HibernateUtil;

public class CustomerDaoImpl implements CustomerDao {

	SessionFactory sessionFactory;

	public CustomerDaoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(customer);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			session.close();
		}
		return customer;
	}

	@Override
	public Customer findCustomerFullData(Long custId) {
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Customer customerAllInfo = null;
		try {
			session.beginTransaction();
			customerAllInfo = (Customer) session.get(Customer.class, custId);
			Hibernate.initialize(customerAllInfo.getPaymentType());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return customerAllInfo;
	}

	@Override
	public void deleteCustomer(Long custId) {
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Customer customer = (Customer) session.load(Customer.class, custId);
			session.delete(customer);
			System.out.println("***Selected customer deleted successfully***");
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return null;
	}

	@Override
	public Customer findCustomer(Long customerId) {
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Customer foundCustomer = null;
		try {
			foundCustomer = (Customer) session.get(Customer.class, customerId);
			Hibernate.initialize(foundCustomer.getPaymentType());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return foundCustomer;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Customer> getAllCustomers() {
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<Customer> allCustomers = session.createCriteria(Customer.class).list();
		return allCustomers;
	}
}
