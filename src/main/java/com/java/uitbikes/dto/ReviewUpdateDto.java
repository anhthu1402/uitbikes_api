package com.java.uitbikes.dto;

public class ReviewUpdateDto {
	private int rate;
	private String text;
	
	public ReviewUpdateDto() {}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
