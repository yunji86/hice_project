package com.greedy.coffee.store.dto;
  
import java.sql.Date;
import java.util.List;

import com.greedy.coffee.best.dto.BeansDTO;
import com.greedy.coffee.best.dto.BeansListDTO;
import com.greedy.coffee.member.dto.MemberDTO;

import lombok.Data;
  
@Data 
public class StoreDTO {
  
  private Long stoCode; 
  private Integer stoType;
  private String stoName; 
  private String stoAdd;
  private char stoStatus; 
  private Date stoDate;
  private Date stoEditDate;
  private Date stoDeleteDate;
  private String call;
  private MemberDTO memId;
  private BeansDTO bean;
  
}
 