package com.maan.crm.req;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClaimLoginUserDetailsReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("LoginUserId")
	private Integer loginUserId;
	
	@JsonProperty("LoginId")
	private String loginId;

	@JsonProperty("BranchCode")
	private String branchCode;

	@JsonProperty("RegionCode")
	private String regionCode;

	@JsonProperty("InsCompanyId")
	private String insCompanyId;

	@JsonProperty("ProductId")
	private String productId;
	
	@JsonProperty("UsertypeId")
	private Integer usertypeId;

	@JsonProperty("Usertype")
	private String usertype;
	
	@JsonProperty("SuminsuredStart")
	private Double suminsuredStart;
	
	@JsonProperty("SuminsuredEnd")
	private Double suminsuredEnd;
		
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Remarks")
	private String remarks;

}
