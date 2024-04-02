package com.maan.crm.res;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LeadViewRes  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("LeadDetails")
	private CrmLeadRes leadDetails;

	@JsonProperty("OldPolicy")
	private OldPolicyRes oldPolicy;
	
	@JsonProperty("VehicleDetails")
	private VehicleDetailsRes vehicleDetails;

}
