package com.greedy.coffee.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.coffee.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{


	Page<Notice> findByNotStatus(String notStatus, Pageable pageable);

	Notice findByNotCodeAndNotStatus(Long notCode, String activeStatus);

	Notice findByNotCode(Long notCode);

	
}
