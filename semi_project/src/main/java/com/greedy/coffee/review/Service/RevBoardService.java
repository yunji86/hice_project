package com.greedy.coffee.review.Service;


import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.greedy.coffee.member.entity.Member;
import com.greedy.coffee.member.repository.MemberRepository;
import com.greedy.coffee.review.dto.RevBoardDTO;
import com.greedy.coffee.review.entity.RevBoard;
import com.greedy.coffee.review.repository.RevBoardRepository;


@Service
@Transactional
public class RevBoardService {
	
	private final MemberRepository memberRepository;
	private final RevBoardRepository revBoardRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public RevBoardService(ModelMapper modelMapper, RevBoardRepository revBoardRepository,  MemberRepository memberRepository) {

		this.modelMapper = modelMapper;
		this.revBoardRepository = revBoardRepository;
		this. memberRepository = memberRepository;
		
	}
	
	public static final int REV_PAGE_SIZE = 9;
	public static final String SORT_BY = "revCode";
	public static final String ACITVE_STATUS = "Y";
	public static final String DELETE_STATUS = "N";
	public static final int PHOTO_BOARD_TYPE = 1;

	public Page<RevBoardDTO> selectRevBoardList(int page, String revSearchValue) {

		Pageable pagable = PageRequest.of(page - 1, REV_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<RevBoard> revBoardList = null;

		if (revSearchValue != null && !revSearchValue.isEmpty()) {
			revBoardList = revBoardRepository.findByRevSearchValue(ACITVE_STATUS, revSearchValue, pagable);
		} else {
			revBoardList = revBoardRepository.findByRevStatus( ACITVE_STATUS, pagable);
		}
		return revBoardList.map(Board -> modelMapper.map(Board, RevBoardDTO.class));
	}

	public void createRevBoard(RevBoardDTO revBoard) {
		
		
		revBoardRepository.save(modelMapper.map(revBoard, RevBoard.class));
	}

	public RevBoardDTO RevBoardView (Long revCode) {
		RevBoard revBoard = revBoardRepository.findByRevCode(revCode);

		return modelMapper.map(revBoard, RevBoardDTO.class);
	}

	
	public void updateRevBoard(RevBoardDTO updateRev) {

		RevBoard savedRev = revBoardRepository.findByRevCode(updateRev.getRevCode());
		savedRev.setRevTitle(updateRev.getRevTitle());
		savedRev.setRevContents(updateRev.getRevContents());
		savedRev.setRevEditDate(updateRev.getRevDate());
	}

	public void deleteRev(Long revCode) {
		
		RevBoard savedRevBoard = revBoardRepository.findByRevCode(revCode);
		
		savedRevBoard.setRevStatus("N");
		
	}
}
