package com.greedy.coffee.event.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greedy.coffee.event.dto.EventDTO;
import com.greedy.coffee.event.service.EventService;
import com.greedy.coffee.member.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/event")
public class EventController {
	
	private final EventService eventServcie;
	
	public EventController(EventService eventServcie) {
		this.eventServcie = eventServcie;
	}
	
	@GetMapping("/eventPage")
	public void eventPage() {}
	
	@GetMapping("/eventRegist")
	public void eventRegist() {}
	
	@PostMapping("/eventRegist")
	public String registEvent(@ModelAttribute EventDTO event, @AuthenticationPrincipal MemberDTO member) {
	
		log.info(" ========== EventController registEvent request Event : " + event);
		
		eventServcie.registEvent(event);
		
		return "redirect:/event/eventEnd";
		
	}
	
	@GetMapping("/eventEnd")
	public String goEventEnd() {
		
		return "event/eventEnd";
		
	}

}


