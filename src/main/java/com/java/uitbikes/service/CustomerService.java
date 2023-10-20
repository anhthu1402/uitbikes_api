package com.java.uitbikes.service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.model.Account;
import com.java.uitbikes.model.Customer;
import com.java.uitbikes.repository.AccountRepository;
import com.java.uitbikes.repository.CartRepository;
import com.java.uitbikes.repository.ChargeRequestRepository;
import com.java.uitbikes.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	//create customer
		public Customer createCustomer() {
			LocalDate localDate = LocalDate.now();
			Date registerDate=Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());			
			Customer customer = customerRepository.save(new Customer());
			customer.setRegisterDate(registerDate);
			return customerRepository.save(customer);
		}
	
	//get all customers
		public List<Customer> getAllCustomers(){
			return customerRepository.findAll();
		}
		
	//get customer by id
		public Customer getCustomerById(Long id) {
			Optional<Customer> customer = customerRepository.findById(id);
			if(customer.isPresent()) {
				return customer.get();
			}
			return new Customer();
		}
	
	
		//update customer
		public Customer updateCustomer(Long id, Customer customerDetail) {
			Optional<Customer> customer = customerRepository.findById(id);
			if(customer.isPresent()) {
				Customer c = customer.get();
				c.setName(customerDetail.getName());
				c.setAddress(customerDetail.getAddress());
				c.setPhone(customerDetail.getPhone());
				c.setDate(customerDetail.getDate());
				c.setGender(customerDetail.getGender());
				c.setBalance(customerDetail.getBalance());
				c.setIdNumber(customerDetail.getIdNumber());
				c.setRegisterDate(customerDetail.getRegisterDate());
				return customerRepository.save(c);
			}
			return customerRepository.save(customerDetail);
		}
		
	
	
}
