package com.greedy.coffee.product.entity;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert 
@Entity
@Table(name = "TBL_PRODUCT")
@SequenceGenerator(name = "PRO_SEQ_GENERATOR", sequenceName = "SEQ_PRO_CODE", initialValue = 1, allocationSize = 1)
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_SEQ_GENERATOR")
	@Column(name="PRO_CODE")
	private Long proCode;	//상품 코드
	
	@Column(name="PRO_TYPE")
	private String proType; //상품 카테고리
	
	@Column(name="PRO_NAME")
	private String proName; //상품 이름
	
	@Column(name="PRO_PRICE")
	private int proPrice; //상품 가격
	
	@Column(name="PRO_CONTENTS")
	private String proContent; //상품 설명
	
	@Column(name="PRO_COUNT")
	private int proCount;		//조회수
	
	@Column(name="PRO_STATUS")
	private String proStatus;		//상품 상태(판매중/품절)
	
	@ManyToOne
	@JoinColumn(name="MEM_ID")
	private Member writer; //상품등록 관리자

	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "RE_PRO_CODE")
	private List<File> fileList;
	
	
	
}
