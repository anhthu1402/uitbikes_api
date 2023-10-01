package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.uitbikes.model.Type;
import com.java.uitbikes.service.TypeService;

@RestController
@RequestMapping(value = "/api/types")
public class TypeController {
	@Autowired
	TypeService typeService;

	//create type
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Type createType(@RequestBody Type type) {
		return typeService.createType(type);
	}

	//get all types
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Type> getAllTypes(){
		return typeService.getAllTypes();
	}

	//get type by id
	@RequestMapping(value = "/{type_id}", method = RequestMethod.GET)
	public Type getTypeById(@PathVariable(value = "type_id") Long type_id) {
		return typeService.getTypeById(type_id);
	}

	//update type
	@RequestMapping(value = "/{type_id}", method = RequestMethod.PUT)
	public Type updateType(@PathVariable(value = "type_id") Long type_id, @RequestBody Type typeDetail) {
		return typeService.updateType(type_id, typeDetail);
	}

	//delete type
	@RequestMapping(value = "/{type_id}", method = RequestMethod.DELETE)
	public Boolean deleteType(@PathVariable(value = "type_id") Long type_id) {
		return typeService.deleteType(type_id);
	}
}
