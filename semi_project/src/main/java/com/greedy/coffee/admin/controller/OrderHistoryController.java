package com.greedy.coffee.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.coffee.admin.service.OrderHistoryService;
import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.order.dto.OrderDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/admin")
public class OrderHistoryController {
	
	
	private final OrderHistoryService orderHistoryService;
	
	public OrderHistoryController(OrderHistoryService orderHistoryService) {
		this.orderHistoryService = orderHistoryService;
	}
	
	
	
	@GetMapping("/page")
	public String admin() {
		
		return "admin/orderHistoryList";
	}

	
	@GetMapping("/orderHistoryList")
	public String GoOrderSelect(@RequestParam(defaultValue="1") int page,
			@RequestParam(required=false) String searchValue, Model model) {
		
		log.info("page : {}", page);
		
		Page<OrderDTO> orderHistoryList = orderHistoryService.selectOrderHistory(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(orderHistoryList);
		
		log.info("orderHistoryList : {}", orderHistoryList.getContent());
		log.info("paging : {}", paging);
		
		model.addAttribute("orderHistoryList", orderHistoryList);
		model.addAttribute("paging", paging);
		if(searchValue != null && !searchValue.isEmpty()) {
			model.addAttribute("searchValue", searchValue);
		}
		
		return "admin/orderHistoryList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
