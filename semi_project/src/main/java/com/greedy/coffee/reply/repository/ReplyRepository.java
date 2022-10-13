package com.greedy.coffee.reply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.coffee.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

	Reply findByReplyNo(Long replyNo);
	
	List<Reply> findByQnaCodeandReplyStatus(Long qnaCode, String replyStatus);
	
}
