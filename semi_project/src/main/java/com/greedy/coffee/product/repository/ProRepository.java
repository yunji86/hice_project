package com.greedy.coffee.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.coffee.product.entity.Product;


public interface ProRepository extends JpaRepository<Product, Long>{
	
	Page<Product> findByProStatus(String proStatus, Pageable pageable);

	//상품전체조회	
	@Query("SELECT p "+
			 "FROM Product p " +
			"WHERE p.proStatus = :proStatus " +
			  "AND (p.proName LIKE '%' || :proSearchValue || '%' " +
			  "OR p.proContent LIKE '%' || :proSearchValue || '%')")
	Page<Product> findByProSearchValue(@Param("proStatus") String proStatus, @Param("proSearchValue") String proSearchValue, Pageable pageable);

	//상품 디테일
	Product findByProCodeAndProStatus(Long proCode, String proStatus);

	Product findByProCode(Long proCode);



}
