package com.java.uitbikes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.model.Type;
import com.java.uitbikes.repository.TypeRepository;

@Service
public class TypeService {
	@Autowired
	TypeRepository typeRepository;
	
	public Type createType(Type type) {
		return typeRepository.save(type);
	}
	
	public List<Type> getAllTypes(){
		return typeRepository.findAll();
	}
	
	public Type getTypeById(Long id) {
		Optional<Type> type = typeRepository.findById(id);
		if(type.isPresent()) {
			return type.get();
		}
		return null;
	}
	
	public Type updateType(Type typeDetail) {
		Optional<Type> type = typeRepository.findById(typeDetail.getId());
		if(type.isPresent()) {
			Type t = type.get();
			t.setName(typeDetail.getName());
			return typeRepository.save(t);
		}
		return typeRepository.save(typeDetail);
	}
}
