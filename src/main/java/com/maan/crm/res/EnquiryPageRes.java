package com.maan.crm.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EnquiryPageRes {
	
	@JsonProperty("EnquiryCount")
	private Long enquiryCount;
	
	@JsonProperty("EnquiryList")
	private List<EnquiryGridRes> enquiryList;

}
