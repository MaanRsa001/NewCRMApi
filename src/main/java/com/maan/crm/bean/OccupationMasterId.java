package com.maan.crm.bean;

import java.io.Serializable;
import java.util.Date;

public class OccupationMasterId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private Integer     occupationId ;
    
    private Date effectiveDate ;
    
    private Integer     amendId ;
    
    private String insCompanyId ;

}
