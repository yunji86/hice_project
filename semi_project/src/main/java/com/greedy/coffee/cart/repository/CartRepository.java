package com.greedy.coffee.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.coffee.cart.entity.Cart;
import com.greedy.coffee.member.entity.Member;
import com.greedy.coffee.mypage.entity.Myorder;
import com.greedy.coffee.product.entity.Product;
import com.greedy.coffee.review.entity.RevBoard;


public interface CartRepository extends JpaRepository<Cart, Long>{
	
	List<Cart> findByMember(Member member);
	
	
	
}
