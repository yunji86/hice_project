package com.greedy.coffee.best.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.coffee.best.entity.Beans;

public interface BestRepository extends JpaRepository<Beans, Long>  {

	@Query("SELECT b " +
	          "FROM Beans b " +
	         "WHERE b.beanType = :beanType " +
	           "AND b.beanStatus = :beanStatus " +
	           "AND (b.beanName LIKE '%' || :searchValue || '%' " +
        	"OR b.beanSolidity LIKE '%' || :searchValue || '%')")
	Page<Beans> findBySearchValue(int beanType, String beanStatus, String searchValue, Pageable pageable);

	Page<Beans> findByBeanTypeAndBeanStatus(int beanType, String beanStatus, Pageable pageable);

	
	
	
	
	
	
}
