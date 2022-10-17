package com.greedy.coffee.admin.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.coffee.order.entity.Order;


public interface AdminRepository extends JpaRepository<Order, Long>{



	Order findByOrdCode(Long ordCode);


	Page<Order> findByOrdType(int textOrderType, Pageable pageable);

	
}
