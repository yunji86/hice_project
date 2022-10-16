package com.greedy.coffee.admin.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.coffee.admin.repository.OrderHistoryRepository;
import com.greedy.coffee.order.dto.OrderDTO;
import com.greedy.coffee.order.entity.Order;


@Service
@Transactional
public class OrderHistoryService {

	public static final int TEXT_PAGE_SIZE = 10;		//잘라올 컨텐츠 갯수
	public static final int TEXT_ORDER_TYPE = 1;
	public static final String SORT_BY = "ordDate";		//정렬기준
	public static final String ACTIVE_STATUS = "Y";		//보여질상태
	
	private final OrderHistoryRepository orderHistoryRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public OrderHistoryService( ModelMapper modelMapper, OrderHistoryRepository orderHistoryRepository) {
		this.orderHistoryRepository = orderHistoryRepository;
		this.modelMapper = modelMapper;
	}
	
	
	//select List
	public Page<OrderDTO> selectOrderHistory(int page, String searchValue) {

		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY));
		Page<Order> orderHistoryList = null;
		
		
		
		if(searchValue != null && !searchValue.isEmpty()) {
			//검색어가 있는 상황
			orderHistoryList = orderHistoryRepository.findBySearchValue(TEXT_ORDER_TYPE, ACTIVE_STATUS, searchValue, pageable);
		} else {
			//검색어가 없는 상황															
			orderHistoryList = orderHistoryRepository.findByOrdTypeAndOrdStatus(TEXT_ORDER_TYPE, ACTIVE_STATUS, pageable);
		}
		
		return orderHistoryList.map(order -> modelMapper.map(order, OrderDTO.class));
	}

}
