package com.greedy.coffee.product.dto;


import java.util.List;

import com.greedy.coffee.file.dto.FileDTO;
import com.greedy.coffee.member.dto.MemberDTO;

import lombok.Data;

@Data
public class ProDTO {
	
	private Long proCode;
	private String proType;
	private String proName;
	private int proPrice;
	private String proContent;
	private int proCount;
	private String proStatus;
	private MemberDTO writer;
	private List<FileDTO> fileList;
	
}
