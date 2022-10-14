/*package com.greedy.coffee.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.coffee.admin.service.EventBoardService;
import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class EventBoardController {
	
	private final EventBoardService eventBoardService;
	
	public EventBoardController(EventBoardService eventBoardService) {
		this.eventBoardService = eventBoardService;
	}
	@GetMapping("list")
	public String eventBoard(@RequestParam(defaultValue="1") int page, Model model) {
		
		Page<EevenDTO> eventList = eventBoardService.selectBoardList(page);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(eventList);
		model.addAttribute("eventList", eventList);
		
		return "admin/eventBoard";
	} 
	
}*/
