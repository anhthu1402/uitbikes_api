package com.java.uitbikes.model;

import java.io.Serializable;

public class InvoiceDetailID implements Serializable {
	private Long invoice_id;
	private Long p_id;
	
	public InvoiceDetailID(Long invoice_id, Long p_id) {
        this.invoice_id = invoice_id;
        this.p_id = p_id;
    }
}
