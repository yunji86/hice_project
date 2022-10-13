package com.greedy.coffee.store.controller;



import org.springframework.context.support.MessageSourceAccessor;

import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.store.dto.StoreDTO;
import com.greedy.coffee.store.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/store")
public class StoreController {
	
	//의존성 
	private final StoreService storeService;
	private final MessageSourceAccessor messageSourceAccesor;
	
	public StoreController(StoreService storeService, MessageSourceAccessor messageSourceAccesor) {
		this.storeService = storeService;
		this.messageSourceAccesor = messageSourceAccesor;
	}

	
	
	//storeList 보여주기
	@GetMapping("/storeList")
	public String storeList(@RequestParam(defaultValue="1") int page, 
			@RequestParam(required=false) String searchValue, Model model) {

		Page<StoreDTO> storeList = storeService.selectStoreList(page, searchValue);
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(storeList);
		
		log.info("storeList : {}", storeList.getContent());

		model.addAttribute("storeList", storeList);
		model.addAttribute("paging", paging);
		if(searchValue != null && !searchValue.isEmpty()) {
			model.addAttribute("searchValue", searchValue);
		}
		
		
		return "store/storeList";		
	}
	
	
	
	//sotre 작성하기
	@GetMapping("/storeRegist")
	public String goRegistStore() {
		
		return "/store/storeRegist";
	}
	
	@PostMapping("/storeRegist")
	public String registStore(StoreDTO store, @AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {
		
		store.setMemId(member);
		storeService.registStore(store);
		
		rttr.addFlashAttribute("message", messageSourceAccesor.getMessage("store.regist"));
		
		return "redirect:/store/storeList";
	}
	
	
	
	//sotre 수정
	@GetMapping("/storeUpdate")
	public String goUpdateStore(){
        
        return "store/storeUpdate";
	}
	
	@PostMapping("/storeUpdate")
	public ModelAndView updateStore(ModelAndView mv, StoreDTO store, RedirectAttributes rttr) {
		
		storeService.updateStore(store);
		rttr.addFlashAttribute("message", messageSourceAccesor.getMessage("store.update"));
		
		mv.setViewName("redirect:/store/storeList");
		
		return mv;
		
	}
	
	
	
	//store 삭제
	@GetMapping("/storeRemove")
	public String goRemoveStore(){
		
		return "store/storeRemove";
	}
	
	@PostMapping("/storeRemove")
	public String removeStore(@AuthenticationPrincipal MemberDTO member, StoreDTO removeStore, @RequestParam Long stoCode, RedirectAttributes rttr){
		
		storeService.removeStore(removeStore);
		rttr.addFlashAttribute("message", messageSourceAccesor.getMessage("store.remove"));
		
		return "redirect:/store/storeList";
		
	}
	
	
	
	
	
}
