package com.greedy.coffee.qna.dto;

import java.sql.Date;

import com.greedy.coffee.member.dto.MemberDTO;

import lombok.Data;

@Data
public class QnaDTO {

<<<<<<< HEAD
	private Long qnaCode;
	private String qnaTitle;
	private String qnaContents;
	private Date qnaDate;
	private Date qnaEditDate;
	private Date qnaDeleteDate;
	private String qnaStatus;
=======
	private Long revCode;
	private String revTitle;
	private String revContents;
	private Date revDate;
	private Date revEditDate;
	private Date revDeleteDate;
	private String revStatus;
>>>>>>> refs/remotes/origin/Zoo
	private MemberDTO writer;
	
}
