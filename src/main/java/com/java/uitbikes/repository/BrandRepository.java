package com.java.uitbikes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.uitbikes.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
	@Query(value="select b.brand_id, b.name, b.image, sum(p.price * ide.quantity) revenue from brand b, invoice_detail ide, product p, invoice i where ide.p_id = p.p_id and p.brand_id = b.brand_id and i.invoice_id = ide.invoice_id and status = 2 group by b.brand_id order by revenue desc limit 1", nativeQuery = true)
	Object findBestSellBrand();
}
