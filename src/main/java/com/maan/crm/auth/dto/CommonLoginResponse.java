package com.maan.crm.auth.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maan.crm.util.error.Error;

import lombok.Data;

@Data
public class CommonLoginResponse {

	@JsonProperty("LoginResponse")
    private ClaimLoginResponse loginResponse;
	@JsonProperty("Errors")
    private List<Error> errors;
	
	@JsonProperty("ChangePasswordYn")
    private String changePasswordYn;
}
