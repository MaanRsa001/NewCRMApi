package com.maan.crm.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AssigntoUserRes {

	@JsonProperty("Code")
	private String code;
	@JsonProperty("CodeDesc")
	private String codeDesc;
}
