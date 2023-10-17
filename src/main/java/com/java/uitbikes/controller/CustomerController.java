package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.uitbikes.model.Customer;
//import com.java.uitbikes.repository.CustomerRepository;
import com.java.uitbikes.service.CustomerService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/customers")

public class CustomerController {
	@Autowired 
	CustomerService customerService;
	
	// create customer
		@RequestMapping(value = "", method = RequestMethod.POST)
		public Customer createCustomer() {
			return customerService.createCustomer();
		}
	// get all customers (for in admin)
		@RequestMapping(value = "", method = RequestMethod.GET)
		public List<Customer> getAllCustomers(){
			return customerService.getAllCustomers();
		}
	
	// get customer by id
		@RequestMapping(value = "/{customer_id}", method = RequestMethod.GET)
		public Customer getCustomerById(@PathVariable(value = "customer_id") Long customer_id) {
			return customerService.getCustomerById(customer_id);
		}
	
	//update customer
	//not done
		@RequestMapping(value = "/{customer_id}", method = RequestMethod.PUT)
		public Customer updateCustomer(@PathVariable(value = "customer_id") Long customer_id, @RequestBody Customer customerDetail) {
			return customerService.updateCustomer(customer_id, customerDetail);
		}
		
	//	delete customer
//		@RequestMapping(value = "/{customer_id}", method = RequestMethod.DELETE)
//		public void deleteCustomer(@PathVariable(value = "customer_id") Long customer_id) {
//			customerService.deleteCustomer(customer_id);
//		}
	
}

