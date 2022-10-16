package com.greedy.coffee.event.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.member.entity.Member;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_EVENT")
@SequenceGenerator(name = "EVE_SEQ_GENERATOR", sequenceName = "SEQ_EVE_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVE_SEQ_GENERATOR")
	@Column(name = "EVE_CODE")
	private Long eveCode;
	
	@Column(name = "EVE_COMPANY")
	private String eveCompany;
	
	@Column(name = "EVE_DEPARTMENT")
	private String eveDepartment;
	
	@Column(name = "EVE_POSITION")
	private String evePosition;
	
	@Column(name = "EVE_SECTOR")
	private String eveSector;
	
	@Column(name = "EVE_ROUTE")
	private String eveRoute;
	
	
	@Column(name = "MEM_ID")
	private String memId;
	
	@Column(name = "EVE_DATE")
	private Date eveDate;
	
	@Column(name = "EVE_STATUS")
	private String eveStatus;
}
