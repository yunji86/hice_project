package com.greedy.coffee.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class membercontroller {

	/* 회원정보수정 화면 이동 */
	@GetMapping("/userpage")
	public String goModifyMember(){
		
		return "mypage/userpage";
	}
	
	
	
	
	
	
	@GetMapping("/mybag")
	public void mybagPage(){
	}
	
	@GetMapping("/mypost")
	public void mypostPage(){
	}
	
	@GetMapping("/myorder")
	public void myorderPage(){
	}

	
	
	
	
}
