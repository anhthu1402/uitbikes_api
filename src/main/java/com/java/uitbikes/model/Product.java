package com.java.uitbikes.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "cc")
	private Long cc;
	
	@Column(name = "date_manu")
	private Long date;
	
	@Column(name = "is_active")
	private Long isActive;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "describe_product")
	private String describe;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "price")
	private Long price;
	
	@ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "type_id")
	private Type type = new Type();
	
	@ManyToOne
	@JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
	private Brand brand = new Brand();
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Cart> cart = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<InvoiceDetail> invoiceDetails  = new ArrayList<>();
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCc() {
		return cc;
	}

	public void setCc(Long cc) {
		this.cc = cc;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getIsActive() {
		return isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public List<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}
	
}
