package com.java.uitbikes.dto;

public class InvoiceDetailDto {
	private Long p_id;
	private Long quantity;
	
	public InvoiceDetailDto(Long p_id, Long quantity) {
		this.p_id = p_id;
		this.quantity = quantity;
	}

	public Long getP_id() {
		return p_id;
	}

	public void setP_id(Long p_id) {
		this.p_id = p_id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
}
