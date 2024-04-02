package com.maan.crm.req;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maan.crm.bean.ClaimLoginMaster;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CamundaReq implements Serializable  {

	private static final long serialVersionUID = 1L;

    
	@JsonProperty("data")
	private ClaimLoginMaster data;
	   
}