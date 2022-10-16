package com.greedy.coffee.admin.service;



import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.coffee.admin.repository.AdminRepository;
import com.greedy.coffee.order.dto.OrderDTO;
import com.greedy.coffee.order.entity.Order;



@Service
@Transactional
public class AdminService {
	
	private final ModelMapper modelMapper;
	private final AdminRepository adminRepository;
	public static final int TEXT_PAGE_SIZE = 10;
	public static final String SORT_BY = "ordCode";
	public static final String ACTIVE_STATUS = "Y";
	
	public AdminService(ModelMapper modelMapper, AdminRepository adminRepository) {
		this.modelMapper = modelMapper;
		this.adminRepository = adminRepository;
	}

	public Page<OrderDTO> selectOrderStatus(int page) {
		
		Pageable pageable = PageRequest.of(page -1, TEXT_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Order> orederList = adminRepository.findByOrdStatus(ACTIVE_STATUS, pageable);
		return orederList.map(Ord -> modelMapper.map(Ord, OrderDTO.class));
	}

	public void staustChange(OrderDTO changeOrd) {
		
		Order order = adminRepository.findByOrdNum(changeOrd.getOrdNum());
		order.setOrdStatus("N");
		
	}


	
	
	
}
