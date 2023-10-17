package com.java.uitbikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.uitbikes.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	List<Invoice> findByCustomerId(Long customer_id);
	List<Invoice> findByStatus(int status);
}
