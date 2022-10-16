package com.greedy.coffee.qna.service;
  
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.coffee.qna.dto.QnaDTO;
import com.greedy.coffee.qna.entity.Qna;
import com.greedy.coffee.qna.repository.QnaReplyRepository;
import com.greedy.coffee.qna.repository.QnaRepository;
import com.greedy.coffee.reply.dto.ReplyDTO;
import com.greedy.coffee.reply.entity.Reply;
 
@Service
@Transactional
public class QnaService {
  
	public static final int TEXT_PAGE_SIZE = 10;
	public static final String SORT_BY = "qnaCode";
	public static final String ACTIVE_STATUS = "Y";
  
	private final QnaRepository qnaRepository;
	private final QnaReplyRepository qnaReplyRepository;
	public final ModelMapper modelMapper;
  
	public QnaService(QnaRepository qnaRepository, QnaReplyRepository qnaReplyRepository, ModelMapper modelMapper) {
  
		this.qnaRepository = qnaRepository;
		this.qnaReplyRepository = qnaReplyRepository;
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
	
	public QnaDTO modifyQna(Long qnaCode) {
		
		Qna qna = qnaRepository.findByQnaCodeAndQnaStatus(qnaCode, ACTIVE_STATUS);
		
//		qna.setQnaTitle(qna.getQnaTitle());
//		
//		qna.setQnaContent(qna.getQnaContent());
		
		qna.setQnaEditDate(new Date(System.currentTimeMillis()));
		
		return modelMapper.map(qna, QnaDTO.class);
		
	}
	
	public void registQna(QnaDTO qna) {
		
		//qnaRepository.save(modelMapper.map(qna, Qna.class));
		
		// qna.setQnaDate(new Date(System.currentTimeMillis()));
		
		Qna postQna = qnaRepository.findByQnaCode(qna.getQnaCode());
		postQna.setQnaTitle(qna.getQnaTitle());
		postQna.setQnaContent(qna.getQnaContent());
		postQna.setQnaDate(new Date(System.currentTimeMillis()));
		
	}
	

	
	// 완료 버튼 누른 상태. 수정에서 누르고 이동한 상태임
	
	public void modifyQna(QnaDTO modifyQna) {
		
		Qna qna = qnaRepository.findByQnaCodeAndQnaStatus(modifyQna.getQnaCode(), ACTIVE_STATUS);
		
		//qna.setQnaCode(modifyQna.getQnaCode());
		
		qna.setQnaTitle(modifyQna.getQnaTitle());
		
		qna.setQnaContent(modifyQna.getQnaContent());
		
		//qna.setQnaEditDate(new Date(System.currentTimeMillis()));
		
		qna.setQnaStatus("N");
		
		//qnaRepository.save(modelMapper.map(modifyQna, Qna.class));
		
	}
	
	public void removeQna(Long qnaCode) {
		
		Qna qna = qnaRepository.findByQnaCode(qnaCode);
		
		qna.setQnaStatus("N");
		
		qna.setQnaDeleteDate(new Date(System.currentTimeMillis()));
		
	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	public void registQnaReply(ReplyDTO registReply) {
//		
//		qnaReplyRepository.save(modelMapper.map(registReply, Reply.class));
//		
//		Reply foundReply = qnaReplyRepository.findByReplyNo(registReply.getReplyNo());
//		foundReply.setReplyDate(new Date(System.currentTimeMillis()));
//		
//	}
//	
//	public List<ReplyDTO> loadQnaReply(ReplyDTO loadReply) {
//		
//		List<Reply> replyList = qnaReplyRepository.findByReplyNoAndReplyStatus(loadReply.getReplyNo(), ACTIVE_STATUS);
//		
//		return replyList.stream().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());
//		
//	}
//	
//	public void removeQnaReply(ReplyDTO removeReply) {
//		
//		Reply foundReply = qnaReplyRepository.findByReplyNo(removeReply.getReplyNo());
//		foundReply.setReplyDeleteDate(new Date(System.currentTimeMillis()));
//		foundReply.setReplyStatus("N");
//		
//	}
//	
//	public void modifyQnaReply(ReplyDTO modifyReply) {
//		
//		Reply foundReply = qnaReplyRepository.findByReplyNo(modifyReply.getReplyNo());
//		foundReply.setReplyEditDate(new Date(System.currentTimeMillis()));
//		foundReply.setReplyStatus("N");
//		
//	}

	
	
}
 