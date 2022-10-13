package com.greedy.coffee.qna.controller;
  
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
  
import com.greedy.coffee.qna.service.QnaService;
  
import lombok.extern.slf4j.Slf4j;
  
@Slf4j
@Controller  
@RequestMapping("/qna")
public class QnaController {
  
	private final QnaService qnaService;
	private final MessageSourceAccessor messageSourceAccor;
  
	public QnaController(QnaService qnaService, MessageSourceAccessor messageSourceAccor) { 
		
		this.qnaService = qnaService;
		this.messageSourceAccor = messageSourceAccor;
			
	}
	
	@GetMapping("")	
  
  }
 