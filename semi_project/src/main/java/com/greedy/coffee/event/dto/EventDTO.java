package com.greedy.coffee.event.dto;

import java.sql.Date;
import com.greedy.coffee.member.dto.MemberDTO;
import com.greedy.coffee.member.entity.Member;

import lombok.Data;

@Data
public class EventDTO {

	private Long eveCode;
	private String eveCompany;
	private String eveDepartment;
	private String evePosition;
	private String eveSector;
	private String eveRoute;
	private String memId;
	private Date eveDate;
	private String eveStatus;
	
	/*
	 * public EventDTO() { super(); }
	 * 
	 * public EventDTO(Long eveCode, String eveCompany, String eveDepartment, String
	 * evePosition, String eveSector, String eveRoute, MemberDTO memId, Date
	 * eveDate) { super(); this.eveCode = eveCode; this.eveCompany = eveCompany;
	 * this.eveDepartment = eveDepartment; this.evePosition = evePosition;
	 * this.eveSector = eveSector; this.eveRoute = eveRoute; this.memId = memId;
	 * this.eveDate = eveDate; }
	 * 
	 * public Long getEveCODE() { return eveCode; } public void setEveCODE(Long
	 * eveCode) { this.eveCode = eveCode; } public String getEveCompany() { return
	 * eveCompany; } public void setEveCompany(String eveCompany) { this.eveCompany
	 * = eveCompany; } public String getEveDepartment() { return eveDepartment; }
	 * public void setEveDepartment(String eveDepartment) { this.eveDepartment =
	 * eveDepartment; } public String getEvePosition() { return evePosition; }
	 * public void setEvePosition(String evePosition) { this.evePosition =
	 * evePosition; } public String getEveSector() { return eveSector; } public void
	 * setEveSector(String eveSector) { this.eveSector = eveSector; } public String
	 * getEveRoute() { return eveRoute; } public void setEveRoute(String eveRoute) {
	 * this.eveRoute = eveRoute; } public MemberDTO getMemId() { return memId; }
	 * public void setMemId(MemberDTO memId) { this.memId = memId; } public Date
	 * getEveDate() { return eveDate; } public void setEveDate(Date eveDate) {
	 * this.eveDate = eveDate; }
	 * 
	 * @Override public String toString() { return "EventDTO [eveCode=" + eveCode +
	 * ", eveCompany=" + eveCompany + ", eveDepartment=" + eveDepartment +
	 * ", evePostion=" + evePosition + ", eveSector=" + eveSector + ", eveRoute=" +
	 * eveRoute + ", memId=" + memId + ", eveDate=" + eveDate + "]"; }
	 */
	
}
