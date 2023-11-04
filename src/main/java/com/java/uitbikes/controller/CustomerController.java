package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.uitbikes.dto.AccountDto;
import com.java.uitbikes.model.Customer;
//import com.java.uitbikes.repository.CustomerRepository;
import com.java.uitbikes.service.CustomerService;

@RestController
@CrossOrigin
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
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Customer getCustomerById(@PathVariable(value = "id") Long id) {
		return customerService.getCustomerById(id);
	}
	
	//update customer
	@RequestMapping(value = "/{username}", method = RequestMethod.PUT)
	public AccountDto updateCustomer(@PathVariable(value = "username") String username, @RequestBody Customer customerDetail) {
		return customerService.updateCustomer(username, customerDetail);
	}
	//update balance
	@RequestMapping(value = "/{id}/balance/{balance}", method = RequestMethod.PUT)
	public Customer updateBalance(@PathVariable(value = "id") Long id, @PathVariable(value = "balance") Long balance) {
		return customerService.updateBalance(id, balance);
	}
}

