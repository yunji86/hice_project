package com.greedy.coffee.qna.controller;

  
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.qna.dto.QnaDTO;
import com.greedy.coffee.qna.service.QnaService;

import lombok.extern.slf4j.Slf4j;
  
@Slf4j
@Controller  
@RequestMapping("/qna")
public class QnaController {
  
	private final QnaService qnaService;
	private final MessageSourceAccessor messageSourceAccessor;
  
	public QnaController(QnaService qnaService, MessageSourceAccessor messageSourceAccessor) { 
		
		this.qnaService = qnaService;
		this.messageSourceAccessor = messageSourceAccessor;
			
	}
	
	@GetMapping("qnaList")
	public String qnaList(@RequestParam(defaultValue = "1") int page, @RequestParam(required = false) String searchValue, Model model) {
		
		log.info("qnaList is working ");
		log.info("qnaList page : {}", page);		
		log.info("qnaList searchValue : {}", searchValue);
		
		Page<QnaDTO> qnaList = qnaService.selectQnaList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(qnaList);
		
		log.info("qnaList qnaList : {}", qnaList.getContent());
		log.info("qnaList paging : {}", paging);
		
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("paging", paging);
		
		if(searchValue != null && !searchValue.isEmpty()) {
			
			model.addAttribute("searchValue", searchValue);
			
		}
  
		log.info("qnaList is worked ");
		
		return "qna/qnaList";
		
	}

	@GetMapping("/qnaRegist")
	public String goQnaRegist() {
		
		return "qna/qnaRegist";
		
	}
	
	@PostMapping("/qnaRegist")
	public String registQna(QnaDTO qna, @AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {
		
		log.info("qnaRegist is working ");
		
		qna.setWriter(member);
		log.info("qnaRegist qna : {} ", qna);
		
		qnaService.registQna(qna);
		
		log.info("qnaRegist qna worked : ", qna);
		
		rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("qna.regist"));
		
		return "redirect:/qna/qnaList";
		
	}
	
	@GetMapping("/qnaDetail")
	public String selectQnaDetail(Model model, Long qnaCode) {
		
		log.info("qnaDetail is working ");
		log.info("qnaDetail qnaCode : {} ", qnaCode);
		
		QnaDTO qna = qnaService.selectQnaDetail(qnaCode);
		
		log.info("qnaDetail qna : {} ", qna);
		
		model.addAttribute("qna", qna);
		
		log.info("qnaDetail is worked ");
		
		return "qna/qnaDetail";
		
	}
	
	@GetMapping("/qnaModify")
	public String goQnaModify(Model model, Long qnaCode) {
		
		log.info("GETqnaModify is working ");
		log.info("GETqnaModify qnaCode : {} ", qnaCode);
		
		QnaDTO qna = qnaService.modifyQna(qnaCode);
		
		log.info("GETqnaModify qna : {} ", qna);
		
		model.addAttribute("qna", qna);
		
		log.info("GETqnaModify is worked ");
		
		qna.setQnaCode(qna.getQnaCode());
		
		log.info("GETqnaModify qnaCode : {} ", qnaCode);
		
		return "qna/qnaModify";
		
	}
	
	@PostMapping("/qnaModify")
	public String qnaModify(@ModelAttribute QnaDTO qna, RedirectAttributes rttr) {
		
		log.info("qnaModify is working ");

		log.info("qnaModify qna : {} ", qna.getQnaTitle());
		
		// qna.setWriter(member);
		qnaService.modifyQna(qna);
		
		log.info("qnaModified qna : {} ", qna);
		
		
		rttr.addAttribute("message", messageSourceAccessor.getMessage("qna.modify"));
		
		log.info("qnaModify is worked ");
		
		return "redirect:/qna/qnaList";
		
	}
	

	@PostMapping("/qnaRemove")
	public String removeQna(@RequestBody Long qnaRemove, RedirectAttributes rttr) {
		
		log.info("qnaRemove is working ");
		log.info("qnaRemove qnaRemove : {}", qnaRemove);
		
		qnaService.removeQna(qnaRemove);
		
		log.info("qnaRemove is worked ");
		
		rttr.addFlashAttribute("Message", messageSourceAccessor.getMessage("qna.remove"));
		
		return "redirect:/qna/list";
		
	}
	
}