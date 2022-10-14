package com.greedy.coffee.event.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.coffee.event.dto.EventDTO;
import com.greedy.coffee.event.entity.Event;
import com.greedy.coffee.event.repository.EventRepository;


@Service
@Transactional
public class EventService {
	
	private final EventRepository eventRepository;
	private final ModelMapper modelMapper;
	
	public EventService(EventRepository eventRepository, ModelMapper modelMapper) {
		
		this.eventRepository = eventRepository;
		this.modelMapper = modelMapper;
		
	}

	public void registEvent(EventDTO event) {
		
		eventRepository.save(modelMapper.map(event, Event.class));
		
		
	}

}
