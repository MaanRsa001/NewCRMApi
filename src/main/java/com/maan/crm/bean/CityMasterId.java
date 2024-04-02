package com.maan.crm.bean;

import java.io.Serializable;
import java.util.Date;

public class CityMasterId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private Integer     cityId ;
    
    private Date effectiveDate ;
    
    private Integer     amendId ;
    
    private Integer stateCode;
    private String insCompanyId;

}
