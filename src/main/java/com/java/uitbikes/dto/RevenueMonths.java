package com.java.uitbikes.dto;

import java.util.List;

public class RevenueMonths {
	private List<String> labels;
	private List<Long> data;
	
	public RevenueMonths() {}
	
	public RevenueMonths(List<String> months, List<Long> revenues) {
		this.labels = months;
		this.data = revenues;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<Long> getData() {
		return data;
	}

	public void setData(List<Long> data) {
		this.data = data;
	}
}
