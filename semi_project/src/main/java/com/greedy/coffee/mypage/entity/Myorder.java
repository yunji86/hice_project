package com.greedy.coffee.mypage.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.coffee.file.entity.File;
import com.greedy.coffee.member.entity.Member;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@DynamicInsert 
@Entity
@Table(name = "TBL_ORDER")
@SequenceGenerator(name = "ORD_SEQ_GENERATOR", sequenceName = "SEQ_ORD_CODE", initialValue = 1, allocationSize = 1)
public class Myorder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORD_SEQ_GENERATOR")
	@Column(name="ORD_CODE")
	private Long orderCode;	
	
	@Column(name="ORD_NAME")
	private String orderName;
	
	@Column(name="ORD_PHONE")
	private String orderPhone; 
	
	@Column(name="ORD_ADDRESS")
	private String orderAddress;
	
	@Column(name="ORD_NUM")
	private String orderNum;
	
	@Column(name="ORD_TOTALAMOUNT")
	private int orderTotalAmount;		
	
	@Column(name="ORD_STATUS")
	private String orderStatus;		
	
	@Column(name = "ORD_DATE")
	private Date orderDate;
	
	@ManyToOne
	@JoinColumn(name="MEM_ID")
	private Member member;
	
	
	
}
