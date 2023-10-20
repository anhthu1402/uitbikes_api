package com.java.uitbikes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.uitbikes.model.InvoiceDetail;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {
	
}
