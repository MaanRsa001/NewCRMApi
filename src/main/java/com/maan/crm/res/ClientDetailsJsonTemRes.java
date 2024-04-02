package com.maan.crm.res;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maan.crm.req.ClientAddressDetailsListSaveReq;
import com.maan.crm.req.ClientAddressDetailsSaveReq;
import com.maan.crm.req.ClientCorContactReq;
import com.maan.crm.req.ClientCrConListSaveReq;
import com.maan.crm.req.ClientDetailsSaveReq;
import com.maan.crm.req.ClientDetailsUpdateReq;
import com.maan.crm.req.ClientMemberDetailsListSaveReq;
import com.maan.crm.req.ClientMemberDetailsReq;

import lombok.Data;

@Data
public class ClientDetailsJsonTemRes {

	@JsonProperty("ClientDetails")
	private ClientDetailsSaveReq clientDetails;

	@JsonProperty("OtherDetails")
	private ClientDetailsUpdateReq otherDetails;

	@JsonProperty("ClientAddressDetails")
	private List<ClientAddressDetailsSaveReq> clientAddressDetailsList ;  
	
	@JsonProperty("ClientCorDetails")
	private List<ClientCorContactReq> clientDetailsList ; 
	
	@JsonProperty("ClientMemberDetails")
	private List<ClientMemberDetailsReq> clientMemberDetailsList ; 

	
}
