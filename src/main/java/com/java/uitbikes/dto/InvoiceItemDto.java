package com.java.uitbikes.dto;

public class InvoiceItemDto {
	private Long id;
	private int quantity;
	
	public InvoiceItemDto() {}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Long getId() {
		return id;
	}
	
	public int getQuantity() {
		return quantity;
	}
}
