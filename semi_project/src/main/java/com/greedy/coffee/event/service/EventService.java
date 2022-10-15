package com.greedy.coffee.event.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.coffee.event.dto.EventDTO;
import com.greedy.coffee.event.entity.Event;
import com.greedy.coffee.event.repository.EventRepository;


@Service
@Transactional
public class EventService {
	
	public static final int TEXT_PAGE_SIZE = 10;
	public static final String SORT_BY = "eveCode";
	public static final String ACTIVE_STATUS = "Y";
	
	
	private final EventRepository eventRepository;
	private final ModelMapper modelMapper;
	
	public EventService(EventRepository eventRepository, ModelMapper modelMapper) {
		
		this.eventRepository = eventRepository;
		this.modelMapper = modelMapper;
		
	}

	public void registEvent(EventDTO event) {
		
		eventRepository.save(modelMapper.map(event, Event.class));	
	}
	
	
	public Page<EventDTO> selectBoardList(int page) {
		
		Pageable pageable = PageRequest.of(page -1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Event> eventList = eventRepository.findByEveStatus(ACTIVE_STATUS, pageable);
		return eventList.map(event -> modelMapper.map(event, EventDTO.class));
	}


}
