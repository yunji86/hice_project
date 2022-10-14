package com.greedy.coffee.qna.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.coffee.qna.entity.Qna;

public interface  QnaRepository extends JpaRepository<Qna, Long>{

	Page<Qna> findByQnaStatus(String qnaStatus, Pageable pageable);
	
	@Query("SELECT q " +
			"FROM Qna q " +
		"WHERE q.qnaStatus = :qnaStatus " +
			"And (q.qnaTitle LIKE '%' || :searchValue || '%' " +
			"OR q.qnaContent LIKE '%' || :searchValue || '%')")
	Page<Qna> findBySearchValue(@Param("qnaStatus") String activeStatus, @Param("searchValue") String searchValue, Pageable pageable);

	Qna findByQnaCodeAndQnaStatus(Long qnaCode, String activeStatus);
	
	Qna findByQnaTitleAndQnaContent(String qnaTitle, String qnaContent);

	Qna findByQnaCode(Long qnaCode);

}
