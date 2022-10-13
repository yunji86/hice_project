package com.greedy.coffee.member.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBL_AUTHORITY")
@Getter
@Setter
@SequenceGenerator(name = "AUTH_SEQ_GENERATOR", sequenceName = "AUTHORITY", initialValue = 1, allocationSize = 1)
@NoArgsConstructor
public class Authority {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTH_SEQ_GENERATOR")
	@Column(name = "AUTHORITY_CODE")
	private long authorityCode;
	
	@Column(name = "AUTHORITY_NAME")
	private String authorityName;
	
	@Column(name = "AUTHORITY_DESC")
	private String authorityDesc;
	
}