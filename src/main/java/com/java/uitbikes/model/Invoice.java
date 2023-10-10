package com.java.uitbikes.model;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "invoice")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	private Long id;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "total")
	private Long total;
	
	@Column(name = "status")
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;
}
