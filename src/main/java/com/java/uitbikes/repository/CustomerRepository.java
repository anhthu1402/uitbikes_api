package com.java.uitbikes.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.java.uitbikes.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{

}

