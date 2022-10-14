package com.greedy.coffee.product.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.greedy.coffee.file.dto.FileDTO;
import com.greedy.coffee.file.entity.File;
import com.greedy.coffee.product.dto.ProDTO;
import com.greedy.coffee.product.entity.Product;
import com.greedy.coffee.product.repository.ProRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Transactional
public class ProService {
	
	private final ProRepository proRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public ProService(ModelMapper modelMapper, ProRepository proRepository) {
		this.modelMapper = modelMapper;
		this.proRepository = proRepository;
	}

	public static final int PRO_PAGE_SIZE = 15;
	public static final int PRO_THUMBNAIL_PAGE_SIZE = 12;
	public static final String SORT_BY = "proCode";
	public static final String ACTIVE_STATUS = "Y";
	
	public Page<ProDTO> selectProductList(int page, String proSearchValue) {
		
		Pageable pageable = PageRequest.of(page -1, PRO_PAGE_SIZE, Sort.by(SORT_BY).descending());
		Page<Product> productList = null;
				
		
		if(proSearchValue != null && !proSearchValue.isEmpty()) {
			productList = proRepository.findByProSearchValue(ACTIVE_STATUS, proSearchValue, pageable);
		}else {
			productList = proRepository.findByProStatus(ACTIVE_STATUS, pageable);
		}
		
		return productList.map(product -> modelMapper.map(product, ProDTO.class));
	}

	public ProDTO selectBoardDetail(Long proCode) {
		
		Product product = proRepository.findByProCodeAndProStatus(proCode, ACTIVE_STATUS);
		
		return modelMapper.map(product, ProDTO.class);
	}

	public void registProduct(ProDTO product) {
		
		proRepository.save(modelMapper.map(product, Product.class));
	}
	
	public ProDTO proView(Long proCode) {
		
		Product product = proRepository.findByProCode(proCode);
		
		return modelMapper.map(product, ProDTO.class);
	}

	public void modifyPro(@ModelAttribute ProDTO modifyPro) {
		
		Product product = proRepository.findByProCode(modifyPro.getProCode());
		product.setProName(modifyPro.getProName());
		product.setProType(modifyPro.getProType());
		product.setProPrice(modifyPro.getProPrice());
		product.setProContent(modifyPro.getProContent());

	}

	public void deletePro(Long proCode) {
		
		Product product = proRepository.findByProCode(proCode);
		
		
		product.setProStatus("N");
	}

	


}
