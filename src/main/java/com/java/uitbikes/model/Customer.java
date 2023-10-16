package com.java.uitbikes.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private Long phone;
	
	@Column(name = "dob")
	private Date date;
	
	@Column(name = "register_date")
	private Date registerDate;
	
	@Column(name = "balance")
	private Long balance;
	
	@Column(name = "gender")
	private Long gender;
	
	@Column(name = "id_number")
	private Long idNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private Account account;
	
	
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(Date rD) {
		this.registerDate=rD;
		this.address =null;
		this.balance=null;
		this.date=null;
		this.gender=null;
		this.idNumber=null;
		this.name=null;
		this.phone=null;
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Long getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public Date getDate() {
		return date;
	}
	public Long getBalance() {
		return balance;
	}
	public Long getGender() {
		return gender;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Long getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(Long idNumber) {
		this.idNumber = idNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	public void setGender(Long gender) {
		this.gender = gender;
	}
}