package com.greedy.coffee.qna.controller;

import java.util.List;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.qna.dto.QnaDTO;
import com.greedy.coffee.qna.service.ReplyService;
import com.greedy.coffee.reply.dto.ReplyDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller  
@RequestMapping("/qna")
public class ReplyController {
  
	private final ReplyService replyService;
	private final MessageSourceAccessor messageSourceAccessor;
  
	public ReplyController(ReplyService replyService, MessageSourceAccessor messageSourceAccessor) { 
		
		this.replyService = replyService;
		this.messageSourceAccessor = messageSourceAccessor;
			
	}
	
	@PostMapping("/replyRegist")
	public String registQna(@RequestBody ReplyDTO reply, @AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {
		
		log.info("replyRegist is working ");
		
		reply.setWriter(member);
		log.info("replyRegist replydto : {} ", reply);
		replyService.registQnaReply(reply);
		
		log.info("qnaRegist qna worked : ", reply);
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("reply.regist"));
		
		return "redirect:/qna/qnaList";
		
	}
	
	@GetMapping("/replyDetail")
	public String selectReplyDetail(ReplyDTO loadReply, String ACTIVE_STATUS, Model model) {
		
		log.info("replyDetail is working ");
		log.info("replyDetail replyNo : {} ", loadReply);
		
		List<ReplyDTO> replyDetail = replyService.loadQnaReply(loadReply, ACTIVE_STATUS);
		
		log.info("replyDetail qna : {} ", replyDetail);
		
		model.addAttribute("replyDetail", replyDetail);
		
		log.info("replyDetail is worked ");
		
		return "qna/qnaDetail";
		
	}
	
	@GetMapping("/replyModify")
	public String goQnaModify(Model model, Long replyNo, ReplyDTO reply) {
		
		log.info("GETReplyModify is working ");
		log.info("GETReplyModify replyNo : {} ", replyNo);
		
		replyService.modifyQnaReply(reply);
		
		log.info("GETReplyModify replyNo : {} ", replyNo);
		
		model.addAttribute("replyNo", replyNo);
		
		log.info("GETReplyModify is worked ");
		
		reply.setReplyNo(reply.getReplyNo());
		
		log.info("GETReplyModify replyNo : {} ", replyNo);
		
		return "qna/qnaModify";
		
	}
	

	
	@PostMapping("/replyModify")
	public String replyModify(ReplyDTO reply, @AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {
		
		log.info("replyModify is working ");
		log.info("replyModify reply : {} ", reply);
		log.info("replyModify member : {} ", member);
		
		// qna.setWriter(member);
		replyService.registQnaReply(reply);
		
		rttr.addAttribute("message", messageSourceAccessor.getMessage("reply.modify"));
		
		log.info("replyModify is worked ");
		
		return "redirect:/qna/qnaList";
		
	}
	

	@PostMapping("/removeReply")
	public ResponseEntity<String> removeRevReply(@RequestBody ReplyDTO removeReply){
		
		replyService.removeQnaReply(removeReply);
		
		return ResponseEntity.ok("댓글 삭제 완료");
	}
	
}
