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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.coffee.file.entity.File;
import com.greedy.coffee.member.entity.Member;
import com.greedy.coffee.product.entity.Product;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@DynamicInsert 
@Entity
@Table(name = "TBL_CART_PRO")
@SequenceGenerator(name = "CTP_SEQ_GENERATOR", sequenceName = "SEQ_CTP_CODE", initialValue = 1, allocationSize = 1)
public class CartPro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CTP_SEQ_GENERATOR")
	@Column(name="CARTPRO_CODE")
	private Long cartProCode;	
	
	@OneToOne
	@JoinColumn(name="CART_CODE")
	private Cart cart;	
	
	@OneToOne
	@JoinColumn(name="PRO_CODE")
	private Product product;	

	@JoinColumn(name="count")
	private int count;	

	
}
