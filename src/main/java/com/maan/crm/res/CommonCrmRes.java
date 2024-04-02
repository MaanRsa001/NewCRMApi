package com.maan.crm.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maan.crm.util.error.Error;
import lombok.Data;

@Data
public class CommonCrmRes {

	@JsonProperty("Message")
	private String message;

	@JsonProperty("IsError")	
	private Boolean isError;
	
	@JsonProperty("ChangePasswordYn")	
	private String changePasswordYn;

	@JsonProperty("ErrorMessage")
	private List<Error> errorMessage;

	//Dynamic
	@JsonProperty("Result")
	private Object commonResponse;
	
	@JsonProperty("ErroCode")
	private int erroCode;

/*	@JsonProperty("AdditionalData")
	private DefaultAllResponse defaultValue; */
}
