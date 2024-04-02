package com.maan.crm.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class FollowUpDetailsId implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY KEY ATTRIBUTES 
    private Integer followupid;

    private String clientid;

    private String companyid;

    private String loginid;

    private String followupapplicableid;
}
