package com.greedy.coffee.reply.dto;

import java.sql.Date;

import com.greedy.coffee.member.dto.MemberDTO;

import lombok.Data;

@Data
public class ReplyDTO {
	
	private Long replyNo;
	private String replyContents;
	private String replyStatus;
	private Date replyDate;
	private Date replyEditDate;
	private Date replyDeleteDate;
	private MemberDTO writer;
	private Long revCode;
	private Long qnaCode;
<<<<<<< HEAD
	
=======

>>>>>>> refs/remotes/origin/Zoo
}
