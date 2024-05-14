package com.maan.crm.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class PolicyDataId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String policyId;
    private String policyNumber;
    private int prospectId;
    private String leadId;
    private String clientRefNo;


}
