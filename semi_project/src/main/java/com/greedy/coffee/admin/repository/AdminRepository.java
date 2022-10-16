package com.greedy.coffee.admin.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.coffee.order.entity.Order;


public interface AdminRepository extends JpaRepository<Order, Long>{

	Page<Order> findByOrdStatus(String activeStatus, Pageable pageable);

	Order findByOrdNum(int ordNum);

	
}
