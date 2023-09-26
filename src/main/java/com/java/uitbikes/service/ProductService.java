package com.java.uitbikes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.dto.ProductDetailDto;
import com.java.uitbikes.dto.ProductDto;
import com.java.uitbikes.model.Product;
import com.java.uitbikes.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	// get all products (for read products in admin)
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	// get product by id
	public Product getProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return product.get();
		}
		return null;
	}
	
	// get list products by name
	public List<Product> getListProductsByName(String name){
		List<Product> products = getAllProducts();
		List<Product> result = new ArrayList<Product>();
		for (Product product : products) {
			if(product.getName().equals(name)) {
				result.add(product);
			}
		}
		return result;
	}
	
	// get list detail of products by name
	public List<ProductDetailDto> getProductDetail(String name){
		List<Product> products = getAllProducts();
		List<ProductDetailDto> result = new ArrayList<ProductDetailDto>();
		for (Product product : products) {
			if(product.getName().equals(name)){
				result.add(new ProductDetailDto(product));
			}
		}
		return result;
	}
	
	// get first product having this name
	public Product getFirstProductByName(String name) {
		List<Product> products = getAllProducts();
		for (Product product : products) {
			if(product.getName().equals(name)){
				return product;
			}
		}
		return null;
	}
	
	// get list names of products
	public List<String> getAllProductsNames(){
		List<Product> products = getAllProducts();
		List<String> result = new ArrayList<String>();
		String name = products.get(0).getName();
		result.add(name);
		for (Product product : products) {
			if(product.getName().equals(name)) {
				continue;
			}
			else {
				name = product.getName();
				result.add(name);
			}
		}
		return result;
	}
	
	// get product detail by name
	public ProductDto getProductDetailByName(String name) {
		Product product = getFirstProductByName(name);
		List<ProductDetailDto> productsDetail = getProductDetail(name);
		return new ProductDto(product, productsDetail);
	}
	
	// get all products detail (to display in page Home)
	public List<ProductDto> getAllProductsDetail(){
		List<Product> products = getAllProducts();
		List<ProductDto> result = new ArrayList<ProductDto>();
		List<String> listNames = getAllProductsNames();
		for (String name : listNames) {
			Product product = getFirstProductByName(name);
			List<ProductDetailDto> productDetail = getProductDetail(name);
			result.add(new ProductDto(product, productDetail));
		}
		return result;
	}
	
	// get all products detail by type id (to display in page Home)
	public List<ProductDto> getAllProductsDetailByTypeId(Long type_id){
		List<ProductDto> products = getAllProductsDetail();
		List<ProductDto> result = new ArrayList<ProductDto>();
		if(type_id == 0) {
			return products;
		}
		else {
			for (ProductDto productDto : products) {
				if(productDto.getType().getId().equals(type_id)) {
					result.add(productDto);
				}
			}
			return result;
		}
	}
	
	// get all products detail by brand id 
		public List<ProductDto> getAllProductsDetailByBrandId(Long brand_id){
			List<ProductDto> products = getAllProductsDetail();
			List<ProductDto> result = new ArrayList<ProductDto>();
			for (ProductDto productDto : products) {
				if(productDto.getBrand().getId().equals(brand_id)) {
					result.add(productDto);
				}
			}
			return result;
		}
}
