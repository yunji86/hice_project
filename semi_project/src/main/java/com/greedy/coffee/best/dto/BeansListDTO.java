package com.greedy.coffee.best.dto;
 
import com.greedy.coffee.store.dto.StoreDTO;
import lombok.Data;
 
@Data 
public class BeansListDTO {
 
	 private Long preBeanPrice;
	 private StoreDTO stoCode;
	 private BeansDTO beanCode; 
 }
 