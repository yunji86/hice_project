package com.greedy.coffee.best.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greedy.coffee.best.dto.BeansDTO;
import com.greedy.coffee.best.service.BestService;
import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/best")
@Controller
public class BestController {
	
	
	//의존성 
	private final BestService bestService;
	
	public BestController(BestService bestService) {
		this.bestService = bestService;
	}

	
	//select List
	@GetMapping("/bestList")
	public String bestList(@RequestParam(defaultValue="1") int page, 
			@RequestParam(required=false) String searchValue, Model model) {
		
		log.info("page : {}", page);
		log.info("searchValue : {}", searchValue);
		
		Page<BeansDTO> bestList = bestService.selectBestList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(bestList);
		
		log.info("beanList : {}", bestList.getContent());
		log.info("paging : {}", paging);

		model.addAttribute("bestList", bestList);
		model.addAttribute("paging", paging);
		
		if(searchValue != null && !searchValue.isEmpty()) {
			model.addAttribute("searchValue", searchValue);
		}
		
		return "best/bestList";
	}
	
	
	
	
	
	
}
