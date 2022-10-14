package com.greedy.coffee.mypage.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.member.entity.Member;
import com.greedy.coffee.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MypageService {
	
	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	
	public MypageService(MemberRepository memberRepository, ModelMapper modelMapper) {
		
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		
	}
	
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
	
	

	


	
	
	
	
	
}
