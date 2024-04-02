package com.maan.crm.res;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PolicySearchRes  implements Serializable {

	@JsonProperty("PolicyCount")
	private Long policyCount;
	
	@JsonProperty("PolicyDetails")
	private  List<PolicyDetailsGetAllRes> prospectDetails;
	
}
