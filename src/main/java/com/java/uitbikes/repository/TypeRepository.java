package com.java.uitbikes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.uitbikes.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long>{
	@Query(value="select t.type_id, t.name, sum(p.price * ide.quantity) revenue from type t, invoice_detail ide, product p, invoice i where ide.p_id = p.p_id and p.type_id = t.type_id and i.invoice_id = ide.invoice_id and status = 2 group by t.type_id order by revenue desc limit 1", nativeQuery = true)
	Object findBestSellType();
}
