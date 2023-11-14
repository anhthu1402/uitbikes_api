package com.java.uitbikes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.dto.ProductDetailDto;
import com.java.uitbikes.dto.ProductDto;
import com.java.uitbikes.model.Brand;
import com.java.uitbikes.model.Product;
import com.java.uitbikes.model.Type;
import com.java.uitbikes.repository.BrandRepository;
import com.java.uitbikes.repository.ProductRepository;
import com.java.uitbikes.repository.TypeRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	TypeRepository typeRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	// create product
	public Product createProduct(Product p) {
		return productRepository.save(p);
	}
	
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
				if(product.getIsActive()==1) {
					result.add(new ProductDetailDto(product));
				}
			}
		}
		return result;
	}
	
	// get first product having this name
	public Product getFirstProductByName(String name) {
		List<Product> products = getAllProducts();
		for (Product product : products) {
			if(product.getName().equals(name)){
				if(product.getIsActive() == (long)1) {
					return product;
				}
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
	
	// get list colors
	public List<String> getAllProductColors(){
		List<String> result=new ArrayList<String>();
		List<Product> products=getAllProducts();
		for (Product product : products) {
			String color = product.getColor();
			if(!result.contains(color)) {
				result.add(color);
			}
		}
		return result;
	}
	
	// get product detail by name
	public ProductDto getProductDetailByName(String name) {
		Product product = getFirstProductByName(name);
		if(product != null) {
			List<ProductDetailDto> productsDetail = getProductDetail(name);
			return new ProductDto(product, productsDetail);
		}
		return null;
	}
	
	// get all products detail (to display in page Home)
	public List<Product> getAllProductsDetail(){
		List<Product> result = new ArrayList<Product>();
		List<String> listNames = getAllProductsNames();
		for (String name : listNames) {
			Product product = getFirstProductByName(name);
			if(product!=null) {
				result.add(product);
			}
		}
		return result;
	}
	
	// get all products detail by type id (to display in page Home)
	public List<Product> getAllProductsDetailByTypeId(Long type_id){
		List<Product> products = getAllProductsDetail();
		List<Product> result = new ArrayList<Product>();
		if(type_id == 0) {
			return products;
		}
		else {
			Optional<Type> type = typeRepository.findById(type_id);
			if(type.isPresent()) {
				for (Product product : products) {
					if(product.getType().getId().equals(type_id)) {
						result.add(product);
					}
				}
				return result;
			}
			return null;
		}
	}
	
	// get all products by type id (for admin)
	public List<Product> getAllProductsByTypeId(Long type_id){
		List<Product> products = getAllProducts();
		List<Product> result = new ArrayList<Product>();
		if(type_id == 0) {
			return products;
		}
		else {
			Optional<Type> type = typeRepository.findById(type_id);
			if(type.isPresent()) {
				for (Product product : products) {
					if(product.getType().getId().equals(type_id)) {
						result.add(product);
					}
				}
				return result;
			}
			return null;
		}
	}
	
	// get all products detail by brand id 
	public List<Product> getAllProductsDetailByBrandId(Long brand_id){
		List<Product> products = getAllProductsDetail();
		List<Product> result = new ArrayList<Product>();
		Optional<Brand> brand = brandRepository.findById(brand_id);
		if(brand.isPresent()) {
			for (Product product : products) {
				if(product.getBrand().getId().equals(brand_id)) {
					result.add(product);
				}
			}
			return result;
		}
		return null;
	}
	
	// set type
	public Product setType(Long p_id, Long type_id) {
		Optional<Product> product = productRepository.findById(p_id);
		Optional<Type> type = typeRepository.findById(type_id);
		if(type.isPresent() && product.isPresent()) {
			Product p = product.get();
			Type t = type.get();
			p.setType(t);
			return productRepository.save(p);
		}
		return null;
	}
	
	// set brand
	public Product setBrand(Long p_id, Long brand_id) {
		Optional<Product> product = productRepository.findById(p_id);
		Optional<Brand> brand = brandRepository.findById(brand_id);
		if(brand.isPresent() && product.isPresent()) {
			Product p = product.get();
			Brand b = brand.get();
			p.setBrand(b);
			return productRepository.save(p);
		}
		return null;
	}
	
	// update product
	public Product updateProduct(Long id, Product pDetail) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			Product p = product.get();
			p.setBrand(pDetail.getBrand());
			p.setCc(pDetail.getCc());
			p.setColor(pDetail.getColor());
			p.setDate(pDetail.getDate());
			p.setDescribe(pDetail.getDescribe());
			p.setImage(pDetail.getImage());
			p.setIsActive(pDetail.getIsActive());
			p.setName(pDetail.getName());
			p.setPrice(pDetail.getPrice());
			p.setQuantity(pDetail.getQuantity());
			p.setType(pDetail.getType());
			return productRepository.save(p);
		}
		return productRepository.save(pDetail);
	}
	
	// change product's active
	public Boolean updateProductActive(Long id, Long isActive) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			Product p = product.get();
			p.setIsActive(isActive);
			productRepository.save(p);
			return true;
		}
		return false;
	}
	
	// get range of cc
	public List<Long> getRangeOfCc(){
		List<Product> listProducts = getAllProducts();
		Long max = listProducts.get(0).getCc();
		Long min = listProducts.get(0).getCc();
		for (Product product : listProducts) {
			Long cc = product.getCc();
			if(cc > max) {
				max = cc;
			}
			if(cc < min) {
				min = cc;
			}
		}
		List<Long> result = new ArrayList<Long>();
		result.add(min);
		result.add(max);
		return result;
	}
	
	// get range of date_manu
	public List<Long> getRangeOfDateManu(){
		List<Product> listProducts = getAllProducts();
		Long max = listProducts.get(0).getDate();
		Long min = listProducts.get(0).getDate();
		for (Product product : listProducts) {
			Long date = product.getDate();
			if(date > max) {
				max = date;
			}
			if(date < min) {
				min = date;
			}
		}
		List<Long> result = new ArrayList<Long>();
		result.add(min);
		result.add(max);
		return result;
	}
	
	// get range of price
	public List<Long> getRangeOfPrice(){
		List<Product> listProducts = getAllProducts();
		Long max = listProducts.get(0).getPrice();
		Long min = listProducts.get(0).getPrice();
		for (Product product : listProducts) {
			Long price = product.getPrice();
			if(price > max) {
				max = price;
			}
			if(price < min) {
				min = price;
			}
		}
		List<Long> result = new ArrayList<Long>();
		result.add(min);
		result.add(max);
		return result;
	}
	
	// search product
	
	
}
