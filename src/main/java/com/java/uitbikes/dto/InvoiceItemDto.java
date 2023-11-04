package com.java.uitbikes.dto;

import com.java.uitbikes.model.Product;

public class InvoiceItemDto {
	private Product product;
	private int quantity;
	
	public InvoiceItemDto() {}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
