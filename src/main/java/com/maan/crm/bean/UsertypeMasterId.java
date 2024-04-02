package com.maan.crm.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsertypeMasterId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private Integer     usertypeId ;
    
    
    private String companyId;
}
