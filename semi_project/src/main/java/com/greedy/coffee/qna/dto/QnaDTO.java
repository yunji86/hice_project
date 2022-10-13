package com.greedy.coffee.qna.dto;

import java.sql.Date;

import com.greedy.coffee.member.dto.MemberDTO;

import lombok.Data;

@Data
public class QnaDTO {

	private Long revCode;
	private String revTitle;
	private String revContents;
	private Date revDate;
	private Date revEditDate;
	private Date revDeleteDate;
	private String revStatus;
	private MemberDTO writer;
	
}
