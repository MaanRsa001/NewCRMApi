package com.maan.crm.res;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ClientViewRes  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("ClientDetails")
	private ClientDetailsGetRes clientDetails;

	@JsonProperty("ClientIndividualDetails")
	private List<ClientMemberDetailsGetRes> clientIndiv;
	
	@JsonProperty("ClientCorporateContactDetails")
	private List<ClientCrContactGetRes> clientCorpContact;
	
	@JsonProperty("ClientGroupDetails")
	private List<ClientMemberDetailsGetRes> clientGroup;

	@JsonProperty("ClientAddressDetails")
	private List<ClientAddressDetailsGetRes> clientAddress;
	

}
