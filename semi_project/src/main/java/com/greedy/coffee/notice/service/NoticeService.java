package com.greedy.coffee.notice.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.coffee.notice.dto.NoticeDTO;
import com.greedy.coffee.notice.entity.Notice;
import com.greedy.coffee.notice.repository.NoticeRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Transactional
public class NoticeService {
	
	public static final int TEXT_PAGE_SIZE = 10;
	public static final String SORT_BY = "notCode";
	public static final String ACTIVE_STATUS = "Y";
	
	private final NoticeRepository noticeRepository;
	public final ModelMapper modelMapper;
	
	public NoticeService(NoticeRepository noticeRepository, ModelMapper modelMapper) {
		this.noticeRepository = noticeRepository;
		this.modelMapper = modelMapper;
	}

	public Page<NoticeDTO> selectNoticeList(int page) {

		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Notice> noticeList = noticeRepository.findByNotStatus(ACTIVE_STATUS, pageable);
		
		log.info("noticeList : {}", noticeList.getContent());
		
		return noticeList.map(notice -> modelMapper.map(notice, NoticeDTO.class));
	}

	public void noticeRegist(NoticeDTO notice) {

		noticeRepository.save(modelMapper.map(notice, Notice.class));
		
	}

	public NoticeDTO findNoticeByCode(Long notCode) {

		Notice notice = noticeRepository.findById(notCode).get();
		
		
		return modelMapper.map(notice, NoticeDTO.class);
	}
	
	public NoticeDTO seleNoticeDetail(Long notCode) {
		
		Notice notice = noticeRepository.findByNotCode(notCode);
		
		return modelMapper.map(notice, NoticeDTO.class);
	}

	public void modifyMember(NoticeDTO noticeModify) {
		
		Notice savedNotice = noticeRepository.findByNotCode(noticeModify.getNotCode());
		savedNotice.setNotTitle(noticeModify.getNotTitle());
		savedNotice.setNotContent(noticeModify.getNotContent());
	}

	public void removeNotice(NoticeDTO notice) {

		Notice savedNotice = noticeRepository.findByNotCode(notice.getNotCode());
		savedNotice.setNotStatus("N");
	}
	
}
