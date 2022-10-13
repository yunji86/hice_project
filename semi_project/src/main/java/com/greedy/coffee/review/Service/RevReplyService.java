package com.greedy.coffee.review.Service;

import java.util.List;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.coffee.reply.dto.ReplyDTO;
import com.greedy.coffee.reply.entity.Reply;
import com.greedy.coffee.review.repository.RevReplyRepository;

@Service
@Transactional
public class RevReplyService {

	private final ModelMapper modelMapper;
	private final RevReplyRepository revReplyRepository;
	
	@Autowired
	public RevReplyService(ModelMapper modelMapper, RevReplyRepository revReplyRepository) {
		
		this.modelMapper = modelMapper;
		this.revReplyRepository = revReplyRepository;
	}	
		
	public static final String ACITVE_STATUS = "Y";
	
	public void registRevReply(ReplyDTO registRevReply) {
		
		revReplyRepository.save(modelMapper.map(registRevReply, Reply.class));
		
	}
	
	

	public List<ReplyDTO> loadRevReply(ReplyDTO loadRevReply) {
		
		List<Reply> revReplyList = revReplyRepository.findByRevCodeAndReplyStatus(loadRevReply.getRevCode(), ACITVE_STATUS);
		
		return revReplyList.stream().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());
	}



	public void removeRevReply(ReplyDTO removeReply) {
		
		 Reply foundReply = revReplyRepository.findByReplyNo(removeReply.getReplyNo());
		 
		 foundReply.setReplyStatus("N");
				 
		
	}






}