package com.greedy.coffee.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.coffee.admin.service.AdminService;
import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.event.dto.EventDTO;
import com.greedy.coffee.event.service.EventService;
import com.greedy.coffee.order.dto.OrderDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final EventService eventService;
	private final AdminService adminService;
	public AdminController(EventService eventService,  AdminService adminService) {
		this.eventService = eventService;
		this.adminService = adminService;
	}
	
	@GetMapping("/adminpage")
	public String admin() {
		
		return "admin/adminpage";
	}	
	
	@GetMapping("list")
	public String eventBoard(@RequestParam(defaultValue="1") int page, Model model) {
		
		Page<EventDTO> eventList = eventService.selectBoardList(page);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(eventList);
		log.info("[eventBoard] eventList : {}", eventList);
		model.addAttribute("eventList", eventList);
		
		return "admin/eventBoard";
	} 
	
	
	@GetMapping("orderStatus")
	public String orderStatus(@RequestParam(defaultValue="1") int page, Model model) {
		
		Page<OrderDTO> orderList = adminService.selectOrderStatus(page);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(orderList);
		log.info("[orderStatus] orderList : {}", orderList);
		model.addAttribute("orderList", orderList);
		
		return "admin/orderStatus";
	}
	
	@GetMapping("/change")
	public String staustChange(@ModelAttribute OrderDTO changeOrd) {
		
		adminService.staustChange(changeOrd);
		
		return"redirect:/";
	}
}
