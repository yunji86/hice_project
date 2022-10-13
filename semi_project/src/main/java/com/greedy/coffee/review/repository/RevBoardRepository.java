package com.greedy.coffee.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.coffee.review.entity.RevBoard;


public interface RevBoardRepository extends JpaRepository<RevBoard, Long> {

	@Query("SELECT r "+
			 "FROM RevBoard r " +
			"WHERE r.revStatus = :revStatus " +
			  "AND (r.revTitle LIKE '%' || :revSearchValue || '%' " +
			  "OR r.revContents LIKE '%' || :revSearchValue || '%')")
	Page<RevBoard> findByRevSearchValue(@Param("revStatus") String revStatus, @Param("revSearchValue") String revSearchValue, Pageable pagable);

	RevBoard findByRevCode(Long revCode);

	

	Page<RevBoard> findByRevStatus(String acitveStatus, Pageable pagable);
	
	
	RevBoard findByRevCodeAndRevStatus(Long revCode, String acitveStatus);

	

	
	
	

	

	

	

	




	

	




	
			  

	

}
