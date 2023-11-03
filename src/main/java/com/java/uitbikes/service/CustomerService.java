package com.java.uitbikes.service;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.model.Customer;
import com.java.uitbikes.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	//create customer
		public Customer createCustomer() {
			Date registerDate= Date.from(Instant.now());			
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
				return customerRepository.save(c);
			}
			return customerRepository.save(customerDetail);
		}
		
		//update balance
		public Customer updateBalance(Long id, Long balance) {
			Optional<Customer> customer = customerRepository.findById(id);
			if(customer.isPresent()) {
				customer.get().setBalance(balance);
				return customerRepository.save(customer.get());
			}
			return null;
		}
		
	
	
}
