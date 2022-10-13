package com.greedy.coffee.member.dto;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MemberDTO implements UserDetails {
	private String memId;
	private String memName;
	private String memPwd;
	private String memPhone;
	private String memEmail;
	
	private Long memDate;
	
	private String memStatus;
	private String memRole;	// 추가
	
	private String memPhone1;
	private Long memPhone2;
	private Long memPhone3;
	
	public MemberDTO() {
		
	}
	
	public MemberDTO(String memPhone, String memPhone1, Long memPhone2, Long memPhone3) {
		this.memPhone = memPhone;
		this.memPhone1 = memPhone1;
		this.memPhone2 = memPhone2;
		this.memPhone3 = memPhone3;
	}
	
	public MemberDTO(String memId, String memName, String memPwd, String memPhone, String memEmail, Long memDate,
			String memStatus, String memRole) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.memPwd = memPwd;
		this.memPhone = memPhone;
		this.memEmail = memEmail;
		this.memDate = memDate;
		this.memStatus = memStatus;
		this.memRole = memRole;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public Long getMemDate() {
		return memDate;
	}
	public void setMemDate(Long memDate) {
		this.memDate = memDate;
	}
	public String getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
	}
	public String getMemRole() {
		return memRole;
	}
	public void setMemRole(String memRole) {
		this.memRole = memRole;
	}

	public String getMemPhone1() {
		return memPhone1;
	}
	public void setMemPhone1(String memPhone1) {
		this.memPhone1 = memPhone1;
	}
	
	public Long getMemPhone2() {
		return memPhone2;
	}
	public void setMemPhone2(Long memPhone2) {
		this.memPhone2 = memPhone2;
	}
	
	public Long getMemPhone3() {
		return memPhone3;
	}
	public void setMemPhone3(Long memPhone3) {
		this.memPhone3 = memPhone3;
	}
	
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority(memRole));
		return roles;
		
	}
	@Override
	public String getPassword() {
		
		return memPwd;
		
	}
	@Override
	public String getUsername() {
		
		return memId;
		
	}
	// 계정 만료
	@Override
	public boolean isAccountNonExpired() {
		
		// true면 만료 아님
		return true;
		
	}
	// 계정 잠김
	@Override
	public boolean isAccountNonLocked() {
		
		// true면 안잠김
		return true;
		
	}
	// 비밀번호 만료
	@Override
	public boolean isCredentialsNonExpired() {
		
		// true면 비밀번호 만료 아님
		return true;
		
	}
	// 계정 활성화
	@Override
	public boolean isEnabled() {
		
		// true면 활성화
		return true;
		
	}
	@Override
	public String toString() {
		return "MemberDTO [memId=" + memId + ", memName=" + memName + ", memPwd=" + memPwd + ", memPhone=" + memPhone
				+ ", memEmail=" + memEmail + ", memDate=" + memDate + ", memStatus=" + memStatus + ", memRole="
				+ memRole + "]";
	}
	
}
//public MemberDTO(Member member, Collection<? extends GrantedAuthority> authorities) {
//	super(member.getMemId(), member.getMemPwd(), authorities);
//	setDetails(member);
//}
//
//public void setDetails(Member member) {
//	this.memId = member.getMemId();
//	this.memName = member.getMemName();
//	this.memPwd = member.getMemPwd();
//	this.memPhone = member.getMemPhone();
//	this.memEmail = member.getMemEmail();
//	this.memDate = member.getMemDate();
//	this.memStatus = member.getMemStatus();
//}