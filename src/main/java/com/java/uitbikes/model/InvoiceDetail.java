package com.java.uitbikes.model;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_detail")
@IdClass(InvoiceDetailID.class)
public class InvoiceDetail {
	@Id
	@Column(name = "invoice_id")
	private Long invoice_id;
	
	@Id
	@Column(name = "p_id")
	private String p_id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
	private Invoice invoice;
}
