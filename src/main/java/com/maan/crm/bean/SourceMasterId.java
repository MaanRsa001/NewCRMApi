package com.maan.crm.bean;

import java.io.Serializable;
import java.util.Date;

public class SourceMasterId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private Integer     sourceId ;
    
    private Date effectiveDate ;
    
    private Integer     amendId ;
    
    private String insCompanyId;
}
