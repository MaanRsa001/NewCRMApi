package com.maan.crm.bean;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnquiryDetailsId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private String enquiryId;
	
    private String leadId;
    
    private String clientRefNo;
    
}
