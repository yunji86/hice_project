package com.greedy.coffee.order.dto;

import java.sql.Date;

import com.greedy.coffee.member.dto.MemberDTO;

import lombok.Data;

@Data
public class OrderDTO {

	private Long ordCode;
	private String ordName;
	private int ordPhone;
	private MemberDTO member;
	private String ordAddress;
	private int ordTotalAmount;
	private String ordStatus;
	private int ordNum;
	private Date ordDate;
	private int ordType;
	private int quantity;
	
}
