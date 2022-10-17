package com.greedy.coffee.cart.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.coffee.cart.dto.CartDTO;
import com.greedy.coffee.cart.dto.CartProDTO;
import com.greedy.coffee.cart.dto.MyCartDTO;
import com.greedy.coffee.cart.entity.Cart;
import com.greedy.coffee.cart.entity.CartPro;
import com.greedy.coffee.cart.repository.CartProRepository;
import com.greedy.coffee.cart.repository.CartRepository;
import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.member.entity.Member;
import com.greedy.coffee.member.repository.MemberRepository;
import com.greedy.coffee.mypage.dto.OrderDTO;
import com.greedy.coffee.mypage.entity.Myorder;
import com.greedy.coffee.mypage.repository.OrderRepository;
import com.greedy.coffee.product.repository.ProRepository;
import com.greedy.coffee.qna.dto.QnaDTO;
import com.greedy.coffee.qna.entity.Qna;
import com.greedy.coffee.qna.repository.QnaRepository;
import com.greedy.coffee.review.dto.RevBoardDTO;
import com.greedy.coffee.review.entity.RevBoard;
import com.greedy.coffee.review.repository.RevBoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CartService {

	private final CartRepository cartRepository;
	private final CartProRepository cartProRepository;
	private final ProRepository proRepository;
	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	private final OrderRepository orderRepository;
	
	public CartService(CartRepository cartRepository, CartProRepository cartProRepository, ProRepository proRepository,
			MemberRepository memberRepository, ModelMapper modelMapper, OrderRepository orderRepository) {
		this.cartRepository = cartRepository;
		this.cartProRepository = cartProRepository;
		this.proRepository = proRepository;
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		this.orderRepository = orderRepository;
	}

	public static final int CART_PAGE_SIZE = 10;
	public static final String SORT_BY = "cartCode";
	public static final String ADDR = "서울시 강남구";
	public static final String ORDER_SUCC_STATUS = "주문진행중";

	public MyCartDTO selectCartList(int page, String memberId) {
		Pageable pageable = PageRequest.of(page - 1, CART_PAGE_SIZE, Sort.by(SORT_BY).descending());

		Member member = memberRepository.findByMemIdAndMemStatus(memberId, "Y")
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

		Page<CartProDTO> cartProDTOList = null;
		Page<CartPro> cartProList = null;

		List<CartProDTO> cartPros = new ArrayList<>();

		// 아이디로 매핑테이블 먼저 조회해야함
		// 카트 내역이 있는지 조회
		List<Cart> carts = cartRepository.findByMember(member);

		log.info("carts {}", carts);
		;

		if (carts.size() > 0) {
			for (Cart cart : carts) {
				CartPro cartPro = cartProRepository.findByCart(cart).orElse(null);
				log.info("cartPro {}", cartPro);
				if (cartPro != null) {
					cartPros.add(modelMapper.map(cartPro, CartProDTO.class));

				}
			}
		}

		int sum = getAllCartSumPrice(cartPros);
		
		final int start = (int) pageable.getOffset();
		final int end = Math.min((start + pageable.getPageSize()), cartPros.size());
		cartProDTOList = new PageImpl<>(cartPros.subList(start, end), pageable, cartPros.size());
			
		MyCartDTO dto = new MyCartDTO();
		dto.setPagingCartProDTO(cartProDTOList);
		dto.setSum(sum);
		
		return dto;
	}

	public int getAllCartSumPrice(List<CartProDTO> cartPros) {
		int price = 0;
		for (CartProDTO c : cartPros) {
			price += c.getProduct().getProPrice() * c.getCount();
		}
		return price;
	}
	
	

	public void insertCart(CartDTO cartDTO) {

	}


	public void deleteCart(Long cartProCode) {	
		
		log.info("deleteCart code{}", cartProCode);
		
		
		CartPro p = cartProRepository.findByCartProCode(cartProCode).orElse(null);
		
		log.info("deleteCart {}", p)
		;
		if(p != null) {
			cartProRepository.delete(p);
			cartRepository.delete(p.getCart());	
		}
	}

	

	public void orderCart(Long cartCode, MemberDTO memberDTO) {
		Member member = memberRepository.findByMemIdAndMemStatus(memberDTO.getMemId(), "Y")
				.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

			
			CartPro p = cartProRepository.findByCartProCode(cartCode).orElse(null);

			
			Myorder order = new Myorder();
			order.setOrderAddress(ADDR);
			order.setOrderDate(new Date(System.currentTimeMillis()));
			order.setOrderStatus(ORDER_SUCC_STATUS);
			order.setOrderNum(Integer.toString( p.getCount()));
			order.setOrderName(p.getProduct().getProName());
			order.setOrderTotalAmount(p.getCount()* p.getProduct().getProPrice());
			order.setOrderPhone(String.valueOf(member.getMemPhone()));		
			order.setCartpro(p);
			order.setMember(member);
			
			orderRepository.save(order);
			
		
	}
	
}
