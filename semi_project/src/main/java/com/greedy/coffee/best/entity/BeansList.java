package com.greedy.coffee.best.entity;
  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.coffee.store.entity.Store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_BEANSLIST")
@DynamicInsert 
public class BeansList {
  
	@Id
	@Column(name = "PRE_BEAN_PRICE") 
	private Long preBeanPrice;
	
	@Column(name = "STO_CODE") 
	private Long stoCode;
	  
	@ManyToOne
	@JoinColumn(name = "BEAN_CODE") 
	private Beans beans;
  }
 