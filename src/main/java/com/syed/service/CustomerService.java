package com.syed.service;

import java.util.List;

import com.syed.entity.Address;
import com.syed.entity.Customer;
import com.syed.entity.PaymentMethod;

public interface CustomerService {
	void updateAddress(Address address);

	void updateCustomer(Customer customer);

	void deleteCustomer(Long custId);

	int deletePaymentMethods(Long custId);

	Customer createCustomer(Customer customer);

	PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod);

	Customer findCustomerFullData(Long custId);

	void createPaymentMethods(List<PaymentMethod> paymentMethods);

	List<PaymentMethod> findPaymentMethods(Long customerId);

	Customer findCustomer(Long customerId);
	
	List<Customer> viewAllCustomers();
}
