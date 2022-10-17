package com.greedy.coffee.cart.dto;

import org.springframework.data.domain.Page;

import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.product.dto.ProDTO;

import lombok.Data;

// 마이페이지 장바구니 조회용 객체 
@Data
public class MyCartDTO {

	Page<CartProDTO> pagingCartProDTO;
	
	int sum;
	
}
