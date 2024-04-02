package com.maan.crm.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadDetailsId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private String     leadId ;
    
    private String     clientRefNo ;
    
    private String     createdBy ;

    private String     insCompanyId ; 
    
    
}
