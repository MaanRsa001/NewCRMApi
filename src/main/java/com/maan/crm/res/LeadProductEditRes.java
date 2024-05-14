package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maan.crm.req.LeadProductDetailsListReq;

import lombok.Data;

@Data
public class LeadProductEditRes {
	
	@JsonProperty("ClientDetails")
	private ClientDetailsEditRes clientDetails;
	
	@JsonProperty("LeadProductList")
	private LeadProductDetailsListReq leadProductList;
}
