package com.greedy.coffee.qna.dto;

import java.sql.Date;

import com.greedy.coffee.member.dto.MemberDTO;

import lombok.Data;

@Data
public class QnaDTO {


	private Long qnaCode;
	private String qnaTitle;
	private String qnaContent;
	private Date qnaDate;
	private Date qnaEditDate;
	private Date qnaDeleteDate;
	private String qnaStatus;
	private MemberDTO writer;
	
}
