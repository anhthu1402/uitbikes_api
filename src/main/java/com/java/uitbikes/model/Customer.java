package com.java.uitbikes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

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
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
	private Account account;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Cart> carts = new ArrayList<Cart>();

	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private List<Invoice> invoices = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<ChargeRequest> chargeRequests = new ArrayList<>();
	
	public Customer() {
		// TODO Auto-generated constructor stub
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

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	
	public void addCart(Cart cart) {
		this.carts.add(cart);
	}
	
	public void removeCart(Cart cart) {
		this.carts.remove(cart);
	}
	
	public List<ChargeRequest> getChargeRequests() {
		return chargeRequests;
	}
	
	public void setChargeRequests(List<ChargeRequest> chargeRequests) {
		this.chargeRequests = chargeRequests;
	}
	
	public List<Invoice> getInvoices() {
		return invoices;
	}
	
	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
}