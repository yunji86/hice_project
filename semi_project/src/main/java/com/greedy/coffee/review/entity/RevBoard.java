package com.greedy.coffee.review.entity;


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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name= "TBL_REVIEW")
@SequenceGenerator(name = "REV_SEQ_GENERATOR", sequenceName = "SEQ_REV_CODE", initialValue = 1, allocationSize = 1)
public class RevBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REV_SEQ_GENERATOR")
	@Column(name = "REV_CODE")
	private Long revCode;
	
	@Column(name = "REV_TITLE")
	private String revTitle;
	
	@Column(name = "REV_CONTENTS")
	private String revContents;
	
	@Column(name = "REV_DATE")
	private Date revDate;
	
	@Column(name = "REV_EDIT_DATE")
	private Date revEditDate;
	
	@Column(name = "REV_DELETE_DATE")
	private Date revDeleteDate;
	
	@Column(name = "REV_STATUS")
	private String revStatus;
	
	@ManyToOne
	@JoinColumn(name="MEM_ID")
	private Member writer;
	
	/*@ManyToOne
	@JoinColumn(name="PRO_CODE")
	private Product pro;*/
	
	@Column(name = "REV_COUNT")
	private Long revCount;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinColumn(name = "REV_CODE")
	private List<File> fileList;
	
	
	
}