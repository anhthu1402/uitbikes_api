package com.java.uitbikes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.model.Product;
import com.java.uitbikes.model.Type;
import com.java.uitbikes.repository.TypeRepository;

@Service
public class TypeService {
	@Autowired
	TypeRepository typeRepository;
	
	//create type
	public Type createType(Type type) {
		return typeRepository.save(type);
	}
	
	//get all types
	public List<Type> getAllTypes(){
		Type typeAll = new Type();
		typeAll.setId((long) 0);
		typeAll.setName("Tất cả");
		List<Type> result = new ArrayList<Type>();
		result.add(typeAll);
		result.addAll(typeRepository.findAll());
		return result;
	}
	
	//get type by id
	public Type getTypeById(Long id) {
		Optional<Type> type = typeRepository.findById(id);
		if(type.isPresent()) {
			return type.get();
		}
		return null;
	}
	
	//update type
	public Type updateType(Long id, Type typeDetail) {
		Optional<Type> type = typeRepository.findById(id);
		if(type.isPresent()) {
			Type t = type.get();
			t.setName(typeDetail.getName());
			return typeRepository.save(t);
		}
		return typeRepository.save(typeDetail);
	}
	
	//delete type
	public Boolean deleteType(Long id) {
		Optional<Type> type = typeRepository.findById(id);
		if(type.isPresent()) {
			Type t = type.get();
			List<Product> products = t.getProducts();
			for (Product product : products) {
				product.setType(null);
			}
			typeRepository.delete(t);
			return true;
		}
		return false;
	}
}
