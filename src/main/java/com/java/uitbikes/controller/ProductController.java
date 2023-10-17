package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.uitbikes.dto.ProductDto;
import com.java.uitbikes.model.Product;
import com.java.uitbikes.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/products")
public class ProductController {
	@Autowired 
	ProductService productService;
	
	// create product
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Product createProduct(@RequestBody Product p) {
		return productService.createProduct(p);
	}
	
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
	
	// get product detail by name (to use in page ProductDetail)
	@RequestMapping(value = "/detail/names/{name}", method = RequestMethod.GET)
	public ProductDto getProductDetailByName(@PathVariable(value = "name") String name) {
		return productService.getProductDetailByName(name);
	}
	
	// get all products detail (to display in page Home)
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public List<Product> getAllProductsDetail(){
		return productService.getAllProductsDetail();
	}
	
	// get all products detail by type id (to display in page Home)
	@RequestMapping(value = "/details/type/{type_id}", method = RequestMethod.GET)
	public List<Product> getAllProductsDetailByTypeId(@PathVariable(value = "type_id") Long type_id){
		return productService.getAllProductsDetailByTypeId(type_id);
	}
	
	// get all products detail by brand id 
	@RequestMapping(value = "/details/brand/{brand_id}", method = RequestMethod.GET)
	public List<Product> getAllProductsDetailByBrandId(@PathVariable(value = "brand_id") Long brand_id){
		return productService.getAllProductsDetailByBrandId(brand_id);
	}
	
	// get list names of products
	@RequestMapping(value = "/names", method = RequestMethod.GET)
	public List<String> getAllProductsNames(){
		return productService.getAllProductsNames();
	}
	
	// get list colors
	@RequestMapping(value = "/colors", method = RequestMethod.GET)
	public List<String> getAllProductColors(){
		return productService.getAllProductColors();
	}
	
	// set type
	@RequestMapping(value = "/{p_id}/type/{type_id}", method = RequestMethod.PUT)
	public Product setType(@PathVariable(value = "p_id") Long p_id, @PathVariable(value = "type_id") Long type_id) {
		return productService.setType(p_id, type_id);
	}
	
	// set brand
	@RequestMapping(value = "/{p_id}/brand/{brand_id}", method = RequestMethod.PUT)
	public Product setBrand(@PathVariable(value = "p_id") Long p_id, @PathVariable(value = "brand_id") Long brand_id) {
		return productService.setBrand(p_id, brand_id);
	}
	
	// change product's active
	@RequestMapping(value = "is-active/{id}/{is-active}", method = RequestMethod.PUT)
	public Boolean updateProductActive(@PathVariable(value = "id") Long id, @PathVariable(value = "is-active") Long isActive) {
		return productService.updateProductActive(id, isActive);
	}
	
	// update product by id
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Product updateProduct(@PathVariable(value = "id") Long id, @RequestBody Product p) {
		return productService.updateProduct(id, p);
	}
}
