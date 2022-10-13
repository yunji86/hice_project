package com.greedy.coffee.qna.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.greedy.coffee.member.dto.MemberDTO;

public class Qna {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REV_SEQ_GENERATOR")
	@Column(name = "QNA_CODE")
	private Long qnaCode;
	
	@Column(name = "QNA_TITLE")
	private String qnaTitle;
	
	@Column(name = "QNA_CONTENT")
	private String qnaContents;
	
	@Column(name = "QNA_DATE")
	private Date qnaDate;
	
	@Column(name = "QNA_EDIT_DATE")
	private Date qnaEditDate;
	
	@Column(name = "QNA_DELETE_DATE")
	private Date qnaDeleteDate;
	
	@Column(name = "QNA_STATUS")
	private String qnaStatus;
	
	@Column(name = "QNA_WRITER")
	private MemberDTO writer;
	
}
