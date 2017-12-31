package com.syed.dao;

import java.util.List;

import com.syed.entity.Customer;

public interface CustomerDao {
	Customer saveCustomer(Customer customer);

	Customer findCustomer(Long customerId); // For Customer and customer with address (Note: Address is eager load)

	Customer findCustomerFullData(Long custId);

	void deleteCustomer(Long custId); // will delete along the customer information also.

	Customer updateCustomer(Customer customer);
	
	List<Customer> getAllCustomers();
}
