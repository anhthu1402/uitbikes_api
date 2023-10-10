package com.java.uitbikes.model;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "charge_request")
public class ChargeRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "account_number")
	private String account_number;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "money")
	private Long money;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Customer customer;
}
