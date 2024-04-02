package com.maan.crm.document.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maan.crm.res.DropDownRes;

import lombok.Data;

@Data
public class DocTypeRes {

//	@JsonProperty("DropDownRes")
//	private List<DropDownRes> dropDownRes;
	@JsonProperty("Code")
	private String code;
	@JsonProperty("CodeDesc")
	private String codeDesc;
}
