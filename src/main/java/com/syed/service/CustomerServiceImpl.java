package com.syed.service;

import java.util.List;

import org.jboss.logging.Logger;

import com.syed.dao.CustomerDao;
import com.syed.dao.CustomerDaoImpl;
import com.syed.dao.PaymentMethodDao;
import com.syed.dao.PaymentMethodDaoImpl;
import com.syed.entity.Address;
import com.syed.entity.Customer;
import com.syed.entity.PaymentMethod;

public class CustomerServiceImpl implements CustomerService {

	PaymentMethodDao paymentMethodDao = new PaymentMethodDaoImpl();
	CustomerDao customerDao = new CustomerDaoImpl();

	static final Logger logger = Logger.getLogger(CustomerServiceImpl.class);

	@Override
	public void updateAddress(Address address) {
	}

	@Override
	public void updateCustomer(Customer customer) {
	}

	@Override
	public void deleteCustomer(Long custId) {
		customerDao.deleteCustomer(custId);
	}

	@Override
	public int deletePaymentMethods(Long custId) {
		return paymentMethodDao.deletePaymentMethods(custId);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerDao.saveCustomer(customer);
	}

	@Override
	public Customer findCustomerFullData(Long custId) {
		return customerDao.findCustomerFullData(custId);
	}

	@Override
	public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod) {
		return paymentMethodDao.updatePaymentMethod(paymentMethod);
	}

	@Override
	public void createPaymentMethods(List<PaymentMethod> paymentMethods) {
		paymentMethodDao.insertPaymentMethods(paymentMethods);
	}

	@Override
	public List<PaymentMethod> findPaymentMethods(Long customerId) {
		return paymentMethodDao.findPaymentMehods(customerId);
	}

	@Override
	public Customer findCustomer(Long customerId) {
		return customerDao.findCustomer(customerId);
	}

	@Override
	public List<Customer> viewAllCustomers() {
		return customerDao.getAllCustomers();
	}
}
