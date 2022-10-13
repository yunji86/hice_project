package com.greedy.coffee.best.entity;
  

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


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  
  @Getter
  @Setter
  @NoArgsConstructor
  @Entity
  @Table(name = "TBL_BEANS")
  @SequenceGenerator(name = "BEAN_SEQ_GENERATOR", sequenceName = "SEQ_BEAN_CODE", initialValue = 1, allocationSize = 1)
  @DynamicInsert 
  public class Beans {
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BEAN_SEQ_GENERATOR")
  @Column(name = "BEAN_CODE") 
  private Long beanCode;
  
  @Column(name = "BEAN_NAME") 
  private String beanName;
  
  @Column(name = "BEAN_PRICE") 
  private String beanPrice;
  
  @Column(name = "BEAN_SOLIDITY") 
  private String beanSolidity;
  
  @Column(name = "BEAN_COUNTRY") 
  private String beanCountry;
  
  @Column(name = "BEAN_EXPLAN")
  private String beanExplan;
  
  @Column(name = "BEAN_TYPE")
  private int beanType;
  
  @Column(name = "BEAN_STATUS")
  private String beanStatus;
  
//  @ManyToOne
//  @JoinColumn(name = "PRO_CODE") 
//  private Product proCode; 
  
//  @OneToMany(cascade=CascadeType.PERSIST)
//  @JoinColumn(name = "QNA_REFER_CODE")
//  private List<FileDTO> fileList;
  
  }
 