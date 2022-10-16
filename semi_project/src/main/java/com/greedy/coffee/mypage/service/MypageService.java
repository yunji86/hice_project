package com.greedy.coffee.mypage.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.member.entity.Member;
import com.greedy.coffee.member.repository.MemberRepository;
import com.greedy.coffee.mypage.dto.OrderDTO;
import com.greedy.coffee.mypage.entity.Myorder;
import com.greedy.coffee.mypage.repository.OrderRepository;
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
public class MypageService {
	
	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	private final RevBoardRepository revBoardRepository;
	private final QnaRepository qnaRepository;
	private final OrderRepository orderRepository;
	
	public MypageService(MemberRepository memberRepository, ModelMapper modelMapper, RevBoardRepository revBoardRepository, QnaRepository qnaRepository, OrderRepository orderRepository) {
		
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		this.revBoardRepository = revBoardRepository;
		this.qnaRepository = qnaRepository;
		this.orderRepository = orderRepository;
		
		
	}
	
	public static final int ORD_PAGE_SIZE = 10;
	public static final String SORT_BY = "revCode";
	public static final String ACITVE_STATUS = "Y";
	public static final String DELETE_STATUS = "N";
	public static final int PHOTO_BOARD_TYPE = 1;
	public static final String REV_SORT_BY = "revCount";
	public static final String Q_SORT_BY = "qnaCode";
	public static final String O_SORT_BY = "orderCode";
	public static final String ORDER_CANCEL = "주문취소";
	public static final String ORDER_BACK = "교환반품진행중";
	
	
	public void registMember(MemberDTO member) {
		
		memberRepository.save(modelMapper.map(member, Member.class));		
		
	}

	/*수정*/
	public void modifyMember(MemberDTO updateMember) {
		
//		수정하고 싶은 메소드만 셋터메소드로 
		Member savedMember = memberRepository.findByMemId(updateMember.getMemId());
		
//		저장되어있는 값을 멤버쪽에 설정한다 
		savedMember.setMemId(updateMember.getMemId());
		savedMember.setMemName(updateMember.getMemName());
//		savedMember.setMemPwd(updateMember.getMemPwd());
//		savedMember.setMemPhone(updateMember.getMemPhone());
		savedMember.setMemEmail(updateMember.getMemEmail());
		savedMember.setMemDate(updateMember.getMemDate());
		
	}
	
	/*탈퇴*/
	public void removeMember(MemberDTO member) {
		
    	Member savedMember = memberRepository.findByMemId(member.getMemId());
    	savedMember.setMemStatus("N");
		
	}
	
	public Page<RevBoardDTO> selectRevBoardListInMypage(MemberDTO loginMember ) {

		Pageable pagable = PageRequest.of(0, 5, Sort.by(REV_SORT_BY).descending());
		
		Member selectedMember = memberRepository.findByMemIdAndMemStatus(loginMember.getMemId(), "Y").orElseThrow( () -> new UsernameNotFoundException("존재하지 않는 회원입니다.") );
		Page<RevBoard>revBoardList = revBoardRepository.findByRevStatusAndWriter( ACITVE_STATUS, pagable, selectedMember);
		return revBoardList.map(Board -> modelMapper.map(Board, RevBoardDTO.class));
	}
	

	public Page<QnaDTO> selectQnaListInMypage(MemberDTO member) {
		Pageable pageable = PageRequest.of(0, 5, Sort.by(Q_SORT_BY).descending());
		Member selectedMember = memberRepository.findByMemIdAndMemStatus(member.getMemId(), "Y").orElseThrow( () -> new UsernameNotFoundException("존재하지 않는 회원입니다.") );
		Page<Qna> QnaList = qnaRepository.findByQnaStatusAndWriter(ACITVE_STATUS, pageable, selectedMember);
		
		return QnaList.map(qna -> modelMapper.map(qna, QnaDTO.class));

	}
	
	public Page<OrderDTO> selectOrderListInMypage(int page, MemberDTO member) {
		Pageable pageable = PageRequest.of(page-1, ORD_PAGE_SIZE, Sort.by(O_SORT_BY).descending());
		Member selectedMember = memberRepository.findByMemIdAndMemStatus(member.getMemId(), "Y").orElseThrow( () -> new UsernameNotFoundException("존재하지 않는 회원입니다.") );
		Page<Myorder> OrdList = orderRepository.findByMember(pageable, selectedMember);
		
		return OrdList.map(ord -> modelMapper.map(ord, OrderDTO.class));

	}

	public void cancelOrder(OrderDTO orderDTO) {
		
		Myorder order = orderRepository.findByOrderCode(orderDTO.getOrderCode()).orElseThrow( () -> new UsernameNotFoundException("존재하지 않는 회원입니다.") );
		
		order.setOrderStatus(ORDER_CANCEL);
	
		log.info("cancelOrder {}", order);
	}

	public void takeBackOrder(OrderDTO orderDTO) {
		Myorder order = orderRepository.findByOrderCode(orderDTO.getOrderCode()).orElseThrow( () -> new UsernameNotFoundException("존재하지 않는 회원입니다.") );
		
		order.setOrderStatus(ORDER_BACK);
	
		log.info("takeBackOrder {}", order);
		
	}
	
}
