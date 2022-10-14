package com.greedy.coffee.reply.entity;

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
@Table(name = "TBL_REPLY")
@SequenceGenerator(
		name = "REPLY_SEQ_GENERATOR",
		sequenceName = "SEQ_REPLY_NO",
		initialValue = 1,
		allocationSize = 1
)
@DynamicInsert 
public class Reply {
	
	@Id
	@Column(name = "REPLY_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "REPLY_SEQ_GENERATOR"
	)
	private Long replyNo;
	
	@Column(name = "REPLY_CONTENTS")
	private String replyContent;
	
	@Column(name = "REPLY_STATUS")
	private String replyStatus;
	
	@Column(name = "REPLY_DATE")
	private Date replyDate;
	
	@Column(name = "REPLY_EDIT_DATE")
	private Date replyEditDate;
	
	@Column(name = "REPLY_DELETE_DATE")
	private Date replyDeleteDate;
	
	@ManyToOne
	@JoinColumn(name = "MEM_ID")
	private Member writer;
	
	@Column(name = "REV_CODE")
	private Long revCode;
	
	@Column(name = "QNA_CODE")
	private Long qnaCode;
}	