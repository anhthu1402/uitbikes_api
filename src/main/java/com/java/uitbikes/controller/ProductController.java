package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.uitbikes.dto.ProductDto;
import com.java.uitbikes.model.Product;
import com.java.uitbikes.service.ProductService;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
	@Autowired 
	ProductService productService;
	
	// get all products (for read products in admin)
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	// get product by id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Product getProductById(@PathVariable(value = "id") Long id) {
		return productService.getProductById(id);
	}
	
	// get list products by name
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public List<Product> getListProductsByName(@PathVariable(value = "name") String name){
		return productService.getListProductsByName(name);
	}
	
	// get product detail by name
	@RequestMapping(value = "/names/{name}", method = RequestMethod.GET)
	public ProductDto getProductDetailByName(@PathVariable(value = "name") String name) {
		return productService.getProductDetailByName(name);
	}
	
	// get all products detail (to display in page Home)
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public List<ProductDto> getAllProductsDetail(){
		return productService.getAllProductsDetail();
	}
	
	// get all products detail by type id (to display in page Home)
	@RequestMapping(value = "/details/type/{type_id}", method = RequestMethod.GET)
	public List<ProductDto> getAllProductsDetailByTypeId(@PathVariable(value = "type_id") Long type_id){
		return productService.getAllProductsDetailByTypeId(type_id);
	}
	
	// get all products detail by brand id 
	@RequestMapping(value = "/details/brand/{brand_id}", method = RequestMethod.GET)
	public List<ProductDto> getAllProductsDetailByBrandId(@PathVariable(value = "brand_id") Long brand_id){
		return productService.getAllProductsDetailByBrandId(brand_id);
	}
	
	// get list names of products
	@RequestMapping(value = "/names", method = RequestMethod.GET)
	public List<String> getAllProductsNames(){
		return productService.getAllProductsNames();
	}
}
