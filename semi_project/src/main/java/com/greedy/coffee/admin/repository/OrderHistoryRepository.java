package com.greedy.coffee.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.coffee.order.entity.Order;

public interface OrderHistoryRepository extends JpaRepository<Order, Long> {

	

	public Page<Order> findByOrdTypeAndOrdStatus(int ordType, String ordStatus, Pageable pageable);
	
	@Query("SELECT o " +
	          "FROM Order o " +
	         "WHERE o.ordType = :ordType " +
	           "AND o.ordStatus = :ordStatus " +
	           "AND (o.ordName LIKE '%' || :searchValue || '%' " +
        	"OR o.ordAddress LIKE '%' || :searchValue || '%')")
	public Page<Order> findBySearchValue(@Param("ordType") int ordType, @Param("ordStatus") String activeStatus, @Param("searchValue") String searchValue, Pageable pageable);



	
	
	

}
