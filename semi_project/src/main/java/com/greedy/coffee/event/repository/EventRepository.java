package com.greedy.coffee.event.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import com.greedy.coffee.event.entity.Event;
import com.greedy.coffee.member.entity.Member;


public interface EventRepository extends JpaRepository<Event, Long> {

	Optional<Event> findByEveCodeAndMemId(Long eveCode, Member member);

	
	Page<Event> findByEveStatus(String activeStatus, Pageable pageable);
	

}