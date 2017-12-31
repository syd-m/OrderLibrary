package com.syed.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.syed.entity.Address;
import com.syed.entity.Customer;
import com.syed.entity.PaymentMethod;
import com.syed.service.CustomerService;
import com.syed.service.CustomerServiceImpl;

public class OrderLibApp {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	static Scanner userInput = new Scanner(System.in);
	private static CustomerService customerService = new CustomerServiceImpl();

	// static final Logger logger = Logger.getLogger(OrderLibApp.class);

	public static void main(String[] args) throws ParseException {
		displayOptions();
	}

	private static void displayOptions() {
		// BasicConfigurator.configure();
		// logger.debug("Orderdisplay options method called");
		int option = 0;
		String input = "";
		do {
			System.out.println("1. Add Customer along with Address" + "\n2. Add PaymentMethods for a customer"
					+ "\n3. Fetch all the PaymentMethods for given customer"
					+ "\n4. Fetch Customer along with Address and Payment methods"
					+ "\n5. Fetch Customer along with Address but no Payment methods"
					+ "\n6. Delete Customer and all associated information"
					+ "\n7. Update customer payment methods with new information"
					+ "\n8. Delete a Payment method of a customer" + "\n9. Show all customers"
					+ "\n10. Apply logging for all the above api/methods " + "\n0. EXIT"
					+ "\nSelect an option from the above options: ");

			option = userInput.nextInt();
			switchOptions(option);
			System.out.println("Do you want to continue (Y/N): ");
			input = userInput.next();
			if (input.equalsIgnoreCase("n")) {
				switchOptions(0);
			}
		} while (input.equalsIgnoreCase("y"));
	}

	private static void switchOptions(int option) {
		switch (option) {
		case 1:
			Customer customer = null;
			try {
				customer = populateCustInfo();
			} catch (ParseException e) {
				System.err.println(e.getMessage());
			}
			Customer savedCustomer = customerService.createCustomer(customer);
			System.out.println("***Entered customer has been added***");
			System.out.println(savedCustomer);
			break;

		case 2:
			System.out.println("Enter the customer id you want to add payment method to: ");
			Customer checkedCustomer = customerService.findCustomer(userInput.nextLong());
			if (checkedCustomer == null) {
				System.out.println("Entered customer doesn't exist");
			} else {
				List<PaymentMethod> paymentMethods = null;
				try {
					paymentMethods = populatePaymentMethods(checkedCustomer);
				} catch (ParseException e) {
					System.err.println(e.getMessage());
				}
				customerService.createPaymentMethods(paymentMethods);
				System.out.println("***Entered payment methods have been added***");
			}
			break;
		case 3:
			System.out.println("Enter the customer id whose payment methods to be displayed: ");
			List<PaymentMethod> customerPaymentMethods = customerService.findPaymentMethods(userInput.nextLong());
			if (!customerPaymentMethods.isEmpty()) {
				for (PaymentMethod paymentMethod : customerPaymentMethods) {
					System.out.println(paymentMethod);
				}
			} else
				System.err.println("No payments methods found with entered ID");
			break;
		case 4:
			System.out.println("Enter the customer id whose customer to be displayed: ");
			Customer customerWithFullData = customerService.findCustomerFullData(userInput.nextLong());
			if (customerWithFullData != null)
				System.out.println(customerWithFullData.printCustomerWithAllData());
			else
				System.err.println("No customer found with entered ID");
			break;
		case 5:
			System.out.println("Enter the customer id whose customer information to be loaded: ");
			Customer foundCustomer = customerService.findCustomer(userInput.nextLong());
			if (foundCustomer != null)
				System.out.println(foundCustomer);
			else
				System.err.println("No customer found with entered ID");
			break;
		case 6:
			System.out.println("Enter the customer's id to delete: ");
			customerService.deleteCustomer(userInput.nextLong());
			break;
		case 7:
			System.out.println("Enter the customer id: ");
			Customer checkCustomer = customerService.findCustomer(userInput.nextLong());
			if (checkCustomer != null) {
				System.out.println(checkCustomer.getPaymentType());
				PaymentMethod paymentMethod = new PaymentMethod();
				System.out.println("Select the payment method id to update from above: ");
				paymentMethod.setPaymentId(userInput.nextLong());
				System.out.println("***Enter payment method details***");
				System.out.println("Enter card name: ");
				paymentMethod.setCardName(userInput.next());
				System.out.println("Enter card number: ");
				paymentMethod.setCardNumber(userInput.next());
				System.out.println("Enter card type ");
				paymentMethod.setCardType(userInput.next());
				System.out.println("Enter date from (YYYY/MM/DD): ");
				try {
					paymentMethod.setDateFrom(sdf.parse(userInput.next()));
				} catch (ParseException e) {
					e.getMessage();
				}
				paymentMethod.setCustomer(checkCustomer);
				customerService.updatePaymentMethod(paymentMethod);
			} else
				System.err.println("Customer doesn't exist");
			break;
		case 8:
			System.out.println("Enter customer id to delete payment methods: ");
			Long custId = userInput.nextLong();
			Customer checkdCustomer = customerService.findCustomer(custId);
			if (checkdCustomer != null) {
				System.out.println(customerService.deletePaymentMethods(custId));
			} else
				System.err.println("Customer doesn't exist");
			break;
		case 9:
			List<Customer> allCustomers = customerService.viewAllCustomers();
			System.out.println(allCustomers);
			break;
		case 0:
			System.out.println("Thank You, GoodBye!!!");
			System.exit(0);
			break;
		default:
			System.err.println("Invalid Choice, Try again");
			break;
		}
	}

