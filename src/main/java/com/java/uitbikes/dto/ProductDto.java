package com.java.uitbikes.dto;

import java.util.ArrayList;
import java.util.List;

import com.java.uitbikes.model.Brand;
import com.java.uitbikes.model.Product;
import com.java.uitbikes.model.Type;

public class ProductDto {
	private Long id;
	private String name;
	private Long cc;
	private Long date;
	private Long isActive;
	private String describe;
	private Type type;
	private Brand brand;
	private List<ProductDetailDto> detail = new ArrayList<ProductDetailDto>();
	
	public ProductDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductDto(Product p, List<ProductDetailDto> detail) {
		this.id = p.getId();
		this.name = p.getName();
		this.cc = p.getCc();
		this.date = p.getDate();
		this.isActive = p.getIsActive();
		this.describe = p.getDescribe();
		this.type = p.getType();
		this.brand = p.getBrand();
		this.detail = detail;
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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	public List<ProductDetailDto> getDetail() {
		return detail;
	}

	public void setDetail(List<ProductDetailDto> detail) {
		this.detail = detail;
	}
	
}
