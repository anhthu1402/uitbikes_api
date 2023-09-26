package com.java.uitbikes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.uitbikes.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{

}