	// private static Customer checkIfCustomerExists(Long customerId) {
	// Customer checkCustomer = null;
	// Session session = null;
	// try {
	// sessionFactory = HibernateUtil.getSessionFactory();
	// Session session = sessionFactory.openSession();
	// checkCustomer = session.get(Customer.class, customerId);
	// Hibernate.initialize(checkCustomer.getPaymentType());
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// } finally {
	// session.close();
	// }
	// return checkCustomer;
	// }

	private static List<PaymentMethod> populatePaymentMethods(Customer checkedCustomer) throws ParseException {
		System.out.println("***Enter payment method details***");
		System.out.println("Enter number of payments methods you want to add: ");
		int noOfPaymentMethods = userInput.nextInt();
		PaymentMethod pm = null;
		List<PaymentMethod> pmList = new ArrayList<>();

		while (noOfPaymentMethods != 0) {
			pm = new PaymentMethod();
			System.out.println("Enter card name: ");
			pm.setCardName(userInput.next());
			System.out.println("Enter card number: ");
			pm.setCardNumber(userInput.next());
			System.out.println("Enter card type ");
			pm.setCardType(userInput.next());
			System.out.println("Enter date from (YYYY/MM/DD): ");
			pm.setDateFrom(sdf.parse(userInput.next()));
			pm.setCustomer(checkedCustomer);
			pmList.add(pm);
			System.out.println("---------------------------------");
			noOfPaymentMethods--;
		}
		return pmList;
	}

	private static Customer populateCustInfo() throws ParseException {
		System.out.println("***Enter customer information***");
		Customer customer = new Customer();
		System.out.println("Enter customer name: ");
		customer.setName(userInput.next());
		System.out.println("Enter date of birth (YYYY/MM/DD): ");
		customer.setDateOfBirth(sdf.parse(userInput.next()));
		System.out.println("Enter annual salary: ");
		customer.setAnnualSalary(userInput.nextDouble());
		System.out.println("Enter Address: ");
		Address address = new Address();
		System.out.println("street: ");
		address.setStreet(userInput.next());
		System.out.println("city: ");
		address.setCity(userInput.next());
		System.out.println("state: ");
		address.setState(userInput.next());
		System.out.println("zip: ");
		address.setZip(userInput.next());
		customer.setAddress(address);
		address.setCustomer(customer);
		return customer;
	}
}
