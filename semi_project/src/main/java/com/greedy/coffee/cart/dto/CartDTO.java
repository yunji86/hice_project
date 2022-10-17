package com.greedy.coffee.cart.dto;


import java.sql.Date;
import java.util.List;

import com.greedy.coffee.member.dto.MemberDTO;

import lombok.Data;

@Data
public class CartDTO {
	
	private Long orderCode;
	private String orderName;
	private String orderPhone;
	private String orderAddress;
	private int orderTotalAmount;
	private String orderNum;
	private Date orderDate;
	private String orderStatus;
	
	private MemberDTO member;
	
}
