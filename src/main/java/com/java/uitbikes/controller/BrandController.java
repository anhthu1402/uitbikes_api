package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.uitbikes.model.Brand;
import com.java.uitbikes.service.BrandService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/brands")
public class BrandController {
	@Autowired
	BrandService brandService;
	
	//create brand
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Brand createBrand(@RequestBody Brand brandDetail) {
		return brandService.createBrand(brandDetail);
	}
	
	//get all brands
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Brand> getAllBrands(){
		return brandService.getAllBrands();
	}
	
	//get brand by id
	@RequestMapping(value = "/{brand_id}", method = RequestMethod.GET)
	public Brand getBrandById(@PathVariable(value = "brand_id") Long brand_id) {
		return brandService.getBrandById(brand_id);
	}
	
	//update brand
	@RequestMapping(value = "/{brand_id}", method = RequestMethod.PUT)
	public Brand updateBrand(@PathVariable(value = "brand_id") Long brand_id, @RequestBody Brand brandDetail) {
		return brandService.updateBrand(brand_id, brandDetail);
	}
	
	//delete brand
	@RequestMapping(value = "/{brand_id}", method = RequestMethod.DELETE)
	public Boolean deleteBrand(@PathVariable(value = "brand_id") Long brand_id) {
		return brandService.deleteBrand(brand_id);
	}
}
