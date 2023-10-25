package com.java.uitbikes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.uitbikes.model.InvoiceDetail;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {
	@Query(value = "select id, invoice_detail.invoice_id, p_id, sum(quantity) quantity from invoice_detail, invoice where invoice.invoice_id = invoice_detail.invoice_id and status = 2 group by p_id order by sum(quantity) desc limit 1;", nativeQuery = true)
	InvoiceDetail findBestSellProduct();
}
