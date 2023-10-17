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
	
	@Column(name = "customer_id")
	private Long customerId;

	@Column(name = "date_req")
	private Date date;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "status")
	private int status = 0;
	
	@Column(name = "money")
	private Long money;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}
}
