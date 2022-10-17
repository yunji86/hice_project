package com.greedy.coffee.cart.controller;

import java.util.Map;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.coffee.cart.dto.CartDTO;
import com.greedy.coffee.cart.dto.CartProDTO;
import com.greedy.coffee.cart.service.CartService;
import com.greedy.coffee.common.Pagenation;
import com.greedy.coffee.common.PagingButtonInfo;
import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.member.service.AuthenticationService;
import com.greedy.coffee.mypage.dto.OrderDTO;
import com.greedy.coffee.mypage.service.MypageService;
import com.greedy.coffee.product.dto.ProDTO;
import com.greedy.coffee.qna.dto.QnaDTO;
import com.greedy.coffee.qna.service.QnaService;
import com.greedy.coffee.review.Service.RevBoardService;
import com.greedy.coffee.review.dto.RevBoardDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {

	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@PostMapping("/insert")
	public String insertCart(@RequestParam Map<String, Object> map) {
		log.info("[CartController] =======================================");
		log.info("[Cart insertCart] request : {}", map);
		
		//map.get("")
		
		
		return "redirect:/mypage/mybag";
	}

	@GetMapping("/delete")
	public String deleteCart(@RequestParam(required = false, defaultValue = "0") Long cartCode,
			@RequestParam(required = false, defaultValue = "") String cartCodeList) {
		// 로그인 검증 & 본인 주문 확인 추가하기
		log.info("[CartController] =======================================");
		log.info("[Cart deleteCart] request : {}", cartCode);
		log.info("[Cart deleteCart] request : {}", cartCodeList);
		String[] delList;
		if (cartCode != 0) {
			cartService.deleteCart(cartCode);
		} else if (cartCodeList != "" || !cartCodeList.isEmpty() ) {
			if (cartCodeList.contains(",")) {
				delList = cartCodeList.split(",");
				log.info("[Cart deleteCart] request delList: {}", delList);
				for (String code : delList) {
					log.info("[Cart deleteCart] request in for: {}", code);
					cartService.deleteCart(Long.parseLong(code));
				}
			}
		} else {
			return "redirect:/";
		}

		return "redirect:/mypage/mybag";
	}

	@GetMapping("/order")
	public String orderCart(@RequestParam(required = false, defaultValue = "0") Long cartCode,
			@RequestParam(required = false, defaultValue = "") String cartCodeList) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		log.info("principal {}", auth.getPrincipal());
		MemberDTO member = (MemberDTO) auth.getPrincipal();
		
		
		log.info("[CartController] =======================================");
		log.info("[Cart orderCart] request : {}", cartCode);
		log.info("[Cart orderCart] request : {}", cartCodeList);
		String[] orderCart;
		if (cartCode != 0) {
			cartService.orderCart(cartCode, member);
		} else if (cartCodeList != "" || !cartCodeList.isEmpty() ) {
			if (cartCodeList.contains(",")) {
				orderCart = cartCodeList.split(",");
				log.info("[Cart orderCart] request orderList: {}", orderCart);
				for (String code : orderCart) {
					log.info("[Cart orderCart] request in for: {}", code);
					cartService.orderCart(Long.parseLong(code), member);
				}
			}
		} else {
			return "redirect:/";
		}

		return "redirect:/mypage/myorder";
	}

	
}
