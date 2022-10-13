package com.greedy.coffee.best.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.coffee.best.dto.BeansDTO;
import com.greedy.coffee.best.entity.Beans;
import com.greedy.coffee.best.repository.BestRepository;

@Service
@Transactional
public class BestService {
	
	public static final int TEXT_PAGE_SIZE = 10;		//잘라올 컨텐츠 갯수
	public static final int TEXT_STORE_TYPE = 1;		//보드타입(text)
	public static final String SORT_BY = "beanName";	//정렬기준
	public static final String ACTIVE_STATUS = "Y";		//보여질상태
	
	
	private final BestRepository bestRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public BestService(BestRepository bestRepository, ModelMapper modelMapper) {
		this.bestRepository = bestRepository;
		this.modelMapper = modelMapper;
	}

	//select List
	public Page<BeansDTO> selectBestList(int page, String searchValue) {

		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY));
		Page<Beans> beanList = null;
		
		
		if(searchValue != null && !searchValue.isEmpty()) {
			//검색어가 있는 상황
			beanList = bestRepository.findBySearchValue(TEXT_STORE_TYPE, ACTIVE_STATUS, searchValue, pageable);
		} else {
			//검색어가 없는 상황															
			beanList = bestRepository.findByBeanTypeAndBeanStatus(TEXT_STORE_TYPE, ACTIVE_STATUS, pageable);
		}
		
		return beanList.map(beans -> modelMapper.map(beans, BeansDTO.class));
	
	
	}
	
	
	
}
