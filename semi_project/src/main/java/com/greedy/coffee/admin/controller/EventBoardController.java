package com.greedy.coffee.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.event.dto.EventDTO;
import com.greedy.coffee.event.service.EventService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class EventBoardController {
	
	private final EventService eventService;
	
	public EventBoardController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@GetMapping("list")
	public String eventBoard(@RequestParam(defaultValue="1") int page, Model model, Long eveCode) {
		
		Page<EventDTO> eventList = eventService.selectBoardList(page, eveCode);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(eventList);
		log.info("[eventBoard] eventList : {}", eventList);
		model.addAttribute("eventList", eventList);
		
		return "admin/eventBoard";
	} 
	
}
