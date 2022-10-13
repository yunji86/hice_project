package com.greedy.coffee.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.coffee.reply.entity.Reply;


public interface RevReplyRepository extends JpaRepository<Reply, Long>{

	

	Reply findByReplyNo(Long replyNo);

	List<Reply> findByRevCodeAndReplyStatus(Long revCode, String acitveStatus);



	
	


}
