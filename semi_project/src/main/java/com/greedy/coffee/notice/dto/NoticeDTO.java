package com.greedy.coffee.notice.dto;

import java.sql.Date;

import com.greedy.coffee.member.dto.MemberDTO;
import lombok.Data;

@Data
public class NoticeDTO {

	private Long notCode;
	private String notTitle;
	private String notContent;
	private Date notDate;
	private Date editDate;
	private Date deleteDete;
	private String notStatus;
	private MemberDTO member;

}
