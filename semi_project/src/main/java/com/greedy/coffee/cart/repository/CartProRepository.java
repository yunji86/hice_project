package com.greedy.coffee.cart.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.coffee.cart.entity.Cart;
import com.greedy.coffee.cart.entity.CartPro;
import com.greedy.coffee.member.entity.Member;
import com.greedy.coffee.mypage.entity.Myorder;
import com.greedy.coffee.product.entity.Product;


public interface CartProRepository extends JpaRepository<CartPro, Long>{
	
	Optional<CartPro> findByCart(Cart cart);
	
	Optional<CartPro> findByProduct(Product pro);
	
	
	Optional<CartPro> findByCartProCode(Long procode);
	
}
