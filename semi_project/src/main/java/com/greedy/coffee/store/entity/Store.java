package com.greedy.coffee.store.entity;
  
  
import java.sql.Date;
import java.util.List;

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

import com.greedy.coffee.best.entity.BeansList;
import com.greedy.coffee.member.entity.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  
  
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_STORE")
@SequenceGenerator(name = "STO_SEQ_GENERATOR", sequenceName = "SEQ_STO_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert 
public class Store {
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STO_SEQ_GENERATOR")
  @Column(name = "STO_CODE") 
  private Long stoCode;
  
  @Column(name = "STO_TYPE")
  private Integer stoType;
  
  @Column(name = "STO_NAME") 
  private String stoName;
  
  @Column(name = "STO_ADD") 
  private String stoAdd;
  
  @Column(name = "STO_STATUS")
  private char stoStatus; 
  
  @Column(name = "STO_DATE")
  private Date stoDate;
  
  @Column(name = "STO_EDIT_DATE")
  private Date stoEditDate;
  
  @Column(name = "STO_DELETE_DATE")
  private Date stoDeleteDate; 
  
  @ManyToOne
  @JoinColumn(name = "MEM_ID")
  private Member memId;
  
  @OneToMany
  @JoinColumn(name = "STO_CODE")
  private List<BeansList> beansList;
  
  
}
 