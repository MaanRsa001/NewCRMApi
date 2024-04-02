package com.maan.crm.req;

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

public class PolicyBulkEditReq  implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Policyid")
	private List<Integer> policyid ;
	
	@JsonProperty("ChangePos")
	private String changePos;
	@JsonProperty("ChangePosId")
	private String changePosId;
	
	@JsonProperty("ChangeSource")
	private String changeSource;
	@JsonProperty("ChangeSourceId")
	private String changeSourceId;
	
	@JsonProperty("ChangeLocation")
	private String changeLocation;
	
	
	
	}
