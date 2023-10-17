package com.java.uitbikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.uitbikes.model.InvoiceDetail;
import com.java.uitbikes.model.InvoiceDetailID;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, InvoiceDetailID> {
	List<InvoiceDetail> findByInvoiceId(Long invoice_id);
}
