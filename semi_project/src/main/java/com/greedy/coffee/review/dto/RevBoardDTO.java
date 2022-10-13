package com.greedy.coffee.review.dto;

import java.sql.Date;
import java.util.List;

import com.greedy.coffee.file.dto.FileDTO;
import com.greedy.coffee.member.dto.MemberDTO;


import lombok.Data;

@Data
public class RevBoardDTO {
	
	private Long revCode;
	private String revTitle;
	private String revContents;
	private Date revDate;
	private Date revEditDate;
	private Date revDeleteDate;
	private String revStatus;
	private MemberDTO writer;
	//private ProDTO pro;
	private List<FileDTO> fileList;
	
}
