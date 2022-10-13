package com.greedy.coffee.qna.service;
  
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.greedy.coffee.qna.dto.QnaDTO;
import com.greedy.coffee.qna.entity.Qna;
import com.greedy.coffee.qna.repository.QnaRepository;
import com.greedy.coffee.reply.dto.ReplyDTO;
import com.greedy.coffee.reply.repository.ReplyRepository;
  
public class QnaService {
  
	public static final int TEXT_PAGE_SIZE = 10;
	public static final String SORT_BY = "qnaCode";
	public static final String ACTIVE_STATUS = "Y";
  
	private final QnaRepository qnaRepository;
	private final ReplyRepository replyRepository;
	public final ModelMapper modelMapper;
  
	public QnaService(QnaRepository qnaRepository, ReplyRepository replyRepository, ModelMapper modelMapper) {
  
		this.qnaRepository = qnaRepository;
		this.replyRepository = replyRepository;
		this.modelMapper = modelMapper;
  
	}
  
	public Page<QnaDTO> selectQnaList(int page, String searchValue) {
		
		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Qna> QnaList = null;
  
		if(searchValue != null && !searchValue.isEmpty()) {
			QnaList = qnaRepository.findBySearchValue(ACTIVE_STATUS, searchValue, pageable); 
		} else {
			QnaList = qnaRepository.findByQnaStatus(ACTIVE_STATUS, pageable);
		}
		
		return QnaList.map(qna -> modelMapper.map(qna, QnaDTO.class));

	}
	
	public QnaDTO selectQnaDetail(Long qnaCode) {
		
		Qna qna = qnaRepository.findByQnaCodeAndQnaStatus(qnaCode, ACTIVE_STATUS);
		
		return modelMapper.map(qna, QnaDTO.class);
		
	}
	
	public void registQna(QnaDTO qna) {
		
	}
	
	public void removeQna(QnaDTO removeQna) {
		
	}
	
	public void registReply(ReplyDTO registReply) {
		
	}
	
	public List<ReplyDTO> loadReply(ReplyDTO loadReply) {
		
		return "asdf";
		
	}
	
	public void removeReply(ReplyDTO removeReply) {
		
	}
	
	
	
	
}
 