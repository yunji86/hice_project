package com.greedy.coffee.qna.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "TBL_QNA")
@SequenceGenerator(name = "QNA_SEQ_GENERATOR", sequenceName = "SEQ_QNA_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Qna {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QNA_SEQ_GENERATOR")
	@Column(name = "QNA_CODE")
	private Long qnaCode;
	
	@Column(name = "QNA_TITLE")
	private String qnaTitle;
	
	@Column(name = "QNA_CONTENT")
	private String qnaContent;
	
	@Column(name = "QNA_DATE")
	private Date qnaDate;
	
	@Column(name = "QNA_EDIT_DATE")
	private Date qnaEditDate;
	
	@Column(name = "QNA_DELETE_DATE")
	private Date qnaDeleteDate;
	
	@Column(name = "QNA_STATUS")
	private String qnaStatus;
	
	@ManyToOne
	@JoinColumn(name = "MEM_ID")
	private Member writer;
	
}
