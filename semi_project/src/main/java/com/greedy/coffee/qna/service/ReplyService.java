package com.greedy.coffee.qna.service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.coffee.qna.dto.QnaDTO;
import com.greedy.coffee.qna.entity.Qna;
import com.greedy.coffee.qna.repository.QnaReplyRepository;
import com.greedy.coffee.reply.dto.ReplyDTO;
import com.greedy.coffee.reply.entity.Reply;

@Service
@Transactional
public class ReplyService {
  
	private final QnaReplyRepository qnaReplyRepository;
	public final ModelMapper modelMapper;
  
	public ReplyService(QnaReplyRepository qnaReplyRepository, ModelMapper modelMapper) {
  
		this.qnaReplyRepository = qnaReplyRepository;
		this.modelMapper = modelMapper;
		
	}
	
	public void registQnaReply(ReplyDTO registReply) {
		
		qnaReplyRepository.save(modelMapper.map(registReply, Reply.class));
		
		Reply foundReply = qnaReplyRepository.findByReplyNo(registReply.getReplyNo());
		foundReply.setReplyDate(new Date(System.currentTimeMillis()));
		
	}
	
	public List<ReplyDTO> loadQnaReply(ReplyDTO loadReply, String ACTIVE_STATUS) {
		
		List<Reply> replyList = qnaReplyRepository.findByReplyNoAndReplyStatus(loadReply.getReplyNo(), ACTIVE_STATUS);
		
		return replyList.stream().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());
		
	}
	
	public void removeQnaReply(ReplyDTO removeReply) {
		
		Reply foundReply = qnaReplyRepository.findByReplyNo(removeReply.getReplyNo());
		foundReply.setReplyDeleteDate(new Date(System.currentTimeMillis()));
		foundReply.setReplyStatus("N");
		
	}
	
	public void modifyQnaReply(ReplyDTO modifyReply) {
		
		Reply foundReply = qnaReplyRepository.findByReplyNo(modifyReply.getReplyNo());
		foundReply.setReplyEditDate(new Date(System.currentTimeMillis()));
		foundReply.setReplyStatus("N");
		
	}
	
}
 