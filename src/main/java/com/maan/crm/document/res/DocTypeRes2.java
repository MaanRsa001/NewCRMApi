package com.maan.crm.document.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DocTypeRes2 {

//	@JsonProperty("DropDownRes")
//	private List<DropDownRes> dropDownRes;
	@JsonProperty("Code")
	private String code;
	@JsonProperty("CodeDesc")
	private String codeDesc;
}