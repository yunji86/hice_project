package com.greedy.coffee.member.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.member.entity.Member;
import com.greedy.coffee.member.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	
	public MemberService(MemberRepository memberRepository, ModelMapper modelMapper) {
		
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		
	}
	
	public boolean selectMemberById(String memId) {
		
		return memberRepository.findByMemIdAndMemStatus(memId, "Y").isPresent();
		
	}
	
	public void registMember(MemberDTO member) {
		
		memberRepository.save(modelMapper.map(member, Member.class));
		
	}

	
}
