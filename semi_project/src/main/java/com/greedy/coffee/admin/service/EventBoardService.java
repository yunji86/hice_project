/*package com.greedy.coffee.admin.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.coffee.admin.repository.EventBoardRepository;

@Service
@Transactional
public class EventBoardService {
	
	public static final int TEXT_PAGE_SIZE = 10;
	public static final String SORT_BY = "eventCode";
	public static final String ACTIVE_STATUS = "Y";
	
	private final EventBoardRepository eventBoardRepositorye;
	private final ModelMapper modelMapper;
	
	public EventBoardService(EventBoardRepository eventBoardRepositorye, ModelMapper modelMapper) {
		
		this.eventBoardRepositorye = eventBoardRepositorye;
		this.modelMapper = modelMapper;
	}
	
	
	public Page<EevenDTO> selectBoardList(int page) {
		
		Pageable pageable = PageRequest.of(page -1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Event> eventList = null;
		
		eventList = eventBoardRepositorye
		return eventList.map(event -> modelMapper.map(event, EventDTO.class));
	}

}*/
