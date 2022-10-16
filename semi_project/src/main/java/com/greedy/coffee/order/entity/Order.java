package com.greedy.coffee.order.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.coffee.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_ORDER")
@SequenceGenerator(name = "ORD_SEQ_GENERATOR", sequenceName = "SEQ_ORD_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert 
public class Order {
	
	@Id
	@Column(name = "ORD_CODE")
	private Long ordCode;
	
	@Column(name = "ORD_NAME")
	private String ordName;
	
	@Column(name = "ORD_PHONE")
	private int ordPhone;
	
	@ManyToOne
	@JoinColumn(name = "MEM_ID")
	private Member member;
	
	@Column(name = "ORD_ADDRESS")
	private String ordAddress;
	
	@Column(name = "ORD_TOTALAMOUNT")
	private int ordTotalAmount;
	
	@Column(name = "ORD_STATUS")
	private String ordStatus;
	
	@Column(name = "ORD_NUM")
	private int ordNum;
	
	@Column(name = "ORD_DATE")
	private Date ordDate;
	
	@Column(name = "ORD_TYPE")
	private int ordType;
	
	@Column(name = "QUANTITY")
	private int quantity;

}
