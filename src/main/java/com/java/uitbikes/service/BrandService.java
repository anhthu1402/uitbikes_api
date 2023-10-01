package com.java.uitbikes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.model.Brand;
import com.java.uitbikes.model.Product;
import com.java.uitbikes.repository.BrandRepository;

@Service
public class BrandService {
	@Autowired
	BrandRepository brandRepository;
	
	//create brand
	public Brand createBrand(Brand brand) {
		return brandRepository.save(brand);
	}
	
	//get all brands
	public List<Brand> getAllBrands(){
		return brandRepository.findAll();
	}
	
	//get brand by id
	public Brand getBrandById(Long id) {
		Optional<Brand> brand = brandRepository.findById(id);
		if(brand.isPresent()) {
			return brand.get();
		}
		return new Brand();
	}
	
	//update brand
	public Brand updateBrand(Long id, Brand brandDetail) {
		Optional<Brand> brand = brandRepository.findById(id);
		if(brand.isPresent()) {
			Brand b = brand.get();
			b.setImage(brandDetail.getImage());
			b.setName(brandDetail.getName());
			return brandRepository.save(b);
		}
		return brandRepository.save(brandDetail);
	}
	
	//delete brand
	public Boolean deleteBrand(Long id) {
		Optional<Brand> brand = brandRepository.findById(id);
		if(brand.isPresent()) {
			Brand b = brand.get();
			List<Product> products = b.getProducts();
			for (Product product : products) {
				product.setBrand(null);
			}
			brandRepository.delete(b);
			return true;
		}
		return false;
	}
}
