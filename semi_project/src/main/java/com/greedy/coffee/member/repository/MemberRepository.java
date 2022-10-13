package com.greedy.coffee.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.coffee.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	Optional<Member> findByMemIdAndMemStatus(String memId, String memStatus);
	 
}
