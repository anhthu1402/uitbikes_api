package com.java.uitbikes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.uitbikes.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long>{

}
