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
	
	public MypageService(MemberRepository memberRepository, ModelMapper modelMapper, RevBoardRepository revBoardRepository, QnaRepository qnaRepository) {
		
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		this.revBoardRepository = revBoardRepository;
		this.qnaRepository = qnaRepository;
		
		
	}
	
	public static final int REV_PAGE_SIZE = 9;
	public static final String SORT_BY = "revCode";
	public static final String ACITVE_STATUS = "Y";
	public static final String DELETE_STATUS = "N";
	public static final int PHOTO_BOARD_TYPE = 1;
	public static final String REV_SORT_BY = "revCount";
	public static final String Q_SORT_BY = "qnaCode";
	
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
	
}
