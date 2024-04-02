package com.maan.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name ="MENU_MASTER")
public class MenuMaster implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//--- ENTITY PRIMARY KEY 
  
    
	@Id
    @Column(name="MENU_ID",nullable=false)
    private Integer menuId;
	
    @Column(name="MENU_NAME")
    private String menuName;

    @Column(name="MENU_URL")
    private String menuUrl;
	
    @Column(name="PARENT_ID")
    private Integer parentId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="ENTRY_DATE",nullable=false)
    private Date entryDate;
    
    @Column(name="STATUS")
    private String      status ;
    
    @Column(name="USER_TYPES")
    private String userTypes;
   
}
