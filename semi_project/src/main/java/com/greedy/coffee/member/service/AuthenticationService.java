package com.greedy.coffee.member.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.member.entity.Member;
import com.greedy.coffee.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AuthenticationService implements UserDetailsService {

	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public AuthenticationService(MemberRepository memberRepository, ModelMapper modelMapper) {
		
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
		
	}
	
	/* Repository 통해 DB로 부터 탈퇴안하고 존재중인 회원 정보 가져오기 */
	@Override
	public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException {
		
		log.info(" ========== AuthenticationService - memId : " + memId);
		
		// 해당 문구는 Filter의 Handler 내에 자체적으로 도는거라 눈으로 확인 불가능. 문구 확인하고 싶으면 Handler에서 직접 처리해야 볼 수 있음.
		Member selectedMember = memberRepository.findByMemIdAndMemStatus(memId, "Y").orElseThrow( () -> new UsernameNotFoundException("존재하지 않는 회원입니다.") );
		
		MemberDTO member = modelMapper.map(selectedMember, MemberDTO.class);
		
	    log.info(" ========== AuthenticationService - member : " + member);
	    
	    return member;
		
	}
	
}
