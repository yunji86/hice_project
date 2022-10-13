package com.greedy.coffee.review.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.reply.dto.ReplyDTO;
import com.greedy.coffee.review.Service.RevReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/reply")
public class RevReplyController {
	
	private final RevReplyService revReplyService;
	
	public RevReplyController( RevReplyService revReplyService) {
		this.revReplyService = revReplyService;
		
	}
	

	
	@PostMapping("/registRevReply")
	public ResponseEntity<String> registRevReply(@RequestBody ReplyDTO registRevReply,
												 @AuthenticationPrincipal MemberDTO member) {
		
		log.info("[RevReplyController] ========================================= ");
		log.info("registRevReply : {}", registRevReply);
		
		registRevReply.setWriter(member);
		revReplyService.registRevReply(registRevReply);
		
		return ResponseEntity.ok("댓글 등록 완료");
		
	}
	
	@GetMapping("/loadRevReply")
	public ResponseEntity<List<ReplyDTO>> loadRevReply(ReplyDTO loadRevReply){
		
		log.info("[RevReplyController] ========================================= ");
		log.info("loadRevReply : {} ", loadRevReply);
		
		List<ReplyDTO> revReplyList = revReplyService.loadRevReply(loadRevReply);
		
		log.info("revReplyList 값 : {} ", revReplyList);
		
		return ResponseEntity.ok(revReplyList);
		
	}
	
	@PostMapping("/removeReply")
	public ResponseEntity<String> removeRevReply(@RequestBody ReplyDTO removeReply){
		
		revReplyService.removeRevReply(removeReply);
		
		return ResponseEntity.ok("댓글 삭제 완료");
	}
	
	
}
