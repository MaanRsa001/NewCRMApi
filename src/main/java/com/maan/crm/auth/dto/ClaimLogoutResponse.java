package com.maan.crm.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ClaimLogoutResponse {
	
	@JsonProperty("Status")
    private String status;

}
