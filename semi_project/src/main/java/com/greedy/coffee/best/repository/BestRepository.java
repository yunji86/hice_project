package com.greedy.coffee.best.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.coffee.product.entity.Product;

public interface BestRepository extends JpaRepository<Product, Long>  {
	

	Page<Product> findByProTypeAndProStatus(String proType, String proStatus, Pageable pageable);
	
	@Query("SELECT p " +
	          "FROM Product p " +
	         "WHERE p.proType = :proType " +
	           "AND p.proStatus = :proStatus " +
	           "AND p.proCount >= 10 " +
	           "AND (p.proName LIKE '%' || :searchValue || '%' " +
      	"OR p.proType LIKE '%' || :searchValue || '%')")
	Page<Product> findBySearchValue(@Param("proType") String proType, @Param("proStatus") String activeStatus, @Param("searchValue") String searchValue, Pageable pageable);

	Product findByProCodeAndProTypeAndProStatus(Long proCode, String stoType, String stoStatus);


	
	
	
	
	
	
}
