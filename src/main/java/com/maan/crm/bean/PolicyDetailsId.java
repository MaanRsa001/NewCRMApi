
package com.maan.crm.bean;

import java.io.Serializable;

import lombok.Data;


@Data
public class PolicyDetailsId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer policyid;
	
	
	private String leadId;

	private String policyNumber;

	private String clientRefNo;

	private String insCompanyId;
	
	private String branchCode;


}

