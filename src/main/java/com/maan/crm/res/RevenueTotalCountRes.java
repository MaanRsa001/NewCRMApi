package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RevenueTotalCountRes {

	@JsonProperty("TotalRevenueWithTax")
	private String totalRevenueWithTax;
	
	@JsonProperty("TotalRevenueWithoutTax")
	private String totalRevenueWithoutTax;
	
	@JsonProperty("NextPossibityRevenue")
	private String nextPossibityRevenue ;
	
	
}
