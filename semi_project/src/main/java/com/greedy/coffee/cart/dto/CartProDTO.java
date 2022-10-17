package com.greedy.coffee.cart.dto;


import java.sql.Date;
import java.util.List;

import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.product.dto.ProDTO;

import lombok.Data;

@Data
public class CartProDTO {
	
	private Long cartProCode;

	private String orderName;
	private int count;
	private CartDTO cart;
	private MemberDTO member;
	private ProDTO product;
	
}
