package com.maan.crm.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data

public class VehicleColorSaveReq {
	@JsonProperty("ColorId")
	private int colorId;
	
	@JsonProperty("ColorCode")
	private String colorCode;
	
	@JsonProperty ("ColorDesc")
	private String colorDesc;
	
	@JsonProperty("Status")
	private String status;
	

	@JsonProperty("CoreAppCode")
	private String coreAppCode;
	
	@JsonProperty("EntryDate")
	private Date entryDate;
	

	@JsonProperty("EffectiveDate")
	private Date effectiveDate;
	

	@JsonProperty("AmendId")
	private int amendId;
	

}
