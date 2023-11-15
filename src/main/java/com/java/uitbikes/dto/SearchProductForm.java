package com.java.uitbikes.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchProductForm {
	public List<Long> rangePrice = new ArrayList<Long>();
	public List<Long> rangeCc = new ArrayList<Long>();
	public List<Long> rangeDateManu = new ArrayList<Long>();
	public Long typeId;
	public Long brandId;
	public List<String> colorArray=new ArrayList<String>();
	public SearchProductForm() {
		// TODO Auto-generated constructor stub
	}
}
