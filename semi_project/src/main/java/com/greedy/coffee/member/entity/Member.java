package com.greedy.coffee.member.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TBL_MEMBER")
@DynamicInsert /* insert 동작 시 null인 필드를 제외하고 동작한다 */
public class Member {
	
	@Id
	@Column(name = "MEM_ID")
	private String memId;
	
	@Column(name = "MEM_NAME")
	private String memName;
	
	@Column(name = "MEM_PWD")
	private String memPwd;
	
	@Column(name = "MEM_PHONE")
	private long memPhone;
	
	@Column(name = "MEM_EMAIL")
	private String memEmail;
	
	@Column(name = "MEM_DATE")
	private Long memDate;
	
	@Column(name = "MEM_STATUS")
	private String memStatus;
	
	@Column(name = "MEM_ROLE")
	private String memRole;
	
}