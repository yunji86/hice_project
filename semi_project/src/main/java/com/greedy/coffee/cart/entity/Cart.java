package com.greedy.coffee.cart.entity;

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
@Table(name = "TBL_CART")
@SequenceGenerator(name = "CRT_SEQ_GENERATOR", sequenceName = "SEQ_CART_CODE", initialValue = 1, allocationSize = 1)
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CRT_SEQ_GENERATOR")
	@Column(name="CART_CODE")
	private Long cartCode;	

	@ManyToOne
	@JoinColumn(name="MEM_ID")
	private Member member;
	
	
}
