package com.greedy.coffee.best.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.coffee.best.repository.BestRepository;
import com.greedy.coffee.product.dto.ProDTO;
import com.greedy.coffee.product.entity.Product;

@Service
@Transactional
public class BestService {
	
	public static final int TEXT_PAGE_SIZE = 10;			//잘라올 컨텐츠 갯수
	public static final String TEXT_PRODUCT_TYPE = "원두";	//보드 타입
	public static final String SORT_BY = "proName";			//정렬 기준
	public static final String ACTIVE_STATUS = "Y";			//보여질 기본 상태
	
	private final BestRepository bestRepository;
	private final ModelMapper modelMapper;
	
	//의존성 부여
	@Autowired
	public BestService(BestRepository bestRepository, ModelMapper modelMapper) {
		this.bestRepository = bestRepository;
		this.modelMapper = modelMapper;
	}


	//select - List
	public Page<ProDTO> selectBestList(int page, String searchValue) {

		Pageable pageable = PageRequest.of(page - 1, TEXT_PAGE_SIZE, Sort.by(SORT_BY));
		Page<Product> bestList = null;
		
		//검색 로직
		if(searchValue != null && !searchValue.isEmpty()) {
			//검색어가 있는 상황
			bestList = bestRepository.findBySearchValue(TEXT_PRODUCT_TYPE, ACTIVE_STATUS, searchValue, pageable);
		} else {
			//검색어가 없는 상황															
			bestList = bestRepository.findByProTypeAndProStatus(TEXT_PRODUCT_TYPE, ACTIVE_STATUS, pageable);
		}
		
		
		return bestList.map(product -> modelMapper.map(product, ProDTO.class));
	
	
	}
	
	
	
}
