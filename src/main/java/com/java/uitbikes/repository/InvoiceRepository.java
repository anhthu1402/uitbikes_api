package com.java.uitbikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.uitbikes.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	List<Invoice> findByStatus(int status);
	
	@Query(value="select count(distinct customer_id) from invoice where date_purchased = CURRENT_DATE", nativeQuery = true)
	double countCustomerPurchasedToday();
}
