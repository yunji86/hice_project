package com.greedy.coffee.qna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.coffee.reply.entity.Reply;

public interface QnaReplyRepository extends JpaRepository<Reply, Long>{

	Reply findByReplyNo(Long replyNo);
	
	List<Reply> findByReplyNoAndReplyStatus(Long replyNo, String replyStatus);
	
}
